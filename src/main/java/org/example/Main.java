package org.example;

public class Main {
    public static void main(String[] args) {

        String exp = "5 + -(9 + 1) * -(8 + 2) + 5";

        int startPos = 4;
        int ennPos = 11;

        String head = exp.substring(0, startPos);
        String body = "(" + exp.substring(startPos + 1, ennPos + 1) + " * -1)";
        String tail = exp.substring(ennPos + 1);

        System.out.println("head: " + head);
        System.out.println("body: " + body);
        System.out.println("tail: " + tail);

        System.out.println("exp : " + exp);
        System.out.printf("exp : ");
        System.out.printf(head);
        System.out.printf(body);
        System.out.printf(tail);
    }
}