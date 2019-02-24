package com.bwie.android.e_commerceproject.app;

import android.content.Context;
import android.text.TextUtils;

import com.bwie.android.lib_core.application.BaseApp;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyApp extends BaseApp {
    public static RefWatcher getRefWatcher(Context context) {
        MyApp application = (MyApp) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
        Fresco.initialize(this);
        RefWatcher refWatcher = LeakCanary.install(this);
        //        RefWatcher refWatcher =  new RefWatcher();
//        refWatcher.watch();
        /*
        第三个参数为SDK调试模式开关，调试模式的行为特性如下：

        输出详细的Bugly SDK的Log；
        每一条Crash都会被立即上报；
        自定义日志将会在Logcat中输出。
        建议在测试阶段建议设置成true，发布时设置为false。*/
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        CrashReport.initCrashReport(context, "17f8a5f1bd", false, strategy);
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
