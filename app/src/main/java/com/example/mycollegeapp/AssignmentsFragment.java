package com.example.mycollegeapp;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Collections;
import java.util.Comparator;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssignmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignmentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 2110TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Integer index;
    String item;
    int itemCounter;
    String userInputtedCourseName;
    String userInputtedCourseTitle;
    String userInputtedCourseInstructor;


    public AssignmentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssignmentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssignmentsFragment newInstance(String param1, String param2) {
        AssignmentsFragment fragment = new AssignmentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RecyclerView recyclerView;
        MyAdapter2 adapter;
        RecyclerView.LayoutManager layoutManager;


        ArrayList<AssignmentsItem> AssignmentsList = new ArrayList<>();
        AssignmentsList.add(new AssignmentsItem("Math Homework","Wednesday", "3500"));
        AssignmentsList.add(new AssignmentsItem("CS 2340 Homework","Monday", "2340"));
        AssignmentsList.add(new AssignmentsItem("Algebra HW","Monday", "4100"));


        View view = inflater.inflate(R.layout.fragment_assignments, container, false);
        recyclerView = view.findViewById(R.id.rvId);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new MyAdapter2(AssignmentsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


////        ListView listView = view.findViewById(R.id.listViewId);
        Button addButton = view.findViewById(R.id.addBtn);
        Button buttonSort = view.findViewById(R.id.buttonSort);
//        Button updateButton = view.findViewById(R.id.updateBtn);
        EditText userText1 = view.findViewById(R.id.courseNameId);
        EditText userText2 = view.findViewById(R.id.courseTimeInputId);
        EditText userText3 = view.findViewById(R.id.courseInstructorId);

//        ArrayList<String> toDoList = new ArrayList<String>();
//
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, toDoList);
////        listView.setAdapter(adapter);
//
        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    public void onActivityResult(ActivityResult result) {
                        Log.d("userInput", "WE EVEN HERE?!?!?!");

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Log.d("word up", "HOLA");

                            userInputtedCourseName = result.getData().getStringExtra("courseName");
                            userInputtedCourseTitle = result.getData().getStringExtra("courseTime");
                            userInputtedCourseInstructor = result.getData().getStringExtra("courseInstructor");
                            if (userInputtedCourseName.length() == 0 && userInputtedCourseTitle.length() == 0 && userInputtedCourseInstructor.length() == 0) {
                                AssignmentsList.remove(itemCounter);
                            } else {
                                AssignmentsList.set(itemCounter, new AssignmentsItem(userInputtedCourseName, userInputtedCourseTitle, userInputtedCourseInstructor));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        adapter.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), AssignmentsIntent.class);
//                intent.putExtra("og_class_name", AssignmentsList.get(position).getClassName());
//                intent.putExtra("og_class_time", AssignmentsList.get(position).getClassTime());
//                intent.putExtra("og_class_instructor", AssignmentsList.get(position).getClassInstructor());
                itemCounter = position;
                mStartForResult.launch(intent);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput1 = String.valueOf(userText1.getText());
                String userInput2 = String.valueOf(userText2.getText());
                String userInput3 = String.valueOf(userText3.getText());

                AssignmentsList.add(new AssignmentsItem(userInput1, userInput2, userInput3));
                adapter.notifyDataSetChanged();
                userText1.getText().clear();
                userText2.getText().clear();
                userText3.getText().clear();
            }
        });

        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort the AssignmentsList alphabetically based on course name
                Collections.sort(AssignmentsList, new Comparator<AssignmentsItem>() {
                    @Override
                    public int compare(AssignmentsItem o1, AssignmentsItem o2) {
                        return o1.className.compareToIgnoreCase(o2.className);
                    }
                });

                // Notify the adapter that the data set has changed after sorting
                adapter.notifyDataSetChanged();
            }
        });
//
//        AssignmentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                item = String.valueOf(parent.getItemAtPosition(position));
//                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
//                index = position;
//            }
//        });
//
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String userInput = String.valueOf(userText.getText());
//                toDoList.set(index, userInput);
//                adapter.notifyDataSetChanged();
//                userText.getText().clear();
//            }
//        });
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                item = String.valueOf(parent.getItemAtPosition(position));
//                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
//                toDoList.remove(position);
//                adapter.notifyDataSetChanged();
//                return true;
//            }
//        });
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), AssignmentsIntent.class);
//                intent.putExtra("original_course", toDoList.get(position));
//                itemCounter = position;
//                mStartForResult.launch(intent);
//            }
//        });
        return view;
    }
}