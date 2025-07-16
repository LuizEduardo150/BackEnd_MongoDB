package ifmg.bkend._5.TrabalhoBackEnd_computer.models;

import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.TypeConverterException;

public enum PerformanceLevel {
    LOWCOST(0),
    BASIC(1),
    MEDIAN(2),
    INTERMEDIARYEDIUM(3),
    INTERMEDIARY(4),
    HIGH(5),
    HIGHEND(6);


    private final Integer value;

    PerformanceLevel(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static PerformanceLevel fromValue(Integer value) {

        for (PerformanceLevel type : PerformanceLevel.values()) {
            if (type.getValue().equals(value))
                return type;
        }

        throw new TypeConverterException("Invalid performane Type: " + value);

    }

}