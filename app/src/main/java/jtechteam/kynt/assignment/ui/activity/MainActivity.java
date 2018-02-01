package jtechteam.kynt.assignment.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import jtechteam.kynt.assignment.R;
import jtechteam.kynt.assignment.ui.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextFragment( new HomeFragment());
    }


    public void nextFragment(Fragment fragment){
        String name = fragment.getClass().getName();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.myLayout, fragment);
        ft.addToBackStack(name);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        try{
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.myLayout);
            if (fragment instanceof HomeFragment){
                super.onBackPressed();
                System.exit(0);
            }else{
                String name = fragment.getClass().getName();
                getSupportFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }catch (Exception e){
            super.onBackPressed();
            System.exit(0);
        }
    }
}
