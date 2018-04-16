package com.larry.present.common.util;

import android.content.Context;
import android.net.wifi.ScanResult;

import com.larry.present.config.Constants;

import java.util.ArrayList;
import java.util.List;

/*
*    
* 项目名称：present-android      
* 类描述： wifi 签到工具类
* 创建人：Larry-sea   
* 创建时间：2017/4/24 21:01   
* 修改人：Larry-sea  
* 修改时间：2017/4/24 21:01   
* 修改备注：   
* @version    
*    
*/
public class WifiSignUtil {

    private static WifiAdmin mWifiAdmin;


    private final static String APP_TAG = "MD";

    private static List<String> wifiList;

    /**
     * 检查周围是否有满足条件的wifi信号
     * 使用场景：学生开始签到，检查周围是否满足的点名wifi信号
     *
     * @return
     */
    public static List<String> checkHasWifiHost(Context context) {
        if (wifiList == null) {
            wifiList = new ArrayList<>();
        } else {
            wifiList.clear();
        }

        if (mWifiAdmin == null) {
            mWifiAdmin = new WifiAdmin(context);

        }
        mWifiAdmin.startScan();
        List<ScanResult> scanResults = mWifiAdmin.getWifiList();
        //扫描的wifi列表不为空才继续执行
        if (scanResults != null) {
            for (ScanResult scanResult : scanResults) {
                //检查是否是老师签到点名的wifi
                if (scanResult.SSID.length() == Constants.WIFI_SIGN_BSSID_LENGTH) {
                    if (scanResult.SSID.substring(0, 2).equals(APP_TAG)) {
                        //检查wifi名称中的mac地址是否和扫描到的wifi地址一样
                        if (scanResult.SSID.substring(2, 5).equals(mWifiAdmin.getLastThrMac(scanResult.BSSID))) {
                            wifiList.add(scanResult.SSID.substring(5));
                        }
                    }
                }

            }
            //如果有符合条件的wifi则返回这个wifi列表
            if (wifiList != null && wifiList.size() > 0) {
                return wifiList;
            }
            //如果没有搜索到符合条件的wifi则返回空
            else {
                return null;
            }
        }
        //没有扫描到任何wifi信号返回为空
        return null;

    }


    /**
     * 打开签到点名的wifi列表
     * <p>
     * 使用场景：老师开始发起签到
     * wifi名称创建规则：MD+lastThreeMac+startSignId
     * MD是应用的英文缩写
     * lastThreeMac是老师的设备后三位地址
     * startSignId  发起签到的id
     * <p>
     * 默认密码是  god_war_peace_love
     *
     * @param context      设备上下文
     * @param startSignId  开始签到的id
     * @param lastThreeMac 老师设备的mac地址后三位
     */
    public static void openSignWifiHost(Context context, String startSignId, String lastThreeMac) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MD");
        stringBuilder.append(lastThreeMac);
        stringBuilder.append(startSignId);
        WifiOpenUtil.openWifi(context, stringBuilder.toString(), "god_war_peace_love");

    }


}
