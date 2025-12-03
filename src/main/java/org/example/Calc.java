package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static boolean debug = true;
    public static int runCallCount = 0;

    public static int run(String exp) {
        runCallCount++;

        exp = exp.trim(); // 문장 양 옆의 쓸데없는 공백 제거

        // 괄호 제거
        exp = stripOuterBrackets(exp);

        // 만약에 -( 패턴이면? -> 내가 해석할 수 있는 형태로 수정
        int[] pos = null;
        while ((pos = findCaseMinusBracket(exp)) != null) {
            exp = changeMinusBracket(exp, pos[0], pos[1]);
        }

        exp = stripOuterBrackets(exp);

        if (debug) {
            System.out.printf("exp (%d) : %s\n", runCallCount, exp);
        }

        // 그냥 숫자만 들어올 경우 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToSplit = exp.contains("(") || exp.contains(")");
        boolean needToCompound = needToPlus && needToMulti;

        exp = exp.replace("- ", "+ -");

        if (needToSplit) {
            int splitPointIndex = findSplitPointIndex(exp);

            String firstExp = exp.substring(0, splitPointIndex);
            String secondExp = exp.substring(splitPointIndex + 1);

            char operator = exp.charAt(splitPointIndex);

            exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);

            return Calc.run(exp);

        } else if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newExp);
        }

        if (needToPlus) {
            String[] bits = exp.split(" \\+ ");
            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        } else if (needToMulti) {
            String[] bits = exp.split(" \\* ");

            int sum = 1;

            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }

            return sum;
        }


        throw new RuntimeException("해석 불가 : 올바른 계산식이 아님");
    }

    private static String changeMinusBracket(String exp, int startPos, int endPos) {
        String head = exp.substring(0, startPos);
        String body = "(" + exp.substring(startPos + 1, endPos + 1) + " * -1)";
        String tail = exp.substring(endPos + 1);

        exp = head + body + tail;

        return exp;

    }

    private static int[] findCaseMinusBracket(String exp) {
        for (int i = 0; i < exp.length() - 1; i++) {
            if (exp.charAt(i) == '-' && exp.charAt(i + 1) == '(') {
                // -( 를 발견했다

                int bracketsCount = 1;

                for (int j = i + 2; j < exp.length(); j++) {
                    char c = exp.charAt(j);

                    if (c == '(') {
                        bracketsCount++;
                    } else if (c == ')') {
                        bracketsCount--;
                    }

                    if (bracketsCount == 0) {
                        return new int[]{i, j};  //스타트 포인트와 엔드포인트를 i와 j로 지정
                    }
                }
            }
        }
        return null;
    }

    private static int findSplitPointIndex(String exp) {
        int index = findSplitPointIndexBy(exp, '+');

        if (index >= 0) return index;

        return findSplitPointIndexBy(exp, '*');

    }

    private static int findSplitPointIndexBy(String exp, char findChar) {
        int bracketsCount = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') {
                bracketsCount++;
            } else if (c == ')') {
                bracketsCount--;
            } else if (c == findChar) {
                if (bracketsCount == 0) return i;
            }
        }
        return -1;
    }

    private static String stripOuterBrackets(String exp) {
        if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') { // 일단 가장 왼쪽과 가장 오른쪽의 문자가 괄호인지 체크
            // 이제부터 그 괄호가 한쌍인지 체크
            int bracketsCount = 0;

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    bracketsCount++;
                } else if (exp.charAt(i) == ')') {
                    bracketsCount--;
                }

                if (bracketsCount == 0) { // 괄호가 끝나는 지점이
                    if (exp.length() == i + 1) { // 문장의 끝이라면, 한쌍이 맞음
                        // 여기서 이렇게 재귀호출한 이유는, 바깥괄호가 어러겹일 수 있기 때문에, 이렇게 하면 쓸데없는 바깥괄호가 몇겹이던 한큐에 다 지워준다.
                        // 즉 이 함수의 사용자는 딱 한번만의 호출로 쓸데없는 괄호를 다 지울 수 있다.(몇겹이던 상관없이), 사실 내부적으로는 여러분 호출되지만 사용자는 그것을 모른다.
                        return stripOuterBrackets(exp.substring(1, exp.length() - 1));
                    }

                    // 한쌍이 아니라고 판단하면 원문 그대로 리턴
                    return exp;
                }
            }
        }

        // 가장 왼쪽과 가장 오른쪽의 문자가 괄호인지 체크했을 때, 괄호가 아니라면 뭘 더 조사하고 말게 없어서 바로 원문 그대로 리턴
        return exp;
    }


}