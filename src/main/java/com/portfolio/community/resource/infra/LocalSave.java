package com.portfolio.community.resource.infra;

import com.portfolio.community.resource.domain.StorageStrategy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class LocalSave implements StorageStrategy {


    private final String basePath="/resources";

    @Override
    public void save(byte[] data, String filename) {
        try {
            File file = new File(basePath, filename);
            file.getParentFile().mkdirs(); // 경로가 없으면 생성
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(data);
            }
            System.out.println("Saved locally: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file locally", e);
        }
    }

    @Override
    public void delete(String filename) {
        File file = new File(basePath, filename);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Deleted locally: " + file.getAbsolutePath());
            } else {
                throw new RuntimeException("Failed to delete file locally");
            }
        } else {
            System.out.println("File not found for deletion: " + file.getAbsolutePath());
        }
    }
}
