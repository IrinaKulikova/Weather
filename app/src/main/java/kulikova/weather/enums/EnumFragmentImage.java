package kulikova.weather.enums;

public enum EnumFragmentImage {

    TAB_MORNING("morning.jpg"),
    TAB_DAY("day.jpg"),
    TAB_EVENING("evening.jpg"),
    TAB_NIGHT("night.jpg"),
    TAB_3_DAYS("3_days.jpg");

    public String getPath() {
        return path;
    }

    String path;

    EnumFragmentImage(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }

    public static String getByPosition(Integer position) {
        return EnumFragmentImage.values()[position].getPath();
    }
}
