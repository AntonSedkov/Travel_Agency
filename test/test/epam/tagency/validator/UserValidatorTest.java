package test.epam.tagency.validator;

import by.epam.tagency.validator.UserValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {

    @Test
    public void testIsValidLogin() {
        String incomeValue = "User_A";
        boolean actual = UserValidator.isValidLogin(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongValidLogin(String incomeValue) {
        boolean actual = UserValidator.isValidLogin(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsValidPassword() {
        String incomeValue = "User_999";
        boolean actual = UserValidator.isValidPassword(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongValidPassword(String incomeValue) {
        boolean actual = UserValidator.isValidPassword(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsValidEmail() {
        String incomeValue = "m/.#$%&’*=?`{|}~^il_22@ma-il_22.ma_1l";
        boolean actual = UserValidator.isValidEmail(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongValidEmail(String incomeValue) {
        boolean actual = UserValidator.isValidEmail(incomeValue);
        assertFalse(actual);
    }

    @Test
    public void testIsValidRole() {
        String incomeValue = "  uSer ";
        boolean actual = UserValidator.isValidRole(incomeValue);
        assertTrue(actual);
    }

    @Test(dataProvider = "wrongData")
    public void testIsWrongValidRole(String incomeValue) {
        boolean actual = UserValidator.isValidRole(incomeValue);
        assertFalse(actual);
    }

    @DataProvider(name = "wrongData")
    public Object[][] wrongData() {
        return new Object[][]{
                {null}, {" "}, {""}, {"sM4ll"}, {"B1111111_1111111g"},
                {"n0bigletter"}, {"N0SM4LLETTER"}, {"Wr@ng%ChaR$"},
                {"mail_1234_mail@mail.mail.mail.mail"}, {"mail@mail3%%$#@.mail"}
        };
    }

}