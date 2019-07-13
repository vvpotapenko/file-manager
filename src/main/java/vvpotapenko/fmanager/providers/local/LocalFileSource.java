package vvpotapenko.fmanager.providers.local;

import vvpotapenko.fmanager.providers.IFileSource;

import javax.swing.*;
import java.io.File;

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
}
