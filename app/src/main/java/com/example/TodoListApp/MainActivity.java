package com.example.TodoListApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.TodoListApp.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fbtn_add;
    Fragment tdlFragment;
    FragmentManager tdlFragmentManager;
    AlertDialog.Builder tdlAlterDialog;
    TodoViewModel tdlTodoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tdlFragment =new TodoListFragment();
        tdlFragmentManager =getSupportFragmentManager();
        tdlFragmentManager.beginTransaction()
                .add(R.id.list_container, tdlFragment)
                .commit();
        fbtn_add = findViewById(R.id.fbtn_main_add);
        fbtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditTodoActivity.class);
                startActivity(intent);
            }
        });
        tdlTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_delete_all:
                tdlAlterDialog = new AlertDialog.Builder(this);
                tdlAlterDialog.setMessage("Are you sure want to delete all??")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name))
                        .setIcon(R.mipmap.ic_launcher);
                tdlAlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tdlTodoViewModel.deleteAll();
                    }
                });
                tdlAlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                tdlAlterDialog.show();
            break;
            case R.id.menu_logout:
                tdlAlterDialog = new AlertDialog.Builder(this);
                tdlAlterDialog.setMessage("Are you sure want to logout??")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name))
                        .setIcon(R.mipmap.ic_launcher);
                tdlAlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.commit();
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                tdlAlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                tdlAlterDialog.show();
                break;

            case R.id.delete_completed:
                tdlAlterDialog = new AlertDialog.Builder(this);
                tdlAlterDialog.setMessage("Are you sure want to delete completed task??")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name))
                        .setIcon(R.mipmap.ic_launcher);
                tdlAlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tdlTodoViewModel.deleteCompleted();
                    }
                });
                tdlAlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                tdlAlterDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
