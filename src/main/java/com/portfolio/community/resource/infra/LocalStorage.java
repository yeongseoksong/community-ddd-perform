package com.portfolio.community.resource.infra;
import com.portfolio.community.resource.domain.Resource;
import com.portfolio.community.resource.domain.StorageStrategy;
import com.portfolio.community.resource.domain.StorageType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.*;

@Component
@Slf4j
@Getter
public class LocalStorage implements StorageStrategy {


    private final String storagePath;
    private  final Path basePath;
    private final StorageType  storageType = StorageType.LOCAL;

    public LocalStorage(@Value("${storage.path}") String storagePath) {
        this.storagePath = storagePath;
        this.basePath = Paths.get(storagePath);
    }

    @Override
    public void save(byte[] data, String fileName) {
        Path targetPath = calcPath(fileName);
        try {
            Files.createDirectories(targetPath.getParent());

            AsynchronousFileChannel channel = AsynchronousFileChannel.open(
                    targetPath,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            ByteBuffer buffer = ByteBuffer.wrap(data);

            channel.write(buffer, 0, null, new CompletionHandler<>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    log.info("File saved successfully to: {}", targetPath.toAbsolutePath());
                    try {
                        channel.close();
                    } catch (IOException e) {
                        log.error("Failed to close channel after success", e);
                    }
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    log.error("Failed to save file to: {}", targetPath.toAbsolutePath(), exc);
                    try {
                        channel.close();
                    } catch (IOException e) {
                        log.error("Failed to close channel after error", e);
                    }
                }
            });

        } catch (IOException e) {
            log.error("Failed to save file asynchronously (setup error)", e);
            throw new RuntimeException("Failed to save file asynchronously", e);
        }
    }





    @Override
    public void delete(String filename) {
        try {
            Path targetPath = basePath.resolve(filename).normalize();
            if (Files.exists(targetPath)) {
                Files.delete(targetPath);
                log.info("Deleted file: {}", targetPath.toAbsolutePath());
            } else {
                log.warn("File not found for deletion: {}", targetPath.toAbsolutePath());
            }
        } catch (IOException e) {
            log.error("Failed to delete file", e);
            throw new RuntimeException("Failed to delete file", e);
        }
    }

    @Override
    public Path calcPath(String fileName){
        Path targetPath = basePath.resolve(fileName).normalize();

        return targetPath;
    }

}