package test.epam.tagency.validator;

import by.epam.tagency.validator.PaycardValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PaycardValidatorTest {

    @Test
    public void testIsPaycardValue() {
        String incomeValue = "1234567";
        boolean actual = PaycardValidator.isPaycardValue(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongPaycardValue(String incomeValue) {
        boolean actual = PaycardValidator.isPaycardValue(incomeValue);
        assertFalse(actual);
    }

    @DataProvider(name = "wrongData")
    public Object[][] wrongData() {
        return new Object[][]{
                {null}, {" "}, {""}, {"ЫЕ12345"}, {"999999999"}, {"12345"}
        };
    }
    
}