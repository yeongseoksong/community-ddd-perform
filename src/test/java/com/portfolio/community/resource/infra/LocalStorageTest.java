package com.portfolio.community.resource.infra;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class LocalStorageTest {
    LocalStorage localStorage = new LocalStorage();

    private final String testFilename = "test.txt";
    private final byte[] testData = "Hello, NIO!".getBytes();

    private final Path testPath = Paths.get("C:/resources").resolve(testFilename).normalize();
    @Test
    void save() throws IOException {
        localStorage.save(testData, testFilename);

        // then
        assertTrue(Files.exists(testPath), "File should be created");
        byte[] savedData = Files.readAllBytes(testPath);
        assertArrayEquals(testData, savedData, "Saved file content should match original data");
    }

    @Test
    void load() throws IOException {
        byte[] load = localStorage.load(testFilename);
        String mimeType = localStorage.getMimeType(testFilename);

        assertTrue(Files.exists(testPath), "File should exist");
        assertNotNull(load, "Loaded data should not be null");
        assertArrayEquals(testData, load, "Loaded content should match original data");

        assertNotNull(mimeType, "MIME type should not be null");
        assertEquals(mimeType,"text/plain");
    }


    @Test
    void delete() {
        localStorage.delete(testFilename);
        assertFalse(Files.exists(testPath));

    }

    @Test
    void calcPath(){
        String s = localStorage.calcPath(testFilename);
        assertEquals(s,localStorage.getBasePath()+"/"+testFilename);
    }
}