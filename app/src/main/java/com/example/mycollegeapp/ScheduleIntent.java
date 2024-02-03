package com.example.mycollegeapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScheduleIntent extends AppCompatActivity {
    public void onSaveInstanceState (Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_intent);

        EditText courseNameInput = (EditText) this.findViewById(R.id.courseNameInputId);
        EditText courseTimeInput = (EditText) this.findViewById(R.id.courseTimeInputId);
        EditText courseInstructorInput = (EditText) this.findViewById(R.id.instructorInputId);

        Button saveButton = (Button) this.findViewById(R.id.saveButtonId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = courseNameInput.getText().toString();
                String courseTime = courseTimeInput.getText().toString();
                String courseInstructor = courseInstructorInput.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ScheduleFragment.class);

                if (!courseName.isEmpty()) {
                    intent.putExtra("courseName", courseName);
                } else {
                    Bundle bundle = getIntent().getExtras();
                    intent.putExtra("data", bundle.getString("original_course"));
                }
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}