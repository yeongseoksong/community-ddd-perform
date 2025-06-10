package com.portfolio.community.resource.infra;
import com.portfolio.community.resource.domain.StorageStrategy;
import com.portfolio.community.resource.domain.StorageType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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

    private  final Path basePath = Paths.get("C:/resources");
    private final StorageType  storageType = StorageType.LOCAL;

    @Override
    public void save(byte[] data, String filename) {
        Path targetPath = basePath.resolve(filename).normalize();

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



    public String getMimeType(String filename) {
        Path path = basePath.resolve(filename).normalize();
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            log.warn("application/octet-stream");
            return "application/octet-stream";
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
    public String calcPath(String filename) {
        return basePath+"/"+filename;
    }
}