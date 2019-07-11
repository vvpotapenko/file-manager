package vvpotapenko.fmanager.providers.local;

import vvpotapenko.fmanager.providers.IFileSource;

import java.io.File;

class LocalFileSource implements IFileSource {

    private final File file;

    LocalFileSource(File file) {
        this.file = file;
    }
}
