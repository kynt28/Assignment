package jtechteam.kynt.assignment.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jtechteam.kynt.assignment.ui.fragment.TabKhoanThuFragment;
import jtechteam.kynt.assignment.ui.fragment.TabLoaiThuFragment;


public class ThuAdapter extends FragmentPagerAdapter{
    private Context context;
    private String tabTitles[] = new String[] { "KHOẢN THU", "LOẠI THU" };


    public ThuAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TabKhoanThuFragment();
            case 1:
                return new TabLoaiThuFragment();
            default:
                return new TabKhoanThuFragment();
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
