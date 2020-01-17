package com.example.myassignmentid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myassignmentid.R;

public class MainActivity extends AppCompatActivity implements ListDataFragment.PassTitle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addingFragment();
    }

    // adding fragment in to an activity
    private void addingFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListDataFragment mListDataFragment = new ListDataFragment();
        fragmentTransaction.replace(R.id.mFrameLayout, mListDataFragment);
        fragmentTransaction.commit();
    }

    // via interface updating the title in a action bar
    @Override
    public void passdata(String title) {
        setTitle(title);
    }
}
