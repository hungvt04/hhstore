package com.datn.be.infrastructure.utils;

import java.util.Date;

public class AppUtils {

    public static Long getCurrentTime() {
        return new Date().getTime();
    }

}
