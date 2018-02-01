package jtechteam.kynt.assignment.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jtechteam.kynt.assignment.ui.fragment.TabKhoanChiFragment;
import jtechteam.kynt.assignment.ui.fragment.TabLoaiChiFragment;


public class ChiAdapter extends FragmentPagerAdapter{
    private Context context;
    private String tabTitles[] = new String[] { "KHOẢN CHI", "LOẠI CHI" };


    public ChiAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TabKhoanChiFragment();
            case 1:
                return new TabLoaiChiFragment();
            default:
                return new TabKhoanChiFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
