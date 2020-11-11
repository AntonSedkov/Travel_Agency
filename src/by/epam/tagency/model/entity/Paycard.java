package by.epam.tagency.model.entity;

import java.util.StringJoiner;

public class Paycard extends Entity {
    private int cardNumber;
    private int cardSum;
    private int cardQuantity;

    public Paycard() {
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardSum() {
        return cardSum;
    }

    public void setCardSum(int cardSum) {
        this.cardSum = cardSum;
    }

    public int getCardQuantity() {
        return cardQuantity;
    }

    public void setCardQuantity(int cardQuantity) {
        this.cardQuantity = cardQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Paycard paycard = (Paycard) o;
        if (cardNumber != paycard.cardNumber) return false;
        if (cardSum != paycard.cardSum) return false;
        return cardQuantity == paycard.cardQuantity;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cardNumber;
        result = 31 * result + cardSum;
        result = 31 * result + cardQuantity;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Paycard.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("cardNumber=" + cardNumber)
                .add("cardSum=" + cardSum)
                .add("cardQuantity=" + cardQuantity)
                .toString();
    }

}