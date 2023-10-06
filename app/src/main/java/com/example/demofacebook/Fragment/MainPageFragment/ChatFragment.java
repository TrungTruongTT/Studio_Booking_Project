package com.example.demofacebook.Fragment.MainPageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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


import com.example.demofacebook.Fragment.Service.PaymentActivity;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.Message;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.Model.TalkjsModel;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {
    private String javatalkjs = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>TalkJS Demo</title>\n" +
            "    <script src=\"https://cdn.talkjs.com/talk.js\"></script>\n" +
            "    <meta\n" +
            "            name=\"viewport\"\n" +
            "            content=\"width=device-width, initial-scale=1, maximum-scale=1\"\n" +
            "    />\n" +
            "</head>\n" +
            "<body>\n" +
            "<div id=\"talkjs-container\" style=\"width: 100%; margin:10px; height: 100vh\"> </div>\n" +
            "\n" +
            "<script type=\"text/javascript\">\n" +
            "    (function (t, a, l, k, j, s) {\n" +
            "        s = a.createElement('script');\n" +
            "        s.async = 1;\n" +
            "        s.src = \"https://cdn.talkjs.com/talk.js\";\n" +
            "        a.head.appendChild(s);\n" +
            "        k = t.Promise;\n" +
            "        t.Talk = {\n" +
            "            v: 3,\n" +
            "            ready: {\n" +
            "                then: function (f) {\n" +
            "                    if (k) return new k(function (r, e) {\n" +
            "                        l.push([f, r, e])\n" +
            "                    });\n" +
            "                    l.push([f]);\n" +
            "                },\n" +
            "                catch: function () {\n" +
            "                    return k && new k();\n" +
            "                },\n" +
            "                c: l\n" +
            "            }\n" +
            "        };\n" +
            "    })(window, document, []);\n" +
            "\n" +
            "    Talk.ready.then(function () {\n" +
            "        var me = new Talk.User({\n" +
            "            id: '123456',\n" +
            "            name: 'Alice',\n" +
            "            email: 'alice@example.com',\n" +
            "            photoUrl: 'https://talkjs.com/images/avatar-1.jpg',\n" +
            "            welcomeMessage: 'Hey there! How are you? :-)',\n" +
            "        });\n" +
            "\n" +
            "        window.talkSession = new Talk.Session({\n" +
            "            appId: 'tQ6S3FD4', // APPID\n" +
            "            me: me,\n" +
            "        });\n" +
            "        var other = new Talk.User({\n" +
            "                    id: '654321',\n" +
            "                    name: 'Sebastian',\n" +
            "                    email: 'Sebastian@example.com',\n" +
            "                    photoUrl: 'https://talkjs.com/images/avatar-5.jpg',\n" +
            "                    welcomeMessage: 'Hey, how can I help?',\n" +
            "                });\n" +
            "        // Tạo một conversation mới từ TalkJS\n" +
            "        var conversation = talkSession.getOrCreateConversation(\n" +
            "            Talk.oneOnOneId(me, other)\n" +
            "        );\n" +
            "        conversation.setParticipant(me);\n" +
            "        conversation.setParticipant(other);\n" +
            "        // Tạo inbox\n" +
            "        var inbox = talkSession.createInbox({ selected: conversation });\n" +
            "        inbox.mount(document.getElementById('talkjs-container'));\n" +
            "\n" +
            "        // Lấy danh sách conversation từ tài khoản me (user)\n" +
            "        talkSession.getOrCreateConversation(me).getOrCompose().then(function (chat) {\n" +
            "            var conversationList = chat.conversations();\n" +
            "            conversationList.forEach(function (conversation) {\n" +
            "                // Truy cập thông tin cuộc trò chuyện và thực hiện hành động tương ứng\n" +
            "                var conversationId = conversation.id;\n" +
            "                var participants = conversation.participants;\n" +
            "                // ... Thực hiện các hành động khác với thông tin cuộc trò chuyện\n" +
            "            });\n" +
            "        });\n" +
            "    });\n" +
            "</script>\n" +
            "</body>\n" +
            "</html>\n";


    //zalo Pay in chat
    private Button btnZaloPay;
    private Studio studio;
    private Service service;
    private CustomerAccount account;
    private WebView talkJsUI;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* editMessage= view.findViewById(R.id.edit_message);
        Button btnSend = view.findViewById(R.id.btn_send);
        rcvMessage= view.findViewById(R.id.rcv_message);*/
        //zalo Pay in chat
        //Button btnZaloPay = view.findViewById(R.id.btnZaloPayChat);
        studio = loadStudio();
        service = loadService();
        initLoadView(view);
        // Nhận Studio ID từ Bundle


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

    private void initLoadView(View view) {
        talkJsUI = view.findViewById(R.id.talkjs);
        account = DataLocalManager.getCustomerAccount();
        TalkjsModel meModel = new TalkjsModel(account.getUser().getUserId(), account.getUser().getFullName(),account.getUser().getEmail() ,account.getUser().getImage(),"HELLO!!");
        Gson gson = new Gson();
        String me = gson.toJson(meModel);
        // đổi từ base gốc
        String modifiedHtml = javatalkjs.replace("var me = new Talk.User({\n" +
                "            id: '123456',\n" +
                "            name: 'Alice',\n" +
                "            email: 'alice@example.com',\n" +
                "            photoUrl: 'https://talkjs.com/images/avatar-1.jpg',\n" +
                "            welcomeMessage: 'Hey there! How are you? :-)',\n" +
                "        });", "var me = new Talk.User(JSON.parse('" + me + "'));");
        // Sử dụng dữ liệu Studio nhận được
        if (studio != null && account !=null) {
            TalkjsModel otherModel = new TalkjsModel(studio.getStudioId(), studio.getTitle(), studio.getAddress_Studio(), studio.getCoverImage(), "Can I Help You ??");
            String other = gson.toJson(otherModel);
            //đổi tiếp lần 2
            modifiedHtml = modifiedHtml.replace("var other = new Talk.User({\n" +
                    "                    id: '654321',\n" +
                    "                    name: 'Sebastian',\n" +
                    "                    email: 'Sebastian@example.com',\n" +
                    "                    photoUrl: 'https://talkjs.com/images/avatar-5.jpg',\n" +
                    "                    welcomeMessage: 'Hey, how can I help?',\n" +
                    "                });", "var other = new Talk.User(JSON.parse('" + other + "'));");
            talkJsUI.getSettings().setJavaScriptEnabled(true);
            Log.d("ModifiedHtml", modifiedHtml);
            talkJsUI.loadData(modifiedHtml, "text/html", "utf-8");
        } else {
            //nếu cả 2 đều null load base gốc
            talkJsUI.getSettings().setJavaScriptEnabled(true);
            Log.d("ModifiedHtml", modifiedHtml);
            talkJsUI.loadData(modifiedHtml, "text/html", "utf-8");
        }


//        btnZaloPay = view.findViewById(R.id.btnZaloPayChat);
    }

    private void OnClickPayZalo() {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service); //gửi dữ liệu qua payment
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        return view;
    }

    private Studio loadStudio() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("studio")) {
            Studio studio = (Studio) bundle.getSerializable("studio");
            return studio;
        } else {
            return null;
        }
    }

    private Service loadService(){
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("service")) {
            Service service = (Service) bundle.getSerializable("service");
            return service;
        } else {
            return null;
        }
    }
}