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
}
