package com.test.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

public class Router {

    private static Router router = new Router();
    private Map<String, Class<? extends Activity>> map;
    private Context context;

    public Router() {
        map = new HashMap<>();
    }

    public static Router getInstance() {
        return router;
    }


    public void init(Context context) {
        this.context = context;
        List<String> className = getClassName("com.test.temp");
        for (String s : className) {
            try {
                Class<?> aClass = Class.forName(s);
                if (IRouter.class.isAssignableFrom(aClass)) {//是否是Irouter实现类
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    public void addActivity(String key, Class<? extends Activity> clazz) {
        if (key != null && clazz != null && !map.containsKey(key)) {
            map.put(key, clazz);
        }
    }

    public void jumpActivity(String key, Bundle bundle) {
        Class<? extends Activity> aClass = map.get(key);
        if (aClass == null) {
            return;
        }
        Intent intent = new Intent(context, aClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    private List<String> getClassName(String packageName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());//apk文件
            Enumeration<String> entries = df.entries();
            while (entries.hasMoreElements()) {
                String className = entries.nextElement();
                if (className.contains(packageName)) {
                    list.add(className);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
