package by.epam.tagency.model.entity;

public enum OrderState {
    NEW("new"),
    CONFIRMED("confirmed"),
    PAID("paid"),
    ADDED_DOCS("added_docs"),
    FINISHED("finished"),
    DECLINED("declined");

    private final String value;

    OrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}