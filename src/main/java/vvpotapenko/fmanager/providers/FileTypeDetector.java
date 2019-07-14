package vvpotapenko.fmanager.providers;

import vvpotapenko.fmanager.model.FileType;

public class FileTypeDetector {

    /**
     * Detects file type
     *
     * @param fileName file name with extension
     * @return detected file type
     */
    public FileType getFileType(String fileName) {
        if (fileName.toLowerCase().endsWith(".txt")) {
            return FileType.TEXT;
        }
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            return FileType.JPG;
        }
        return FileType.UNKNOWN;
    }
}
