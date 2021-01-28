package com.wu.corelib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: SharedPreferences工具类, 可以通过传入实体对象保存其至SharedPreferences中,
 * 并通过实体的类型Class将保存的对象取出. 支持不带泛型的对象以及List集合
 * @Author: wumz
 * @CreateDate: 2019/4/22 10:16
 */
public class PreferencesHelper {

    private static final String LIST_TAG = ".LIST";
    private static SharedPreferences sp;
    private static Gson gson;

    /**
     * 使用之前初始化, 可在Application中调用
     *
     * @param context 请传入ApplicationContext避免内存泄漏
     */
    public static void init(Context context) {
        sp = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
        gson = new Gson();
    }

    private static void checkInit() {
        if (sp == null || gson == null) {
            throw new IllegalStateException("Please call init(context) first.");
        }
    }

    /**
     * 保存对象数据至SharedPreferences, key默认为类名, 如
     * <pre>
     * PreferencesHelper.saveData(saveUser);
     * </pre>
     *
     * @param data 不带泛型的任意数据类型实例
     */
    public static <T> void saveData(T data) {
        saveData(data.getClass().getName(), data);
    }

    /**
     * 根据key保存对象数据至SharedPreferences, 如
     * <pre>
     * PreferencesHelper.saveData(key, saveUser);
     * </pre>
     *
     * @param data 不带泛型的任意数据类型实例
     */
    public static <T> void saveData(String key, T data) {
        checkInit();
        if (data == null)
            throw new IllegalStateException("data should not be null.");
        sp.edit().putString(key, gson.toJson(data)).apply();
    }

    /**
     * 保存List集合数据至SharedPreferences, 请确保List至少含有一个元素, 如
     * <pre>
     * PreferencesHelper.saveData(users);
     * </pre>
     *
     * @param data List类型实例
     */
    public static <T> void saveData(List<T> data) {
        checkInit();
        if (data == null || data.size() <= 0)
            throw new IllegalStateException(
                    "List should not be null or at least contains one element.");
        Class returnType = data.get(0).getClass();
        sp.edit().putString(returnType.getName() + LIST_TAG,
                gson.toJson(data)).apply();
    }

    /**
     * 将数据从SharedPreferences中取出, key默认为类名, 如
     * <pre>
     * User user = PreferencesHelper.getData(key, User.class)
     * </pre>
     */
    public static <T> T getData(Class<T> clz) {
        return getData(clz.getName(), clz);
    }

    /**
     * 根据key将数据从SharedPreferences中取出, 如
     * <pre>
     * User user = PreferencesHelper.getData(User.class)
     * </pre>
     */
    public static <T> T getData(String key, Class<T> clz) {
        checkInit();
        String json = sp.getString(key, "");
        return gson.fromJson(json, clz);
    }

    /**
     * 将数据从SharedPreferences中取出, 如
     * <pre>List&lt;User&gt; users = PreferencesHelper.getData(List.class, User.class)</pre>
     */
    public static <T> List<T> getData(Class<List> clz, Class<T> gClz) {
        checkInit();
        String json = sp.getString(gClz.getName() + LIST_TAG, "");
        return gson.fromJson(json, getClassType(clz, gClz));
    }

    private static ParameterizedType getClassType(final Class<?> raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    /**
     * 简易字符串保存, 仅支持字符串
     */
    public static void saveData(String key, String data) {
        sp.edit().putString(key, data).apply();
    }

    /**
     * 简易字符串获取, 仅支持字符串
     */
    public static String getData(String key) {
        return getData(key, "");
    }

    public static long getLongData(String key) {
        return getData(key, 0L);
    }

    public static String getData(String key, String defValue) {
        checkInit();
        return sp.getString(key, defValue);
    }

    public static long getData(String key, long defValue) {
        checkInit();
        return sp.getLong(key, defValue);
    }

    public static boolean getData(String key, boolean defValue) {
        checkInit();
        return sp.getBoolean(key, defValue);
    }

    public static int getData(String key, int defValue) {
        checkInit();
        return sp.getInt(key, defValue);
    }

    /**
     * 删除保存的对象
     */
    public static void remove(String key) {
        sp.edit().putString(key, "").apply();
    }

    /**
     * 删除保存的对象
     */
    public static void removeInt(String key) {
        sp.edit().putInt(key, 0).apply();
    }

    /**
     * 删除保存的对象
     */
    public static void remove(Class clz) {
        remove(clz.getName());
    }

    /**
     * 删除保存的数组
     */
    public static void removeList(Class clz) {
        sp.edit().putString(clz.getName() + LIST_TAG, "").apply();
    }

    // from mzl
    public static <T> void saveData(Context context, String name, String key, T data) {
        if (data == null) {
            throw new IllegalStateException("data should not be null.");
        }
        context.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(key, gson.toJson(data)).apply();
    }

    public static void remove(Context context, String name, String key) {
        context.getSharedPreferences(name, Context.MODE_PRIVATE).edit().remove(key).apply();
    }

    public static String getData(Context context, String name, String key) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key, "");
    }

    public static <T> T getData(Context context, String name, String key, Class<T> clz) {
        return gson.fromJson(context.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key, ""), clz);
    }

    public static void saveBooleanData(String key, boolean data) {
        sp.edit().putBoolean(key, data).apply();
    }

    public static void saveIntData(String key, int data) {
        sp.edit().putInt(key, data).apply();
    }

    /**
     * 获取某个sp所有key
     *
     * @param context Context
     * @param name    String
     * @return List<String>
     */
    public static List<String> getOneSPAllKey(Context context, String name) {
        if (context == null)
            return null;
        Map<String, ?> map = context.getSharedPreferences(name, Context.MODE_PRIVATE).getAll();
        return new ArrayList<>(map.keySet());
    }
}
