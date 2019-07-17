package vvpotapenko.fmanager.model;

public abstract class BaseFileItem implements IFileItem {

    private final IFileItem parent;

    public BaseFileItem(IFileItem parent) {
        this.parent = parent;
    }

    @Override
    public IFileItem getParent() {
        return parent;
    }

    @Override
    public int compareTo(IFileItem o) {
        if (this.isDirectory() && !o.isDirectory()) {
            return -1;
        }
        if (o.isDirectory() && !this.isDirectory()) {
            return 1;
        }
        return this.getName().compareTo(o.getName());
    }
}
