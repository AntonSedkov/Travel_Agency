package by.epam.tagency.model.service.impl;

import by.epam.tagency.model.entity.TourType;
import by.epam.tagency.model.service.GeneralService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneralServiceImpl implements GeneralService {
    private static final GeneralServiceImpl INSTANCE = new GeneralServiceImpl();


    private GeneralServiceImpl() {
    }

    public static GeneralServiceImpl getInstance() {
        return INSTANCE;
    }

    public int sumListValues(List<Integer> values) {
        int sum = values.stream().mapToInt(Integer::intValue).sum();
        return sum;
    }

    @Override
    public Set<String> formTourTypes() {
        Set<String> restTypes = new HashSet<>();
        for (TourType type : TourType.values()) {
            StringBuilder sb = new StringBuilder(type.toString().toLowerCase());
            sb.replace(0, 1, sb.substring(0, 1).toUpperCase());
            restTypes.add(sb.toString());
        }
        return restTypes;
    }

}