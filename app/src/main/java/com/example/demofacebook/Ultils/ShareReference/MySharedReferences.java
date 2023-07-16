package com.example.demofacebook.Ultils.ShareReference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class MySharedReferences {

    //model SharedReferences
    private static final String MY_SHARED_REFERENCES = "MY_SHARED_REFERENCES";
    private Context mcontext;

    public MySharedReferences(Context context) {
        this.mcontext = context;
    }
    //get&set boolean
    public void putBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARED_REFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARED_REFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    //get&set String
    public void putStringValue(String key, String value) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARED_REFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public String getStringValue(String key) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARED_REFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    //get&set List String
    public void putStringSetValue(String key, Set<String> values) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARED_REFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    public Set<String> getStringSetValue(String key) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARED_REFERENCES, Context.MODE_PRIVATE);
        Set<String> valueDefault = new HashSet<>();
        return sharedPreferences.getStringSet(key,valueDefault);
    }


    //get&set Object


}
