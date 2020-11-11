package test.epam.tagency.validator;

import by.epam.tagency.validator.GeneralValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GeneralValidatorTest {

    @Test
    public void testIsDigitValue() {
        String incomeValue = "234";
        boolean actual = GeneralValidator.isDigitValue(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsFalseDigitValue(String incomeValue) {
        boolean actual = GeneralValidator.isDigitValue(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsLatinLiterals() {
        String incomeValue = "Java";
        boolean actual = GeneralValidator.isLatinLiterals(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsFalseLatinLiterals(String incomeValue) {
        boolean actual = GeneralValidator.isLatinLiterals(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsDateFormat() {
        String incomeValue = "2000-20-01";
        boolean actual = GeneralValidator.isDateFormat(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsFalseDateFormat(String incomeValue) {
        boolean actual = GeneralValidator.isDateFormat(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsImageName() {
        String incomeValue = "image.jpeg";
        boolean actual = GeneralValidator.isImageName(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsFalseImageName(String incomeValue) {
        boolean actual = GeneralValidator.isImageName(incomeValue);
        assertFalse(actual);
    }

    @DataProvider(name = "wrongData")
    public Object[][] wrongData() {
        return new Object[][]{
                {null}, {" "}, {""}, {"22www"}, {"-22"}, {"рус"},
                {"2222-22-ee"}, {"2222.22pw"}, {"image2.newformat"}
        };
    }

}