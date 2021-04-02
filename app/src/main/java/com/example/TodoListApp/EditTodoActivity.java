package com.example.TodoListApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class EditTodoActivity extends AppCompatActivity {

    Fragment tdlFragment;
    FragmentManager tdlFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        tdlFragment =new EditTodoFragment();
        tdlFragmentManager =getSupportFragmentManager();
        tdlFragmentManager.beginTransaction()
                .add(R.id.main_container, tdlFragment)
                .commit();
    }
}