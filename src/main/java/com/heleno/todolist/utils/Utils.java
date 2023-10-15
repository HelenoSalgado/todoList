package com.heleno.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target){

        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
    
    public static String[] getNullPropertyNames(Object source){

        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> empetyNames = new HashSet<>();
        
        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                empetyNames.add(pd.getName());
            }
        }

        String[] result = new String[empetyNames.size()];

        return empetyNames.toArray(result);
    }
}