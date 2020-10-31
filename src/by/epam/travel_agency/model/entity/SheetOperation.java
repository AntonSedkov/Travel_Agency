package by.epam.travel_agency.model.entity;

import java.util.StringJoiner;

public class SheetOperation extends Entity {
    private int idSheet;
    private int operationSum;
    private String operationPurpose;

    public SheetOperation() {
    }

    public SheetOperation(int idSheet, int operationSum, String operationPurpose) {
        this.idSheet = idSheet;
        this.operationSum = operationSum;
        this.operationPurpose = operationPurpose;
    }

    public int getIdSheet() {
        return idSheet;
    }

    public void setIdSheet(int idSheet) {
        this.idSheet = idSheet;
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
        SheetOperation sheetOperation = (SheetOperation) o;
        if (idSheet != sheetOperation.idSheet) return false;
        if (operationSum != sheetOperation.operationSum) return false;
        return operationPurpose != null ? operationPurpose.equals(sheetOperation.operationPurpose) : sheetOperation.operationPurpose == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idSheet;
        result = 31 * result + operationSum;
        result = 31 * result + (operationPurpose != null ? operationPurpose.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SheetOperation.class.getSimpleName() + "[", "]")
                .add("idSheet=" + idSheet)
                .add("operationSum=" + operationSum)
                .add("operationPurpose='" + operationPurpose + "'")
                .toString();
    }

}