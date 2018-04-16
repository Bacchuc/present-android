package com.larry.present.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Larry-sea on 2016/11/17.
 * <p>
 * 精简的创建wifi热点类
 */

public class APUtil {


    static WifiManager wifiManager;     //wifimanager用来获取wifimanager的服务

    /**
     * 打开wifi热点功能
     *
     * @param wifiName     wifi热点名称
     * @param wifiPassword wifi热点密码
     * @param enabled      设置wifi热点是否打开 true代表打开热点 false代表关闭热点
     */
    static public boolean setApEnabled(Context context, String wifiName, String wifiPassword, boolean enabled) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);       //关闭wifi才能开启热点
        wifiManager.getWifiState();

        try {
            return initAPConfig(wifiName, wifiPassword, enabled);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 初始化wifi热点的配置信息
     *
     * @param wifiName     wifi热点名称
     * @param wifiPassword wifi热点密码
     * @param enabled      设置wifi热点是否打开 true代表打开热点 false代表关闭热点
     */
    static private boolean initAPConfig(String wifiName, String wifiPassword, boolean enabled) throws IllegalAccessException, InvocationTargetException {


        //热点的配置类
        WifiConfiguration netConfig = new WifiConfiguration();

        //获取wifi管理服务

        Method method = null;
        try {
            method = wifiManager.getClass().getMethod("setWifiApEnabled",
                    WifiConfiguration.class, boolean.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        netConfig.SSID = wifiName;
        netConfig.preSharedKey = wifiPassword;
        netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

        try {
            return (Boolean) method.invoke(wifiManager, netConfig, enabled);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 检查wifi热点是否打开
     *
     * @return false 代表无设备链接上热点
     */
    public static Boolean isApEnabled(Context context) {
        try {
            WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            Method method = manager.getClass().getMethod("isApEnabled");
            return (Boolean) method.invoke(manager);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 判断该热点是否有设备连接上
     *
     * @return false   没有设备连接上
     */
    public static boolean isAPConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }





}
