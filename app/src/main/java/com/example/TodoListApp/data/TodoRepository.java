package com.example.TodoListApp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.TodoListApp.model.ETodo;

import java.util.List;

public class TodoRepository {
    private TodoDAO tdlTodoDAO;
    private LiveData<List<ETodo>> tdlAllTodoList;

    public TodoRepository(Application application) {
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        tdlTodoDAO =database.mTodoDAO();
        tdlAllTodoList = tdlTodoDAO.getAllTodos();

    }

    public LiveData<List<ETodo>> getTdlAllTodoList() {
        return tdlAllTodoList;
    }

    public ETodo getTodoById(int id){
        return tdlTodoDAO.getTodoById(id);
    }

    public void insert(ETodo todo){
        new insertTodoAsynchTask(tdlTodoDAO).execute(todo);
    }

    public void deleteAll(){
        new deleteAllTodoAsynchTask(tdlTodoDAO).execute();
    }
    public void update(ETodo todo){
        new updateTodoAsynchTask(tdlTodoDAO).execute(todo);
    }

    public void deleteById(ETodo todo){
        new deleteByIdTodoAsynchTask(tdlTodoDAO).execute(todo);
    }
    public void deleteCompleted(){
        new deleteCompletedTodoAsynchTask(tdlTodoDAO).execute();
    }

    private static class deleteCompletedTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO tdlTodoDAO;
        private deleteCompletedTodoAsynchTask(TodoDAO todoDAO){
            tdlTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {

            tdlTodoDAO.deleteCompleted();
            return null;
        }
    }

    private static class insertTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO tdlTodoDAO;
        private insertTodoAsynchTask(TodoDAO todoDAO){
            tdlTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
           tdlTodoDAO.insert(todos[0]);
            return null;
        }
    }
    private static class updateTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO tdlTodoDAO;
        private updateTodoAsynchTask(TodoDAO todoDAO){
            tdlTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
            tdlTodoDAO.update(todos[0]);
            return null;
        }
    }
    private static class deleteAllTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO tdlTodoDAO;
        private deleteAllTodoAsynchTask(TodoDAO todoDAO){
            tdlTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {

            tdlTodoDAO.deleteAll();
            return null;
        }
    }
    private static class deleteByIdTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO tdlTodoDAO;
        private deleteByIdTodoAsynchTask(TodoDAO todoDAO){
            tdlTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
            tdlTodoDAO.deleteById(todos[0]);
            return null;
        }
    }
}
