package test.epam.tagency.model.service.impl;

import by.epam.tagency.model.service.impl.GeneralServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class GeneralServiceImplTest {
    GeneralServiceImpl service = GeneralServiceImpl.getInstance();

    @Test
    public void testSumListValues() {
        List<Integer> nums = List.of(10, 50, 1, 5, 5, 700);
        int expected = 771;
        int actual = service.sumListValues(nums);
        assertEquals(actual, expected);
    }

    @Test
    public void testSumListValuesNull() {
        List<Integer> nums = null;
        int expected = 0;
        int actual = service.sumListValues(nums);
        assertEquals(actual, expected);
    }

    @Test
    public void testFormTourTypes() {
        Set<String> expected = Set.of("Rest", "Excursion", "Shopping");
        Set<String> actual = service.formTourTypes();
        assertEquals(actual, expected);
    }

}