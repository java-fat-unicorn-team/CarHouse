package com.carhouse.service.fileUpload;

import com.carhouse.service.exception.WriteFileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocalFileWriterTest {

    private String IMAGES_ABSOLUTE_PATH;

    private LocalFileWriter fileWriter;
    private MockMultipartFile multipartFile;
    private byte[] fileAsBytes;

    public LocalFileWriterTest() throws IOException {
        IMAGES_ABSOLUTE_PATH = getClass().getClassLoader()
                .getResource("default").getFile().replace("/default", "/");
        this.fileWriter = new LocalFileWriter();
        ReflectionTestUtils.setField(fileWriter, "IMAGES_ABSOLUTE_PATH", IMAGES_ABSOLUTE_PATH);
        this.fileAsBytes = Files.readAllBytes(
                Path.of(getClass().getClassLoader().getResource("default").getFile()));
        this.multipartFile = new MockMultipartFile("file", "file",
                "image/*", fileAsBytes);
    }

    @Test
    void deleteFile() {
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
        String fileName = "/write/with/exception";
        WriteFileException exception = assertThrows(WriteFileException.class,
                () -> fileWriter.writeFile(multipartFile, fileName));
        File file = new File(IMAGES_ABSOLUTE_PATH + fileName);
        assertEquals("Something went wrong. Error writing file.", exception.getMessage());
        assertFalse(file.exists());
    }
}