package vvpotapenko.fmanager.providers.local;

import org.apache.commons.io.FileUtils;
import vvpotapenko.fmanager.providers.IFileSource;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

class LocalFileSource extends BaseLocalFileSource implements IFileSource {

    private final File file;

    LocalFileSource(File file) {
        this.file = file;
    }

    @Override
    public Icon getSystemIcon() {
        return getSystemIcon(file);
    }

    @Override
    public void destroy() {
        // do nothing
    }

    @Override
    public String getDisplaySize() {
        return getDisplaySize(file);
    }

    @Override
    public InputStream createInputStream() throws IOException {
        return FileUtils.openInputStream(file);
    }
}
