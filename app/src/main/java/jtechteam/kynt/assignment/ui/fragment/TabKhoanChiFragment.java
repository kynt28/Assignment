package jtechteam.kynt.assignment.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jtechteam.kynt.assignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabKhoanChiFragment extends Fragment {


    public TabKhoanChiFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_khoan_chi, container, false);
    }

}
