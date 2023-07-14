package com.example.demofacebook.Fragment.MainPageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Chat.ChatAdapter;
import com.example.demofacebook.Fragment.Service.PaymentActivity;
import com.example.demofacebook.Model.Message;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment{
    private RecyclerView rcvMessage;
    private EditText editMessage;
    private ChatAdapter messageAdapter;
    private List<Message> sListMessage;

    //zalo Pay in chat
    private Button btnZaloPay;

    private WebView talkJsUI;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editMessage= view.findViewById(R.id.edit_message);
        Button btnSend = view.findViewById(R.id.btn_send);
        rcvMessage= view.findViewById(R.id.rcv_message);
        //zalo Pay in chat
        Button btnZaloPay = view.findViewById(R.id.btnZaloPayChat);
        initLoadView(view);

       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvMessage.setLayoutManager(linearLayoutManager);

        sListMessage= new ArrayList<>();

        messageAdapter = new ChatAdapter();

        messageAdapter.setData(sListMessage);
        rcvMessage.setAdapter(messageAdapter);


        //bắt sự kiện send chat
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });*/

       /* btnZaloPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickPayZalo();
            }
        });*/

    }
    private void initLoadView(View view){
        talkJsUI = view.findViewById(R.id.talkjs);
        talkJsUI.getSettings().setJavaScriptEnabled(true); // Cho phép chạy mã JavaScript
        talkJsUI.loadUrl("file:///android_asset/talkjs.html");

        //layoutTop= view.findViewById(R.id.layout_top_chat);
        //layoutBottom = view.findViewById(R.id.layout_bottom_chat);
        //editMessage= view.findViewById(R.id.edit_message);
        //btnSend= view.findViewById(R.id.btn_send);
        //rcvMessage= getActivity().findViewById(R.id.rcv_message);
        //btnZaloPay = view.findViewById(R.id.btnZaloPayChat);
    }

    private void OnClickPayZalo(){
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putSerializable("order", order); //gửi dữ liệu qua payment
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //hàm xử lý send chat
    private void sendMessage() {

        String strMessage = editMessage.getText().toString().trim();
        if(TextUtils.isEmpty(strMessage)){
            return;
        }
        sListMessage.add(new Message(strMessage));
        messageAdapter.notifyDataSetChanged();
        rcvMessage.scrollToPosition(sListMessage.size()-1); //hiển thị tin nhắn cuối cùng
        editMessage.setText("");
    }//endSendMessage

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container , false);
        return view;
    }
}