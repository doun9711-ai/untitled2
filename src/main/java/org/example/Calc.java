package org.example;

public class Calc {

    public static int run(String exp) {

        exp = exp.replace("- ", "+ -");

        boolean needToPlus = exp.contains("+");

        String[] bits = null;

        if (needToPlus) {
            bits = exp.split(" \\+ ");
        }
        int sum = 0;
        for ( int i = 0; i < bits.length; i++) {
            sum = sum + Integer.parseInt(bits[i]);
        }

        return sum;
    }
}


