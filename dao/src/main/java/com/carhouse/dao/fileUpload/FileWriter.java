package com.carhouse.dao.fileUpload;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;

/**
 * The interface File writer.
 */
public interface FileWriter {

    /**
     * Delete image.
     *
     * @param fileName the image name
     */
    void deleteFile(String fileName);

    /**
     * Write file.
     *
     * @param file     the file to write
     * @param fileName the file name
     * @throws FileSystemException the file system exception when writing file
     */
    void writeFile(MultipartFile file, String fileName) throws FileSystemException;
}
