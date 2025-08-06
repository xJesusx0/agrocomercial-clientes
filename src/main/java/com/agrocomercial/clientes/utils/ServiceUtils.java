package com.agrocomercial.clientes.utils;

import com.agrocomercial.clientes.models.BaseEntity;

import java.util.List;

public class ServiceUtils {

    private  ServiceUtils() {}

    public static <T extends BaseEntity> Integer getLastId(List<T> list){
        if(list.isEmpty()){
            return 0;
        }

        return list.getLast().getId();
    }


}
