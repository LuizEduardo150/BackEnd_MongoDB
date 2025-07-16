package ifmg.bkend._5.TrabalhoBackEnd_computer.models;


import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.TypeConverterException;

public enum ProductType {
    GPU("gpu"),
    CPU("cpu"),
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

        throw new TypeConverterException("Invalid product Type: " + value);
    }
}
