package jtechteam.kynt.assignment.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import jtechteam.kynt.assignment.R;
import jtechteam.kynt.assignment.ui.adapter.ThuAdapter;
import jtechteam.kynt.assignment.ui.customview.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhoanThuFragment extends Fragment {
    private ImageView imgToolbar;
    private TextView txtToolbar;
    private CustomViewPager viewPager;
    private TabLayout tabLayout;

    public KhoanThuFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoan_thu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupWidget();
        attachEvent();
    }


    private void setupView(View view) {
        //---------------ánh xạ viewpager & tablayout
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        //---------------ánh xạ các view trong toolbar
        txtToolbar = view.findViewById(R.id.txtToolBar);
        imgToolbar = view.findViewById(R.id.imgToolbar);
    }

    @SuppressLint("SetTextI18n")
    private void setupWidget() {
        //-----------------------setup viewpager & tablayout
        viewPager.setAdapter(new ThuAdapter(getChildFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);
        txtToolbar.setText("Khoản thu");
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
