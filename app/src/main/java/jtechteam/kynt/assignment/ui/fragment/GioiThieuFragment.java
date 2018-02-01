package jtechteam.kynt.assignment.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import jtechteam.kynt.assignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GioiThieuFragment extends Fragment {
    private ImageView imgToolbar;
    private TextView txtToolbar;

    public GioiThieuFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
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
        //---------------ánh xạ các view trong toolbar
        txtToolbar = view.findViewById(R.id.txtToolBar);
        imgToolbar = view.findViewById(R.id.imgToolbar);
    }

    @SuppressLint("SetTextI18n")
    private void setupWidget() {
        txtToolbar.setText("Giới thiệu");
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
