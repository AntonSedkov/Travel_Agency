package by.epam.tagency.model.entity;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class ClientOrder extends Entity {
    private Tour tour;
    private ClientPassport passport;
    private TravelDocs travelDocs;
    private LocalDateTime dateTimeOrder;
    private OrderState orderState;
    private String comment;

    public ClientOrder() {
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public ClientPassport getPassport() {
        return passport;
    }

    public void setPassport(ClientPassport passport) {
        this.passport = passport;
    }

    public LocalDateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
        this.dateTimeOrder = dateTimeOrder;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TravelDocs getTravelDocs() {
        return travelDocs;
    }

    public void setTravelDocs(TravelDocs travelDocs) {
        this.travelDocs = travelDocs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientOrder order = (ClientOrder) o;
        if (tour != null ? !tour.equals(order.tour) : order.tour != null) return false;
        if (passport != null ? !passport.equals(order.passport) : order.passport != null) return false;
        if (travelDocs != null ? !travelDocs.equals(order.travelDocs) : order.travelDocs != null) return false;
        if (dateTimeOrder != null ? !dateTimeOrder.equals(order.dateTimeOrder) : order.dateTimeOrder != null)
            return false;
        if (orderState != order.orderState) return false;
        return comment != null ? comment.equals(order.comment) : order.comment == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (travelDocs != null ? travelDocs.hashCode() : 0);
        result = 31 * result + (dateTimeOrder != null ? dateTimeOrder.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientOrder.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("tour=" + tour)
                .add("passport=" + passport)
                .add("travelDocs=" + travelDocs)
                .add("dateTimeOrder=" + dateTimeOrder)
                .add("orderState=" + orderState)
                .add("comment='" + comment + "'")
                .toString();
    }

}