package com.mijiu.commom.util;

import com.mijiu.entity.Administrator;

/**
 * @author mijiupro
 */
public class AdminHolder { private static final ThreadLocal<Administrator> TOKEN_HOLDER = new ThreadLocal<>();

    public static void setInfoByToken(Administrator administrator) {
        TOKEN_HOLDER.set(administrator);
    }

    public static Administrator getInfoByToken() {
        return TOKEN_HOLDER.get();
    }

    public static void clear() {
        TOKEN_HOLDER.remove();
    }

}
