package storeapp.domain.enums;

public enum CustomerState {

    ACTIVE(true),
    INACTIVE(false);

    private final boolean description;

    CustomerState(boolean description){
        this.description = description;
    }

    public boolean getDescription(){
        return description;
    }
}
