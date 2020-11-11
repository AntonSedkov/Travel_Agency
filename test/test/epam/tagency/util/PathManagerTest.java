package test.epam.tagency.util;

import by.epam.tagency.util.PathManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PathManagerTest {

    @Test
    public void testGetProperty() {
        String expected = "/index.jsp";
        String actual = PathManager.getProperty("path.page.index");
        assertEquals(actual, expected);
    }

}