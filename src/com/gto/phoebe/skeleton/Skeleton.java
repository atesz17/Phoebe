package com.gto.phoebe.skeleton;

public class Skeleton {
    public static int testCase = 0;
    public static int turn = 1;
    private static int level = 0;

    public static void init(int testCase) {
        Skeleton.testCase = testCase;
    }

    public static void methodCall(String method) {
        StringBuilder string = new StringBuilder();
        string.append(tabs()).append("-->").append(method);
        System.out.println(string);
        level++;
    }

    public static void methodReturn(String returnValue) {
        level--;
        StringBuilder string = new StringBuilder();
        string.append(tabs()).append("<--").append(returnValue);
        System.out.println(string);
    }

    private static String tabs() {
        String ret = "";
        for (int i = 0; i < level; i++) {
            ret += "\t";
        }
        return ret;
    }

}
