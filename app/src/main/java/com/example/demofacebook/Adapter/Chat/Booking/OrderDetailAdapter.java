package com.example.demofacebook.Adapter.Chat.Booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackOrderDetailListener;
import com.example.demofacebook.Model.BookingGroupItem;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.SlotBookingItem;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class OrderDetailAdapter extends BaseExpandableListAdapter {
    private List<BookingGroupItem> groupItems;
    private Map<BookingGroupItem, List<SlotBookingItem>> bookingItems;
    private IClickItemFeedbackOrderDetailListener iClickItemFeedbackOrderDetailListener;
    private Context context;
    private String orderStatus;
    private List<OrderDetail> orderDetailList;

    public OrderDetailAdapter(List<BookingGroupItem> groupItems, Map<BookingGroupItem, List<SlotBookingItem>> bookingItems, IClickItemFeedbackOrderDetailListener iClickItemFeedbackOrderDetailListener, Context context, String orderStatus, List<OrderDetail> orderDetailList) {
        this.groupItems = groupItems;
        this.bookingItems = bookingItems;
        this.iClickItemFeedbackOrderDetailListener = iClickItemFeedbackOrderDetailListener;
        this.context = context;
        this.orderStatus = orderStatus;
        this.orderDetailList = orderDetailList;
    }

    @Override
    public int getGroupCount() {
        if (groupItems != null) {
            return groupItems.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        if (bookingItems != null && groupItems != null) {
            return bookingItems.get(groupItems.get(i)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return groupItems.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return bookingItems.get(groupItems.get(i));
    }

    @Override
    public long getGroupId(int i) {
        BookingGroupItem groupItem = groupItems.get(i);
        return groupItem.getId();
    }

    @Override
    public long getChildId(int i, int i1) {
        SlotBookingItem bookingItem = bookingItems.get(groupItems.get(i)).get(i1);
        return bookingItem.getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_studio_booking_item_main, null);

            ImageView image = view.findViewById(R.id.booking_Item_StudioImage);
            TextView name = view.findViewById(R.id.booking_Item_StudioName);
            TextView date = view.findViewById(R.id.date_Item_BookingStudio);
            TextView description = view.findViewById(R.id.booking_Item_StudioDescription);


            BookingGroupItem groupItem = groupItems.get(i);
            name.setText(groupItem.getName());
            description.setText(groupItem.getDescription());
            date.setText(groupItem.getDate());
            Picasso.get()
                    .load(groupItem.getImage())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(image);
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_studio_booking_item_2, null);

            TextView time = view.findViewById(R.id.time_Item_OrderDetailStudio);
            Button feedback = view.findViewById(R.id.btn_Feedback);

            SlotBookingItem bookingItem = bookingItems.get(groupItems.get(i)).get(i1);

            if (orderStatus.equals("pending") || orderStatus.equals("cancel")) {
                feedback.setVisibility(View.INVISIBLE);

            } else {
                if (bookingItem.getPostDate() != null || bookingItem.getContent() != null) {
                    feedback.setVisibility(View.INVISIBLE);
                }
                if (bookingItem.getPostDate() == null && bookingItem.getContent() == null) {
                    feedback.setVisibility(View.VISIBLE);
                }
            }


            feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickItemFeedbackOrderDetailListener.onClickItemFeedbackOrderDetail(bookingItem.getId(), feedback);
                }
            });
            time.setText(bookingItem.getTime());
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
