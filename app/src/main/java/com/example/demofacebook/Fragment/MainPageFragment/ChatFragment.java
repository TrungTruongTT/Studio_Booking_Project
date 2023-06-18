package com.example.demofacebook.Fragment.MainPageFragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Chat.ChatAdapter;
import com.example.demofacebook.Model.Message;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment{



    private RelativeLayout layoutBottom;
   /* private FrameLayout layoutTop;*/
    private RecyclerView rcvMessage;
    private EditText editMessage;
    private Button btnSend;
    private ChatAdapter messageAdapter;
    private List<Message> sListMessage;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*layoutTop.findViewById(R.id.layout_top_chat);*/
        layoutBottom = view.findViewById(R.id.layout_bottom_chat);
        editMessage= view.findViewById(R.id.edit_message);
        btnSend= view.findViewById(R.id.btn_send);
        rcvMessage= getActivity().findViewById(R.id.rcv_message);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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
        });
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