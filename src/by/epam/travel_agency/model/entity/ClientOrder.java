package by.epam.travel_agency.model.entity;

import by.epam.travel_agency.model.entity.state.OrderState;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class ClientOrder extends Entity {
    private int idTour;
    private int idPassport;
    private int idTravelDoc;
    private LocalDateTime dateTimeOrder;
    private OrderState orderState;
    private String comment;

    public ClientOrder() {
    }

    public ClientOrder(int idTour, int idPassport, int idTravelDoc, LocalDateTime dateTimeOrder, OrderState orderState, String comment) {
        this.idTour = idTour;
        this.idPassport = idPassport;
        this.idTravelDoc = idTravelDoc;
        this.dateTimeOrder = dateTimeOrder;
        this.orderState = orderState;
        this.comment = comment;
    }

    public int getIdTour() {
        return idTour;
    }

    public void setIdTour(int idTour) {
        this.idTour = idTour;
    }

    public int getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(int idPassport) {
        this.idPassport = idPassport;
    }

    public int getIdTravelDoc() {
        return idTravelDoc;
    }

    public void setIdTravelDoc(int idTravelDoc) {
        this.idTravelDoc = idTravelDoc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientOrder that = (ClientOrder) o;
        if (idTour != that.idTour) return false;
        if (idPassport != that.idPassport) return false;
        if (idTravelDoc != that.idTravelDoc) return false;
        if (dateTimeOrder != null ? !dateTimeOrder.equals(that.dateTimeOrder) : that.dateTimeOrder != null)
            return false;
        if (orderState != null ? !orderState.equals(that.orderState) : that.orderState != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idTour;
        result = 31 * result + idPassport;
        result = 31 * result + idTravelDoc;
        result = 31 * result + (dateTimeOrder != null ? dateTimeOrder.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientOrder.class.getSimpleName() + "[", "]")
                .add("id=" + getId() + "")
                .add("idTour=" + idTour)
                .add("idPassport=" + idPassport)
                .add("idTravelDoc=" + idTravelDoc)
                .add("dateTimeOrder=" + dateTimeOrder)
                .add("orderState=" + orderState)
                .add("comment='" + comment + "'")
                .toString();
    }

}