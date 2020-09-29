package by.epam.travel_agency.model.entity;

import java.util.StringJoiner;

public class TourDocument extends Entity {
    private final int idTour;
    private boolean isNeedVisa;
    private String voucher;
    private String insurancePolicy;
    private String ticketFlight;
    private String ticketBus;

    public TourDocument(int idTour) {
        this.idTour = idTour;
    }

    public TourDocument(int idTour, String voucher, String insurancePolicy) {
        this.idTour = idTour;
        this.voucher = voucher;
        this.insurancePolicy = insurancePolicy;
    }

    public int getTour() {
        return idTour;
    }

    public boolean isNeedVisa() {
        return isNeedVisa;
    }

    public void setNeedVisa(boolean needVisa) {
        isNeedVisa = needVisa;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(String insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getTicketFlight() {
        return ticketFlight;
    }

    public void setTicketFlight(String ticketFlight) {
        this.ticketFlight = ticketFlight;
    }

    public String getTicketBus() {
        return ticketBus;
    }

    public void setTicketBus(String ticketBus) {
        this.ticketBus = ticketBus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TourDocument that = (TourDocument) o;
        if (idTour != that.idTour) return false;
        if (isNeedVisa != that.isNeedVisa) return false;
        if (voucher != null ? !voucher.equals(that.voucher) : that.voucher != null) return false;
        if (insurancePolicy != null ? !insurancePolicy.equals(that.insurancePolicy) : that.insurancePolicy != null)
            return false;
        if (ticketFlight != null ? !ticketFlight.equals(that.ticketFlight) : that.ticketFlight != null) return false;
        return ticketBus != null ? ticketBus.equals(that.ticketBus) : that.ticketBus == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idTour;
        result = 31 * result + (isNeedVisa ? 1 : 0);
        result = 31 * result + (voucher != null ? voucher.hashCode() : 0);
        result = 31 * result + (insurancePolicy != null ? insurancePolicy.hashCode() : 0);
        result = 31 * result + (ticketFlight != null ? ticketFlight.hashCode() : 0);
        result = 31 * result + (ticketBus != null ? ticketBus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TourDocument.class.getSimpleName() + "[", "]")
                .add("idTour=" + idTour)
                .add("isNeedVisa=" + isNeedVisa)
                .add("voucher='" + voucher + "'")
                .add("insurancePolicy='" + insurancePolicy + "'")
                .add("ticketFlight='" + ticketFlight + "'")
                .add("ticketBus='" + ticketBus + "'")
                .toString();
    }

}