package by.epam.tagency.model.entity;

public enum OrderState {
    NEW("new"),
    CONFIRM("confirm"),
    PAID("paid"),
    ADD_DOCS("add_docs"),
    FINISHED("finished");

    private final String value;

    OrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}