package ifmg.bkend._5.TrabalhoBackEnd_computer.models;


public enum ProductType {
    GPU("gpu"),
    CPU("cpu"),
    MOTHER_BOARD("mb"),
    MEMORY("memo"),
    PSU("psu");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProductType fromValue(String value){

        for (ProductType type : ProductType.values()){
            if (type.getValue().equals(value)){
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid user Type: " + value);
    }
}
