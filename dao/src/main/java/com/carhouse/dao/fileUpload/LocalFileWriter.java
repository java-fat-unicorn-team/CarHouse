package com.carhouse.dao.fileUpload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.Objects;

/**
 * The local file writer.
 * Writes files to a folder that is processed by the Apache server
 *
 * @author Katuranau Maksimilyan
 * @see FileWriter
 */
@Component
public class LocalFileWriter implements FileWriter {

    @Value("${image.absolute.path}")
    private String IMAGES_ABSOLUTE_PATH;

    /**
     * Delete file if exist and don't equals to 'default' value.
     *
     * @param fileName the image name
     */
    @Override
    public void deleteFile(final String fileName) {
        if (Objects.nonNull(fileName) && !fileName.isEmpty() && !fileName.equals("default")) {
            File file = new File(IMAGES_ABSOLUTE_PATH + fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * Write file.
     * Generate unique file name using UUID
     *
     * @param file the file to write
     * @throws FileSystemException the file system exception when writing file
     */
    @Override
    public void writeFile(final MultipartFile file, final String fileName) throws FileSystemException {
        File localFile = new File(IMAGES_ABSOLUTE_PATH + fileName);
        try {
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(localFile, false));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            deleteFile(fileName);
            throw new FileSystemException("Something went wrong. Error writing file.");
        }
    }
}
