package vvpotapenko.fmanager.providers.local;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.IDirectorySource;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipDirectorySource extends LocalDirectorySource {

    private File tempZipDirectory;

    ZipDirectorySource(File file, IDirectorySource parent, boolean onlyDirectories) {
        super(file, parent, onlyDirectories);
    }

    @Override
    public List<FileItem> loadChildren() throws Exception {
        if (tempZipDirectory == null) {
            unzipFile();
        }

        FileFilter filter = onlyDirectories ? FileFilterUtils.directoryFileFilter() : FileFilterUtils.trueFileFilter();
        File[] files = tempZipDirectory.listFiles(filter);
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        } else {
            List<FileItem> children = new ArrayList<>();
            for (File file : files) {
                children.add(createFileItem(file, this, onlyDirectories));
            }
            children.sort(defaultFilesComparator);
            return children;
        }
    }

    @Override
    public IDirectorySource clone(boolean onlyDirs) {
        IDirectorySource parent = getParent();
        IDirectorySource clonedParent = parent != null ? parent.clone(onlyDirs) : null;
        return new ZipDirectorySource(file, clonedParent, onlyDirs);
    }

    @Override
    public void destroy() {
        if (tempZipDirectory != null) {
            try {
                FileUtils.deleteDirectory(tempZipDirectory);
                tempZipDirectory = null;
            } catch (IOException e) {
                // TODO
                e.printStackTrace();
            }
        }
    }

    @Override
    public InputStream createInputStream() throws Exception {
        return FileUtils.openInputStream(file);
    }

    private void unzipFile() throws Exception {
        tempZipDirectory = Files.createTempDirectory("fs-").toFile();
        if (!tempZipDirectory.exists()) tempZipDirectory.mkdirs();

        byte[] buffer = new byte[1024];
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                File destFile = new File(tempZipDirectory, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    destFile.mkdirs();
                } else {
                    new File(destFile.getParent()).mkdirs();
                    FileOutputStream fos = new FileOutputStream(destFile);
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }

                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }
}
