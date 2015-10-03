package org.lab1;

/**
 * Created by Strelchenko on 27.09.2015.
 */
public class test {
    public static void main(String[] args) {
        String[] info = {"sis", "sas", "assa"};
        String sqlS = "";
        for (int i = 0; i < info.length; i++) {
            sqlS += ",\"" + info[i] + "\"";
        }

        System.out.println(sqlS);
    }
}
