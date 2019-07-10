package vvpotapenko.fmanager.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFileContainer extends BaseFile implements IFileContainer {

    private final List<IFile> children = new ArrayList<>();
    private final boolean hasChildren;

    public BaseFileContainer(String name, boolean hasChildren) {
        super(name);
        this.hasChildren = hasChildren;
    }

    @Override
    public List<IFile> getChildren() {
        return this.children;
    }

    @Override
    public boolean hasChildren() {
        return hasChildren;
    }
}
