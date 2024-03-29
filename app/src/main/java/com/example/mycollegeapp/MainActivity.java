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
                    case R.id.id_schedule:
                        // schedule
                        fragment = new ScheduleFragment();
                        break;
                    case R.id.id_assignments:
                        // assignments
                        fragment = new AssignmentsFragment();
                        break;
                    case R.id.id_exams:
                        // exams
                        fragment = new ExamFragment();
                        break;
                    case R.id.id_todo:
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