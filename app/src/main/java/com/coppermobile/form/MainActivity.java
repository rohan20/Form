package com.coppermobile.form;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: 07-Jul-16 --> Check naming of arraylists (suffix List to be added) and other namings of views and ids
        // TODO: 07-Jul-16 --> Remove all hardcoded values

        ButterKnife.bind(this);
        setTitle("IIT Admission Portal");

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        switchFragments(new HomeFragment());

    }

    public void switchFragments(Fragment targetFragment) {
        switchFragments(targetFragment, null);
    }

    public void switchFragments(Fragment targetFragment, Bundle bundle) {
        switchFragments(targetFragment, false, bundle);
    }

    public void switchFragments(Fragment targetFragment, boolean addToBackStack, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (bundle != null) {
            targetFragment.setArguments(bundle);
        }
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.frame_layout_for_fragments, targetFragment);
        fragmentTransaction.commit();
    }
}
