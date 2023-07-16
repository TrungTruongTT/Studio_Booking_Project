package com.example.demofacebook.Ultils.ShareReference;

import android.content.Context;

import java.util.Set;

public class DataLocalManager {
    //lưu cái gì (DAO)
    private static final String PREF_FIRST_INSTALL ="PREF_FIRST_INSTALL";
    private static final String PREF_NAME_USER ="PREF_NAME_USER";
    private static DataLocalManager instance;
    private MySharedReferences mySharedReferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedReferences = new MySharedReferences(context);
    }

    public static DataLocalManager getInstance(){
        if(instance ==null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFirstInstalled(boolean isFirst){
        DataLocalManager.getInstance().mySharedReferences.putBooleanValue(PREF_FIRST_INSTALL,isFirst);
    }
    public static boolean getFirstInstalled(){
        return DataLocalManager.getInstance().mySharedReferences.getBooleanValue(PREF_FIRST_INSTALL);
    }

    public static void setNameUserInstalled(Set<String> nameUser){
        DataLocalManager.getInstance().mySharedReferences.putStringSetValue(PREF_NAME_USER,nameUser);
    }
    public static Set<String> getNameUserInstalled(){
        return DataLocalManager.getInstance().mySharedReferences.getStringSetValue(PREF_NAME_USER);
    }

}
