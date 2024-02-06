package com.example.mycollegeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AssignmentsIntent extends AppCompatActivity {
    public void onSaveInstanceState (Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_intent);

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

                Intent intent = new Intent(getApplicationContext(), AssignmentsFragment.class);

                if (!courseName.isEmpty()) {
                    intent.putExtra("courseName", courseName);
                } else {
//                    Bundle bundle = getIntent().getExtras();
//                    intent.putExtra("cn", bundle.getString("og_class_name"));
                    intent.putExtra("courseName", "");

                }

                if (!courseTime.isEmpty()) {
                    intent.putExtra("courseTime", courseTime);
                } else {
//                    Bundle bundle = getIntent().getExtras();
//                    intent.putExtra("ct", bundle.getString("og_class_time"));
                    intent.putExtra("courseTime", "");

                }

                if (!courseInstructor.isEmpty()) {
                    intent.putExtra("courseInstructor", courseInstructor);
                } else {
//                    Bundle bundle = getIntent().getExtras();
//                    intent.putExtra("ci", bundle.getString("og_class_instructor"));
                    intent.putExtra("courseInstructor", "");

                }
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}