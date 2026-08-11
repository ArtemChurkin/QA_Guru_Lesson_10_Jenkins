package tests.simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkippedTests {

    @Test
    @Disabled
    public void someTest() {
        assertTrue(false);
    }

    @Test
    @Disabled("Some reason")
    public void someTest1() {
        assertTrue(false);
    }

    @Test
    @Disabled("Some reason2")
    public void someTest2() {
        assertTrue(false);
    }

}
