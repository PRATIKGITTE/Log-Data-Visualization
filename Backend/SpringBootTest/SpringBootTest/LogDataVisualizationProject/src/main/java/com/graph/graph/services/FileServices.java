package com.graph.graph.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service interface for handling file operations, including uploading and retrieving files.
 */
@Service
public interface FileServices {

    /**
     * Uploads a file to the specified path and generates a chart based on the provided graph name.
     *
     * @param path       The directory path where the file will be uploaded.
     * @param file       The file to be uploaded.
     * @param graphName  The name of the graph/chart to be generated.
     * @return           The name of the generated image file.
     * @throws IOException If an error occurs during file upload or chart generation.
     */
    String uploadImage(String path, MultipartFile file, String graphName) throws IOException;

    /**
     * Retrieves a file as an InputStream from the specified path.
     *
     * @param path       The base path where the file is located.
     * @param file       The name of the file to retrieve.
     * @return           An InputStream for the file, or null if the file is not found.
     */
    InputStream getResource(String path, String file);
}
