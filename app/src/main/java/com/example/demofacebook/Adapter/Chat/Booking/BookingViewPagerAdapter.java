package com.example.demofacebook.Adapter.Chat.Booking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.CanceledFragment;
import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.CompletedFragment;
import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.ScheduledFragment;

public class BookingViewPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_TABS = 3;

    public BookingViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ScheduledFragment();
            case 1:
                return new CompletedFragment();
            case 2:
                return new CanceledFragment();
            default:
                return new ScheduledFragment();
        }
    }
    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
