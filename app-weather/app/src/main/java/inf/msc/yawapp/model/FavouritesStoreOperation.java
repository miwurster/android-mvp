package inf.msc.yawapp.model;

public interface FavouritesStoreOperation {
    public enum Operation {
        ADDED, DELETED, UPDATED
    }

    public Operation getOperation();

    public Location getLocation();
}
