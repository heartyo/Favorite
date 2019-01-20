package com.lcxyy.mvpproject.mvp;

import java.lang.reflect.ParameterizedType;

public class CreateUtil {
    static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
