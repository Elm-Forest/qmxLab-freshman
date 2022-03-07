package com.qmxkaifa.util;

import java.util.regex.Pattern;

public class isMail {
    public static boolean isEmail(String email) {
        if (email.length() == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        return pattern.matcher(email).matches();
    }
}