package jtechteam.kynt.assignment.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jtechteam.kynt.assignment.R;
import jtechteam.kynt.assignment.bean.KhoanThu;
import jtechteam.kynt.assignment.ui.adapter.TabLoaiThuAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabLoaiThuFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<KhoanThu> datas;
    private TabLoaiThuAdapter adapter;

    public TabLoaiThuFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupData();
        setupView(view);
        setupWidget();
    }

    private void setupData() {
        datas = new ArrayList<>();
        adapter = new TabLoaiThuAdapter(getActivity(), datas);
        fakeData();
    }

    private void setupView(View view) {
        recyclerView = view.findViewById(R.id.rv);
    }

    private void setupWidget() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new TabLoaiThuAdapter(getActivity(), datas);
        recyclerView.setAdapter(adapter);
    }

    private void fakeData() {
        for (int i=0; i<100; i++) {
            datas.add(new KhoanThu(i,
                    "Tiền thưởng tết " +i,
                    "Lorem Ipsum is simply dummy text of the printing " + i,
                    100000,
                    "23/1/2018"));
        }
        adapter.notifyDataSetChanged();
    }

}
