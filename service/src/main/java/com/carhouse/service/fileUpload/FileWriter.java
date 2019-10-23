package com.carhouse.service.fileUpload;

import org.springframework.web.multipart.MultipartFile;

/**
 * The interface File writer.
 */
public interface FileWriter {

    /**
     * Delete file.
     *
     * @param fileName the image name
     */
    void deleteFile(String fileName);

    /**
     * Write file.
     *
     * @param file     the file to write
     * @param fileName the file name
     */
    void writeFile(MultipartFile file, String fileName);
}
