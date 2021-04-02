package com.example.TodoListApp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.TodoListApp.data.TodoRepository;
import com.example.TodoListApp.model.ETodo;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository tdlTodoRepository;
    private LiveData<List<ETodo>> tdlAllTodos;

    public TodoViewModel(@NonNull Application application) {
        super(application);

        tdlTodoRepository = new TodoRepository(application);
        tdlAllTodos = tdlTodoRepository.getTdlAllTodoList();
    }
    public void insert(ETodo todo){
        tdlTodoRepository.insert(todo);
    }
    public void update(ETodo todo){
        tdlTodoRepository.update(todo);
    }
    public LiveData<List<ETodo>> getAllTodos() {
        return tdlAllTodos;
    }
    public ETodo getTodoById(int id){
        return tdlTodoRepository.getTodoById(id);
    }

    public void deleteById(ETodo todo){
        tdlTodoRepository.deleteById(todo);
    }
    public void deleteAll(){
        tdlTodoRepository.deleteAll();
    }
    public void deleteCompleted(){
        tdlTodoRepository.deleteCompleted();
    }
}
