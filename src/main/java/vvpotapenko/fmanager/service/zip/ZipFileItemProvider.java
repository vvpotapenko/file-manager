package vvpotapenko.fmanager.service.zip;

import org.apache.commons.io.FileUtils;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.model.UpFileItem;
import vvpotapenko.fmanager.service.BaseFileItemProvider;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileItemProvider extends BaseFileItemProvider {

    private Map<String, File> tempDirectories = new HashMap<>();

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof ZipFileItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) throws Exception {
        ZipFileItem zipFileItem = (ZipFileItem) directory;
        File tempDirectory = getTempDirectory(zipFileItem);

        List<IFileItem> items = new LinkedList<>();
        File[] files = tempDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                items.add(createLocalFileItem(file, directory));
            }
        }
        Collections.sort(items);
        items.add(0, UpFileItem.getInstance());
        return items;
    }

    @Override
    public InputStream createInputStream(IFileItem fileItem) throws Exception {
        ZipFileItem zipFileItem = (ZipFileItem) fileItem;
        return FileUtils.openInputStream(zipFileItem.file);
    }

    private File getTempDirectory(ZipFileItem zipFileItem) throws IOException {
        File file = zipFileItem.file;

        String filePath = file.getAbsolutePath();
        File dir = tempDirectories.get(filePath);
        if (dir == null) {
            unzipFile(file, filePath);
            dir = tempDirectories.get(filePath);
        }

        return dir;
    }

    private void unzipFile(File zipFile, String filePath) throws IOException {
        File tempDirectory = Files.createTempDirectory("fs-").toFile();
        FileUtils.forceMkdir(tempDirectory);

        byte[] buffer = new byte[1024];
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                File destFile = new File(tempDirectory, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    FileUtils.forceMkdir(destFile);
                } else {
                    FileUtils.forceMkdirParent(destFile);
                    try (FileOutputStream fos = new FileOutputStream(destFile)) {
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }

                zipEntry = zipInputStream.getNextEntry();
            }
        }
        tempDirectories.put(filePath, tempDirectory);
    }
}
