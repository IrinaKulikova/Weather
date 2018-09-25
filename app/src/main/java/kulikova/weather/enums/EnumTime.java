package kulikova.weather.enums;

public enum EnumTime {
    MORNING("06:00:00", 0),
    MIDDAY("12:00:00", 1),
    EVENING("18:00:00", 2),
    NIGHT("00:00:00", 3);

    private String time;

    public Integer getIndex() {
        return index;
    }

    private Integer index;

    EnumTime(final String time, final Integer index) {
        this.time = time;
        this.index = index;
    }

    @Override
    public String toString() {
        return time;
    }

    public static EnumTime getByPosition(Integer curPosition) {
        return EnumTime.values()[curPosition];
    }
}
