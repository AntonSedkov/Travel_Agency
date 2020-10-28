package by.epam.travel_agency.model.service;

import by.epam.travel_agency.model.entity.ClientSheet;

public interface SheetService {

    ClientSheet findSheetByUsername(String login) throws SecurityException;

    ClientSheet findSheetByIdSheet(String login) throws SecurityException;

}