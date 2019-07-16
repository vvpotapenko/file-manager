package vvpotapenko.fmanager.service;

import vvpotapenko.fmanager.model.FileItemType;

public class FileTypeDetector {

    /**
     * Detects file type
     *
     * @param fileName file name with extension
     * @return detected file type
     */
    public FileItemType getFileType(String fileName) {
        if (fileName.toLowerCase().endsWith(".txt")) {
            return FileItemType.TEXT;
        }
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            return FileItemType.JPG;
        }
        if (fileName.toLowerCase().endsWith(".png")) {
            return FileItemType.PNG;
        }
        return FileItemType.UNKNOWN;
    }
}
