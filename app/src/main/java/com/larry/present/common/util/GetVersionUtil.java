package com.larry.present.common.util;

import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.Context;
/**
 * Created by Laiyin on 2017/8/4.
 */

public class GetVersionUtil {

    /**
     * 获取版本号
     * @param context
     * @return
     */
    public static int getPackageVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        int versionNum = 0;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionNum = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionNum;
    }

    /**
     * 获取版本名
     * @param context
     * @return
     */
    public static String getPackageVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        String versionName = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


}
