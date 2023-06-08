package com.example.demofacebook.Adapter.StudioDetail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demofacebook.Fragment.StudioDetailFragment.StudioFeedbackFragment;
import com.example.demofacebook.Fragment.StudioDetailFragment.StudioGalleryFragment;
import com.example.demofacebook.Fragment.StudioDetailFragment.StudioServiceFragment;
import com.example.demofacebook.Model.Studio;

public class StudioViewPagerAdapter extends FragmentStateAdapter {

    private final Studio studio;

    public StudioViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Studio studio) {
        super(fragmentActivity);
        this.studio = studio;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new StudioServiceFragment(studio);
            case 2:
                return new StudioFeedbackFragment(studio);
            default:
                return new StudioGalleryFragment(studio);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
