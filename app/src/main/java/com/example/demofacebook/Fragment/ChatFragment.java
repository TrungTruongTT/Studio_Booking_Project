package com.example.demofacebook.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.demofacebook.R;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment{


    private RecyclerView rcvMessage;
    private EditText editMessage;
    private Button btnSend;
    /*private MessageAdapter messageAdapter;*/
    private List<Message> sListMessage;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editMessage.findViewById(R.id.edit_message);
        btnSend.findViewById(R.id.btn_send);
        rcvMessage.findViewById(R.id.rcv_message);


        sListMessage= new ArrayList<>();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container , false);
        return view;
    }
}