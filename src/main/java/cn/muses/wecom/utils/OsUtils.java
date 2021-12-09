/*
 * Copyright 2021 XYZ Co., Ltd. All Rights Reserved
 */
package cn.muses.wecom.utils;

/**
 * 操作系统判断
 *
 * @author miaoqiang
 * @date 2021/12/7.
 */
public class OsUtils {

    private static String osName = null;

    /**
     * windows操作系统
     *
     * @return
     */
    public static boolean isWindows() {
        if (null == osName) {
            synchronized (OsUtils.class) {
                if (null == osName) {
                    osName = System.getProperty("os.name").toUpperCase();
                }
            }
        }

        return osName.indexOf("WINDOWS") != -1;
    }
}
