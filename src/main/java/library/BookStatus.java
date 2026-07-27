package library;

public enum BookStatus {
    AVAILABLE,
    BORROWED,
    RESERVED,
    LOST;

    @Override
    public String toString() {
        switch (this) {
            case AVAILABLE:
                return "Available";
            case BORROWED:
                return "Borrowed";
            case RESERVED:
                return "Reserverd";
            default:
                return "";
        }
    }
}
