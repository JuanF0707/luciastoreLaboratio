package storeapp.domain.enums;

public enum CustomerTypeEnum {

    NEW_CUSTOMER ("Cliente Nuevo"),
    OLD_CUSTOMER ("Cliente Antiguo"),
    BLOCKED ("Cliente en mora");

    private final String description;

    CustomerTypeEnum(String description){
        this.description = description;
    }


    public String getDescription(){
        return description;
    }





}
