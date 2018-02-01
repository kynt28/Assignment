package jtechteam.kynt.assignment.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jtechteam.kynt.assignment.R;
import jtechteam.kynt.assignment.ui.activity.MainActivity;
import jtechteam.kynt.assignment.ui.adapter.HomeAdapter;
import jtechteam.kynt.assignment.ui.dialog.ExitDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TextView txtNameProfile , txtIDProfile;

    private DrawerLayout mDrawer;
    private NavigationView navigation;

    public HomeFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupWidgets();
    }


    private void setupView(View view) {
        viewPager = view.findViewById(R.id.view_pager_home);
        //---------------ánh xạ toolbar
        toolbar = view.findViewById(R.id.toolbar);
        //---------------ánh xạ navigation drawer
        navigation = view.findViewById(R.id.nav_view);
        mDrawer = view.findViewById(R.id.drawerLayout);
        //---------------ánh xạ header
        View header = navigation.getHeaderView(0);
        txtNameProfile = header.findViewById(R.id.txtNameProfile);
        txtIDProfile = header.findViewById(R.id.txtIDProfile);
    }

    private void setupWidgets() {
        viewPager.setAdapter(new HomeAdapter(getChildFragmentManager(), getActivity()));
        //-----------------------setup header
        int name = txtNameProfile.getResources().getColor(R.color.white);
        txtNameProfile.setTextColor(name);
        int id = txtIDProfile.getResources().getColor(R.color.white);
        txtIDProfile.setTextColor(id);
        //-----------------------setup toolbar
        ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("Thống kê");
        //-----------------------setup navigations
        ActionBarDrawerToggle drawerToggle = setupDrawerToggle();
        drawerToggle.syncState();
        mDrawer.addDrawerListener(drawerToggle);
        setupDrawerContent(navigation);

    }


    //------------------------------------------navigation drawer-----------------------------------//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.ic_nav_thong_ke:
                ((MainActivity) getActivity()).nextFragment(new HomeFragment());
                break;
            case R.id.ic_nav_thu:
                ((MainActivity) getActivity()).nextFragment(new KhoanThuFragment());
                break;
            case R.id.ic_nav_chi:
                ((MainActivity) getActivity()).nextFragment(new KhoanChiFragment());
                break;
            case R.id.ic_nav_about:
                ((MainActivity) getActivity()).nextFragment(new GioiThieuFragment());
                break;
            case R.id.ic_nav_thoat:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ExitDialogFragment editNameDialogFragment = new ExitDialogFragment();
                editNameDialogFragment.show(fm, "fragment_them_lop_dialog");
                break;
            default:
                ((MainActivity) getActivity()).nextFragment(new HomeFragment());
        }

        menuItem.setChecked(true);
        mDrawer.closeDrawers();


    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(getActivity(),
                mDrawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
    }

}
