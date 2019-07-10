package vvpotapenko.fmanager.model;

public abstract class BaseFile implements IFile {

    private final String name;

    public BaseFile(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isContainer() {
        return false;
    }
}
