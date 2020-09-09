package com.test.common;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;

public class LiveDataBus {


    private static LiveDataBus liveDataBus = new LiveDataBus();

    public static LiveDataBus getInstance(){
        return  liveDataBus;
    }

    private Map<String, MutableLiveData<Object>> map;
    private LiveDataBus() {
        map = new HashMap<>();
    }

    public synchronized <T> MutableLiveData<T> with(String key, Class<T> tClass) {
        if (!map.containsKey(key)) {
            map.put(key, new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) map.get(key);
    }

}
