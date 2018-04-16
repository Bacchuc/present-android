package com.larry.present.common.util;

import android.telephony.TelephonyManager;

import android.content.Context;
import com.larry.present.common.context.AppContext;
import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * Created by Laiyin on 2017/8/27.
 * <p>
 * 获取用户设备的唯一标识符
 */
public class GetUniqueNameUtil {

    /**
     * 获取手机mac地址
     * @return
     */
    public static String getMacAddress() {
        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }

    /**
     * 获取手机的IMEI号码
     * @return
     */
    public static String getPhoneIMEI() {
        TelephonyManager mTm = (TelephonyManager) AppContext.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();
        String imsi = mTm.getSubscriberId();
        String mtype = android.os.Build.MODEL; // 手机型号
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        return imei;
    }

    /**
     * 获取设备唯一标识符
     * @return
     */
    public static String getUniqueName(){
        if(getPhoneIMEI().equals(" "))
            return getMacAddress();

        return getPhoneIMEI();
    }
}
