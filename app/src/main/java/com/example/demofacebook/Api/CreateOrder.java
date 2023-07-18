package com.example.demofacebook.Api;


import com.example.demofacebook.Ultils.AppInfo;
import com.example.demofacebook.Ultils.Helper.Helpers;

import org.json.JSONObject;

import java.util.Date;

import okhttp3.FormBody;
import okhttp3.RequestBody;




// dành cho zalo
public class CreateOrder {
    //model tạo đơn hàng ZaloPay
    private class CreateOrderData {
        //554
        String AppId;
        //	Thông tin người dùng như: id/username/tên/số điện thoại/email của user.
        String AppUser;
        //Thời gian tạo đơn hàng (unix timestamp in milisecond).
        String AppTime;
        //Giá trị của đơn hàng
        String Amount;
        //Mã giao dịch của đơn hàng. Mã giao dịch phải bắt đầu theo format yymmdd của ngày hiện tại.
        // Mã giao dịch nên theo format yymmdd_Mã đơn hàng thanh toán
        String AppTransId;
        //	Dữ liệu riêng của đơn hàng.
        //	Dữ liệu này sẽ được callback lại cho AppServer khi thanh toán thành công
        String EmbedData;
        //Item của đơn hàng, do ứng dụng tự định nghĩa
        String Items;
        /*Mã ngân hàng*/
        String BankCode;
        /*Thông tin mô tả về dịch vụ đang được thanh toán dùng để hiển thị cho user trên app ZaloPay và trên tool quản lý Merchant*/
        String Description;
        String Mac;

        private CreateOrderData(String amount) throws Exception {
            long appTime = new Date().getTime();
            AppId = String.valueOf(AppInfo.APP_ID);
            AppUser = "Android_Demo";
            AppTime = String.valueOf(appTime);
            Amount = amount;
            AppTransId = Helpers.getAppTransId();
            EmbedData = "{}";
            Items = "[]";
            BankCode = "ZaloPay";
            Description = "Merchant pay for order #" + Helpers.getAppTransId();
            String inputHMac = String.format("%s|%s|%s|%s|%s|%s|%s",
                    this.AppId,
                    this.AppTransId,
                    this.AppUser,
                    this.Amount,
                    this.AppTime,
                    this.EmbedData,
                    this.Items);

            Mac = Helpers.getMac(AppInfo.MAC_KEY, inputHMac);
        }
    }

     public JSONObject createOrder(String amount) throws Exception {
        CreateOrderData input = new CreateOrderData(amount);

        RequestBody formBody = new FormBody.Builder()
                .add("appid", input.AppId)
                .add("appuser", input.AppUser)
                .add("apptime", input.AppTime)
                .add("amount", input.Amount)
                .add("apptransid", input.AppTransId)
                .add("embeddata", input.EmbedData)
                .add("item", input.Items)
                .add("bankcode", input.BankCode)
                .add("description", input.Description)
                .add("mac", input.Mac)
                .build();

        JSONObject data = HttpProvider.sendPost(AppInfo.URL_CREATE_ORDER, formBody);
        return data;
    }
}

