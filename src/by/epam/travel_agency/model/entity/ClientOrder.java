package by.epam.travel_agency.model.entity;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class ClientOrder extends Entity {
    private int idTour;
    private int idPassport;
    private int idTravelDoc;
    private LocalDateTime dateTimeOrder;
    private OrderState orderState;
    private String comment;

    private ClientPassport passport;
    private Tour tour;
    private TourDocs tourDocs;

    public ClientOrder() {
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

    public ClientPassport getPassport() {
        return passport;
    }

    public void setPassport(ClientPassport passport) {
        this.passport = passport;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public TourDocs getTourDocs() {
        return tourDocs;
    }

    public void setTourDocs(TourDocs tourDocs) {
        this.tourDocs = tourDocs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientOrder order = (ClientOrder) o;
        if (idTour != order.idTour) return false;
        if (idPassport != order.idPassport) return false;
        if (idTravelDoc != order.idTravelDoc) return false;
        if (dateTimeOrder != null ? !dateTimeOrder.equals(order.dateTimeOrder) : order.dateTimeOrder != null)
            return false;
        if (orderState != order.orderState) return false;
        if (comment != null ? !comment.equals(order.comment) : order.comment != null) return false;
        if (passport != null ? !passport.equals(order.passport) : order.passport != null) return false;
        if (tour != null ? !tour.equals(order.tour) : order.tour != null) return false;
        return tourDocs != null ? tourDocs.equals(order.tourDocs) : order.tourDocs == null;
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
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        result = 31 * result + (tourDocs != null ? tourDocs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientOrder.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("idTour=" + idTour)
                .add("idPassport=" + idPassport)
                .add("idTravelDoc=" + idTravelDoc)
                .add("dateTimeOrder=" + dateTimeOrder)
                .add("orderState=" + orderState)
                .add("comment='" + comment + "'")
                .add("passport=" + passport)
                .add("tour=" + tour)
                .add("tourDocs=" + tourDocs)
                .toString();
    }

}