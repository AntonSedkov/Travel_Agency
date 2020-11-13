package by.epam.tagency.model.service.impl;

import by.epam.tagency.model.entity.TourType;
import by.epam.tagency.model.service.GeneralService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralServiceImpl implements GeneralService {
    private static final GeneralServiceImpl INSTANCE = new GeneralServiceImpl();

    private GeneralServiceImpl() {
    }

    public static GeneralServiceImpl getInstance() {
        return INSTANCE;
    }

    public int sumListValues(List<Integer> values) {
        int sum = 0;
        if (values != null) {
            sum = values.stream().mapToInt(Integer::intValue).sum();
        }
        return sum;
    }

    @Override
    public Set<String> formTourTypes() {
        Set<String> restTypes = Arrays.stream(TourType.values())
                .map(TourType::getValue)
                .collect(Collectors.toSet());
        return restTypes;
    }

}