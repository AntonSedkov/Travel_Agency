package by.epam.tagency.model.entity;

import java.util.StringJoiner;

public class SheetOperation extends Entity {
    private int operationSum;
    private String operationPurpose;

    public SheetOperation() {
    }

    public int getOperationSum() {
        return operationSum;
    }

    public void setOperationSum(int operationSum) {
        this.operationSum = operationSum;
    }

    public String getOperationPurpose() {
        return operationPurpose;
    }

    public void setOperationPurpose(String operationPurpose) {
        this.operationPurpose = operationPurpose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SheetOperation that = (SheetOperation) o;
        if (operationSum != that.operationSum) return false;
        return operationPurpose != null ? operationPurpose.equals(that.operationPurpose) : that.operationPurpose == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + operationSum;
        result = 31 * result + (operationPurpose != null ? operationPurpose.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SheetOperation.class.getSimpleName() + "[", "]")
                .add("idSheet=" + getId())
                .add("operationSum=" + operationSum)
                .add("operationPurpose='" + operationPurpose + "'")
                .toString();
    }

}