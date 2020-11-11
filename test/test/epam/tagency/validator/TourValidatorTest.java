package test.epam.tagency.validator;

import by.epam.tagency.validator.TourValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TourValidatorTest {

    @Test
    public void testIsValidTourType() {
        String incomeValue = " reST ";
        boolean actual = TourValidator.isValidTourType(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongValidTourType(String incomeValue) {
        boolean actual = TourValidator.isValidTourType(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsDigitParamValue() {
        String incomeValue = "12345";
        boolean actual = TourValidator.isDigitParamValue(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongDigitParamValue(String incomeValue) {
        boolean actual = TourValidator.isDigitParamValue(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsDaysValue() {
        String incomeValue = "25";
        boolean actual = TourValidator.isDaysValue(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongDaysValue(String incomeValue) {
        boolean actual = TourValidator.isDaysValue(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsDiscountValue() {
        String incomeValue = "15";
        boolean actual = TourValidator.isDiscountValue(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongDiscountValue(String incomeValue) {
        boolean actual = TourValidator.isDiscountValue(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsValidHotelType() {
        String incomeValue = "  hostel  ";
        boolean actual = TourValidator.isValidHotelType(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongValidHotelType(String incomeValue) {
        boolean actual = TourValidator.isValidHotelType(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsValidTransportType() {
        String incomeValue = "Bus";
        boolean actual = TourValidator.isValidTransportType(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongValidTransportType(String incomeValue) {
        boolean actual = TourValidator.isValidTransportType(incomeValue);
        assertFalse(actual);
    }

    @DataProvider(name = "wrongData")
    public Object[][] wrongData() {
        return new Object[][]{
                {null}, {" "}, {""}, {"Рус"}, {"ЫЕ12345678"}, {"999999999"}
        };
    }

}