package by.epam.travel_agency.model.entity;

import java.util.StringJoiner;

public class ClientOrder extends Entity {
    private Tour tour;
    private long orderDate;
    private int quantity;
    private boolean isPaid;

    private static final boolean DEFAULT_PAID = false;

    public ClientOrder() {
        super();
    }

    public ClientOrder(Tour tour, long orderDate, int quantity) {
        super();
        this.tour = tour;
        this.orderDate = orderDate;
        this.quantity = quantity;
        isPaid = DEFAULT_PAID;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientOrder that = (ClientOrder) o;
        if (orderDate != that.orderDate) return false;
        if (quantity != that.quantity) return false;
        if (isPaid != that.isPaid) return false;
        return tour != null ? tour.equals(that.tour) : that.tour == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        result = 31 * result + (int) (orderDate ^ (orderDate >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (isPaid ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientOrder.class.getSimpleName() + "[", "]")
                .add("tour=" + tour)
                .add("orderDate=" + orderDate)
                .add("quantity=" + quantity)
                .add("isPaid=" + isPaid)
                .toString();
    }

}