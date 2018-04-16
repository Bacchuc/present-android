package com.larry.present.common.context;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.larry.present.common.basetemplate.BaseApplication;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.util.PresentExceptionHandler;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import cn.smssdk.SMSSDK;


/*
*    
* 项目名称：Present      
* 类描述： 应用的设备上下文
* 创建人：Larry-sea   
* 创建时间：2017/4/5 16:31   
* 修改人：Larry-sea  
* 修改时间：2017/4/5 16:31   
* 修改备注：   
* @version    
*    
*/
public class AppContext extends BaseApplication {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void log(Object object){
        Log.e("hanze", Objects.toString(object));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        SMSSDK.initSDK(this, "1cda9753bd63d", "c107a5e6412c2650b64d6185c3dcb292");
        ProgressSubscriber.setProcessException(new PresentExceptionHandler());

    }

    /**
     * 初始化信息
     */
    @Override
    public void initConfigs() {

    }

    /**
     *
     * 初始化腾讯bugly的配置
     *
     */
    private void intBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "9ecfa96fe9", true);
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
