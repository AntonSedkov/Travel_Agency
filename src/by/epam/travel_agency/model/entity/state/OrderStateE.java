package by.epam.travel_agency.model.entity.state;

import by.epam.travel_agency.model.entity.ClientOrder;

public interface OrderStateE {

    void activateState(ClientOrder order);

}