package com.graph.graph.payload;

/**
 * Represents the response after a file operation such as upload or download.
 */
public class FileResponse {

    private String fileName; // The name of the file involved in the operation
    private String message;  // Status message about the operation

    /**
     * Constructs a FileResponse with the given file name and message.
     *
     * @param fileName The name of the file.
     * @param message  A message describing the result of the file operation.
     */
    public FileResponse(String fileName, String message) {
        super();
        this.fileName = fileName; // Initializes the fileName field
        this.message = message;   // Initializes the message field
    }

    /**
     * Retrieves the name of the file.
     *
     * @return The name of the file.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the name of the file.
     *
     * @param fileName The new name of the file.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Retrieves the message describing the operation status.
     *
     * @return The status message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message describing the operation status.
     *
     * @param message The new status message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
