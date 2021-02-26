package com.example.friendsdiary;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

public class TabAccessorAdapter extends FragmentPagerAdapter  {


    public TabAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new ChatFragment();
            case 1:
                return new GroupFragment();
            case 2:
                return new ContactFragment();
            default:
                return null;
        }

    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Chats";
            case 1:
                return "Groups";

            case 2:

                return "Contacts";
            default:
                return null;
        }
    }

}


