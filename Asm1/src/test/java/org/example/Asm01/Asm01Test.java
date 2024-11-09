package org.example.Asm01;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Asm01Test {
    static List<String> arr = Arrays.asList("a", "b", "v", "1", "2", "n", "l", "s");
    Asm01 asm01 = new Asm01();
    @SuppressWarnings("static-access")
    @Test
    void test1() {
        assertTrue(asm01.checkStringInList("a", arr), "Expected 'a' to be in the list");
    }

    @SuppressWarnings("static-access")
    @Test
    void test2() {
        assertTrue(asm01.checkStringInList("B", arr), "Expected 'B' to be in the list");
    }

    @SuppressWarnings("static-access")
    @Test
    void test3() {
        assertTrue(asm01.checkStringInList("V", arr), "Expected 'V' to be in the list");
    }

    @SuppressWarnings("static-access")
    @Test
    void test4() {
        assertFalse(asm01.checkStringInList("d", arr), "Expected 'd' not to be in the list");
    }

    @SuppressWarnings("static-access")
    @Test
    void test5() {
        assertFalse(asm01.checkStringInList("o", arr), "Expected 'o' not to be in the list");
    }

    @SuppressWarnings("static-access")
    @Test
    void test6() {
        assertFalse(asm01.checkStringInList("9", arr), "Expected '9' not to be in the list");
    }
}
