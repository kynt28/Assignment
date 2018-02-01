package jtechteam.kynt.assignment.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jtechteam.kynt.assignment.ui.fragment.ThongKeFragment;


public class HomeAdapter extends FragmentPagerAdapter{
    private Context context;


    public HomeAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ThongKeFragment();
            default:
                return new ThongKeFragment();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

}
