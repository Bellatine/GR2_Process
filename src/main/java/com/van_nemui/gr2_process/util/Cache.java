package com.van_nemui.gr2_process.util;

import com.van_nemui.gr2_process.model.Category;
import com.van_nemui.gr2_process.model.User;

import java.util.List;
import java.util.Map;

public class Cache {
    public static class Users{
        public static Map<String, User> ALLUSERS;
        public static User AUTH_USER;
    }

    public static class Categories{
        public static Map<Integer, Category> ALL_CATEGORIES;
    }
}
