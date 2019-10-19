package com.carhouse.dao.fileUpload;

import com.carhouse.dao.config.TestConfiguration;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LocalFileWriterTest {

    @Value("${image.absolute.path}")
    private String IMAGES_ABSOLUTE_PATH;

    private FileWriter fileWriter;
    private MockMultipartFile multipartFile;
    private byte[] fileAsBytes;

    @Autowired
    public LocalFileWriterTest(FileWriter fileWriter) throws IOException {
        this.fileWriter = fileWriter;
        this.fileAsBytes = Files.readAllBytes(
                Path.of(getClass().getClassLoader().getResource("default").getFile()));
        this.multipartFile = new MockMultipartFile("file", "file",
                "image/*", fileAsBytes);
    }

    @Test
    void deleteFile() throws FileSystemException {
        String fileName = "fileToDelete";
        fileWriter.writeFile(multipartFile, fileName);
        File file = new File(IMAGES_ABSOLUTE_PATH + fileName);
        assertTrue(file.exists());
        fileWriter.deleteFile(fileName);
        assertFalse(file.exists());
    }

    @Test
    void deleteNotExistFileShouldPass() {
        String fileName = "notExistFile";
        File file = new File(IMAGES_ABSOLUTE_PATH + fileName);
        assertFalse(file.exists());
        fileWriter.deleteFile(fileName);
        assertFalse(file.exists());
    }

    @Test
    void writeFile() throws IOException {
        String fileName = "newFileToTest";
        fileWriter.writeFile(multipartFile, fileName);
        File file = new File(IMAGES_ABSOLUTE_PATH + fileName);
        assertTrue(file.exists());
        assertArrayEquals(fileAsBytes, Files.readAllBytes(file.toPath()));
    }

    @Test
    void writeFileException() {
        String fileName = "writeWithException";
        FileSystemException exception = assertThrows(FileSystemException.class,
                () ->fileWriter.writeFile(null, fileName));
        File file = new File(IMAGES_ABSOLUTE_PATH + fileName);
        assertEquals("Something went wrong. Error writing file.", exception.getMessage());
        assertFalse(file.exists());
    }
}