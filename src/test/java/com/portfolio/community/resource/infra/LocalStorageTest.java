package com.portfolio.community.resource.infra;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class LocalStorageTest {
    LocalStorage localStorage  = new LocalStorage("/tmp/resources");


    private final String testFilename = "test.txt";
    private final byte[] testData = "Hello, NIO!".getBytes();

    private final Path testPath = localStorage.getBasePath().resolve(testFilename).normalize();
    @Test
    @Order(1)
    void save() throws IOException {
        localStorage.save(testData, testFilename);

        // then
        assertTrue(Files.exists(testPath), "File should be created");
        byte[] savedData = Files.readAllBytes(testPath);
        assertArrayEquals(testData, savedData, "Saved file content should match original data");
    }



    @Test
    @Order(2)
    void delete() {
        localStorage.delete(testFilename);
        assertFalse(Files.exists(testPath));

    }

    @Test
    void calcPath(){
        Path path = localStorage.calcPath(testFilename);
        assertEquals(path.toString(),localStorage.getBasePath()+"\\"+testFilename);
    }
}