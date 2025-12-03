package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTest {

    @Test
    @DisplayName("1 + 1 == 2")
    public void test1() {
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 == 3")
    public void test2() {
        assertThat(Calc.run("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    public void test3() {
        assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("2 + 3 == 5")
    public void test4() {
        assertThat(Calc.run("2 + 3")).isEqualTo(5);
    }

    @Test
    @DisplayName("1000 + 200 == 1200")
    public void test5() {
        assertThat(Calc.run("1000 + 200")).isEqualTo(1200);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    public void test6() {
        assertThat(Calc.run("2 - 1")).isEqualTo(1);
    }

    @Test
    @DisplayName("50 - 30 == 20")
    public void test7() {
        assertThat(Calc.run("50 - 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("7 - 4 == 3")
    public void test8() {
        assertThat(Calc.run("7 - 4")).isEqualTo(3);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    public void test9() {
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    public void test10() {
        assertThat(Calc.run("10 - 20 + 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    public void test11() {
        assertThat(Calc.run("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    public void test12() {
        assertThat(Calc.run("10 - 10 - 10 - 10")).isEqualTo(-20);
    }

    @Test
    @DisplayName("10 + 20 + 30 - 10 + 60 == 110")
    public void test13() {
        assertThat(Calc.run("10 + 20 + 30 - 10 + 60")).isEqualTo(110);
    }

    @Test
    @DisplayName("10 * 10 == 100")
    public void test14() {
        assertThat(Calc.run("10 * 10")).isEqualTo(100);
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    public void test15() {
        assertThat(Calc.run("10 * 10 * 10")).isEqualTo(1000);
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    public void test16() {
        assertThat(Calc.run("10 + 5 * 2")).isEqualTo(20);
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    public void test17() {
        assertThat(Calc.run("20 + 10 + 5 * 2")).isEqualTo(40);
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    public void test18() {
        assertThat(Calc.run("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    public void test19() {
        assertThat(Calc.run("(10 + 20)")).isEqualTo(30);
    }

    @Test
    @DisplayName("((10 + 20)) == 30")
    public void test20() {
        assertThat(Calc.run("((10 + 20))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(((10 + 20))) == 30")
    public void test21() {
        assertThat(Calc.run("(((10 + 20)))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(20 + 20) + 20  == 60")
    public void test22() {
        assertThat(Calc.run("(20 + 20) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("((20 + 20)) + 20  == 60")
    public void test23() {
        assertThat(Calc.run("((20 + 20)) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    public void test24() {
        assertThat(Calc.run("(10 + 20) * 3")).isEqualTo(90);
    }

    @Test
    @DisplayName("(20 + 20) * 20 == 800")
    public void test25() {
        assertThat(Calc.run("(20 + 20) * 20")).isEqualTo(800);
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    public void test26() {
        assertThat(Calc.run("10 + (10 + 5)")).isEqualTo(25);
    }
}