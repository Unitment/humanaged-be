package vn.fsoft.humanaged.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Normalization {
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d"); }

    public static void main(String []args){
        System.out.println(removeAccent("Bùi Thanh Bảo"));
    }
}