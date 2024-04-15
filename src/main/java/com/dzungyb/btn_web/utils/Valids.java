package com.dzungyb.btn_web.utils;

public class Valids {
    public static <T> boolean isEmpty(T value){
        if(value == null) return true;
        if (value instanceof String string)
            return string.trim().equals("") || string.isEmpty();
        return false;
    }
}
