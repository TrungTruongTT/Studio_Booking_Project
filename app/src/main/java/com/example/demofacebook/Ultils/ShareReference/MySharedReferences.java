package com.example.demofacebook.Ultils.ShareReference;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedReferences {
    private static final String MY_SHARED_REFERENCES = "MY_SHARED_REFERENCES";
    private Context mcontext;

    public MySharedReferences(Context context) {
        this.mcontext = context;
    }

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
}
