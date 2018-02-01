package jtechteam.kynt.assignment.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import jtechteam.kynt.assignment.R;
import jtechteam.kynt.assignment.ui.adapter.ChiAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhoanChiFragment extends Fragment {
    private ImageView imgToolbar;
    private TextView txtToolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public KhoanChiFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoan_chi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupData();
        setupView(view);
        setupWidget();
        attachEvent();
    }

    private void setupData() {

    }

    private void setupView(View view) {
        //---------------ánh xạ viewpager & tablayout
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        //---------------ánh xạ các view trong toolbar
        imgToolbar = view.findViewById(R.id.imgToolbar);
        txtToolbar = view.findViewById(R.id.txtToolBar);
    }

    @SuppressLint("SetTextI18n")
    private void setupWidget() {
        txtToolbar.setText("Khoản chi");
        //-----------------------setup viewpager & tablayout
        viewPager.setAdapter(new ChiAdapter(getChildFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void attachEvent() {
        imgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}
