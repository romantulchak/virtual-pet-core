package com.virtualpet.utils;

import com.virtualpet.exeption.CannotParsePageException;

public class AppHelper {

    private AppHelper(){

    }

    public static int getCurrentPage(String pageAsString){
        if (pageAsString == null || pageAsString.isEmpty()){
            return 0;
        }
        try {
            int page = Integer.parseInt(pageAsString);
            if (page >= 1){
                page--;
            }
            return page;
        }catch (NumberFormatException e){
            throw new CannotParsePageException(pageAsString);
        }
    }
}
