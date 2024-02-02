package com.example.mycollegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case 2131231221:
                        // schedule
                        fragment = new ScheduleFragment();
                        break;
                    case 2131231219:
                        // assignments
                        fragment = new AssignmentsFragment();
                        break;
                    case 2131231220:
                        // exams
                        fragment = new ExamFragment();
                        break;
                    case 2131231222:
                        // todo list
                        fragment = new TodoFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
                return true;
            }
        });

    }
}