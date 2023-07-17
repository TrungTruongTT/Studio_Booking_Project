package com.example.demofacebook.Ultils;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern EMAIL_ADDRESS = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    public static final Pattern PHONE_NUMBER = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
    public  static final Pattern PASSWORD = Pattern.compile("^" +
            //"(?=.*?[A-Z])" + //at least 1 digit
            //"(?=.*?[a-z])" +  //at least 1 lowercase
            "(?=.*[a-zA-Z])"+ // any character
            "(?=.*?[0-9])" +  // at least 1 number letter
            //"(?=.*?[#?!@$ %^&*-])" + //at least 1 special character
            ".{8,20}" +//at least 8 character and max is 20 character
            "$");


}
