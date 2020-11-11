package test.epam.tagency.validator;

import by.epam.tagency.validator.PassportValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PassportValidatorTest {

    @Test
    public void testIsPersonalString() {
        String incomeValue = "Ivanov";
        boolean actual = PassportValidator.isPersonalString(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongPersonalString(String incomeValue) {
        boolean actual = PassportValidator.isPersonalString(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsPassportNumber() {
        String incomeValue = "AB1234567";
        boolean actual = PassportValidator.isPassportNumber(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongPassportNumber(String incomeValue) {
        boolean actual = PassportValidator.isPassportNumber(incomeValue);
        assertFalse(actual);
    }

    @DataProvider(name = "wrongData")
    public Object[][] wrongData() {
        return new Object[][]{
                {null}, {" "}, {""}, {"Рус"}, {"ЫЕ12345678"}, {"CD999999999"}, {"EFGH1234567"},
                {"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"}
        };
    }

}