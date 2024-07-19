package com.mijiu.commom.util;

import com.mijiu.entity.Administrator;
import com.mijiu.entity.User;

/**
 * @author mijiupro
 */
public class UserHolder {
    private static final ThreadLocal<User> TOKEN_HOLDER = new ThreadLocal<>();

    public static void setInfoByToken( User user) {
        TOKEN_HOLDER.set( user);
    }

    public static User getInfoByToken() {
        return TOKEN_HOLDER.get();
    }

    public static void clear() {
        TOKEN_HOLDER.remove();
    }

}
