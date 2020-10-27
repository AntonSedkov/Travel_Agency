package by.epam.travel_agency.model.service.specification;

import by.epam.travel_agency.model.entity.ClientOrder;

import java.util.function.Predicate;

public interface OrderSpecification extends Predicate<ClientOrder> {
    int LOW_ORDER = 500;
    int ORDINARY_ORDER = 1500;
}