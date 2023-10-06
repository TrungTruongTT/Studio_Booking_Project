package com.example.demofacebook.Ultils.ShareReference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.TokenResponse;
import com.google.gson.Gson;

import java.util.Set;

public class DataLocalManager {
    //Biến DỮ Liệu
    private static final String PREF_FIRST_INSTALL ="PREF_FIRST_INSTALL";
    private static final String PREF_NAME_USER ="PREF_NAME_USER";

    private static final String PREF_TOKEN_RESPONSE="PREF_TOKEN_RESPONSE";
    private static final String PREF_CUSTOMER_ACCOUNT="PREF_CUSTOMER_ACCOUNT";
    private static DataLocalManager instance;
    private MySharedReferences mySharedReferences;
    // constructor xử lý cho APP
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

    //hàm xử lý
    //SET BIẾn Cài đặt đầu tiền vào PREF_FIRST_INSTALL
    public static void setFirstInstalled(boolean isFirst){
        DataLocalManager.getInstance().mySharedReferences.putBooleanValue(PREF_FIRST_INSTALL,isFirst);
    }
    public static boolean getFirstInstalled(){
        return DataLocalManager.getInstance().mySharedReferences.getBooleanValue(PREF_FIRST_INSTALL);
    }

    //setlist name vào biến PREF_NAME_USER
    public static void setNameUserInstalled(Set<String> nameUser){
        DataLocalManager.getInstance().mySharedReferences.putStringSetValue(PREF_NAME_USER,nameUser);
    }
    public static Set<String> getNameUserInstalled(){
        return DataLocalManager.getInstance().mySharedReferences.getStringSetValue(PREF_NAME_USER);
    }
    //lưu token
    public static void setTokenResponse(TokenResponse token){
        Gson gson = new Gson();
        String strJsonTokenResponse = gson.toJson(token);
        DataLocalManager.getInstance().mySharedReferences.putStringValue(PREF_TOKEN_RESPONSE,strJsonTokenResponse);
    }

    public static TokenResponse getTokenResponse(){
        String strTokenRes= DataLocalManager.getInstance().mySharedReferences.getStringValue(PREF_TOKEN_RESPONSE);
        Gson gson = new Gson();
        TokenResponse token = gson.fromJson(strTokenRes, TokenResponse.class);
        return token;
    }

    public static void setCustomerAccount(CustomerAccount account){
        Gson gson = new Gson();
        String strJsonTokenResponse = gson.toJson(account);
        DataLocalManager.getInstance().mySharedReferences.putStringValue(PREF_CUSTOMER_ACCOUNT,strJsonTokenResponse);
    }

    public static CustomerAccount getCustomerAccount(){
        String strTokenRes= DataLocalManager.getInstance().mySharedReferences.getStringValue(PREF_CUSTOMER_ACCOUNT);
        Gson gson = new Gson();
        CustomerAccount account = gson.fromJson(strTokenRes, CustomerAccount.class);
        return account;
    }
}
