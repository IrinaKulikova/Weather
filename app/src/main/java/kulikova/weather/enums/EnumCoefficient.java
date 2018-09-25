package kulikova.weather.enums;

public enum EnumCoefficient {

    TEMPERATURE(273.15);
    private Double coefficient;

    EnumCoefficient(final Double coefficient) {
        this.coefficient = coefficient;
    }

    public double getValue() {
        return coefficient;
    }
}
