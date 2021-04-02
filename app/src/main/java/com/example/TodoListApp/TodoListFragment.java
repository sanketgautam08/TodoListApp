package com.example.TodoListApp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.TodoListApp.model.ETodo;
import com.example.TodoListApp.viewmodel.TodoViewModel;

import java.text.SimpleDateFormat;
import java.util.List;


public class TodoListFragment extends Fragment {
    View rootView;
    private TodoViewModel tdlTodoViewModel;
    RecyclerView tdlRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     rootView = inflater.inflate(R.layout.fragment_todo_list, container, false);
        tdlTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        tdlRecyclerView = rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tdlRecyclerView.setLayoutManager(layoutManager);
        updateRV();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                List<ETodo> todoList = tdlTodoViewModel.getAllTodos().getValue();
                TodoAdapter adapter = new TodoAdapter(todoList);
                ETodo todo = adapter.getTodo(viewHolder.getAdapterPosition());
                tdlTodoViewModel.deleteById(todo);

            }
        }).attachToRecyclerView(tdlRecyclerView);
        return rootView;
    }
    void updateRV(){
        tdlTodoViewModel.getAllTodos().observe(this, new Observer<List<ETodo>>() {
            @Override
            public void onChanged(List<ETodo> todoList) {
                TodoAdapter adapter = new TodoAdapter(todoList);
                tdlRecyclerView.setAdapter(adapter);
            }
        });
    }
    private class TodoAdapter extends RecyclerView.Adapter<TodoHolder>{
        List<ETodo> tdlTodoList;
        public TodoAdapter(List<ETodo> todoList){
            tdlTodoList = todoList;
        }
        @NonNull
        @Override
        public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            return new TodoHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
            ETodo todo = tdlTodoList.get(position);
            LinearLayout layout = (LinearLayout)((ViewGroup)holder.tdlTitle.getParent());
            switch (todo.getPriority()){
                case 1:
                    layout.setBackgroundColor(getResources().getColor(R.color.color_high_priority));
                    break;
                case 2:
                    layout.setBackgroundColor(getResources().getColor(R.color.color_medium_priority));
                    break;
                case 3:
                    layout.setBackgroundColor(getResources().getColor(R.color.color_low_priority));
                    break;
            }
            holder.bind(todo);
        }
        @Override
        public int getItemCount() {
            return tdlTodoList.size();
        }
        public ETodo getTodo(int index){
            return tdlTodoList.get(index);
        }

        public ETodo getTodoAt(int index){
            return tdlTodoList.get(index);
        }
    }
    private class TodoHolder extends RecyclerView.ViewHolder{
        TextView tdlTitle, tdlDate, tdlDesprition;
        public TodoHolder(LayoutInflater inflater, ViewGroup parentViewGroup) {
            super(inflater.inflate(R.layout.list_item_todo, parentViewGroup, false));
            tdlTitle = itemView.findViewById(R.id.list_title);
            tdlDate = itemView.findViewById(R.id.list_date);
            tdlDesprition =itemView.findViewById(R.id.list_description);


            tdlTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TodoAdapter adapter = new TodoAdapter(tdlTodoViewModel.getAllTodos().getValue());
                    int position = getAdapterPosition();
                    ETodo eTodo= adapter.getTodoAt(position);
                    Intent intent = new Intent(getActivity(),EditTodoActivity.class);
                    intent.putExtra("TodoId", eTodo.getId());
                    startActivity(intent);
                }
            });
            tdlDesprition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TodoAdapter adapter = new TodoAdapter(tdlTodoViewModel.getAllTodos().getValue());
                    int position = getAdapterPosition();
                    ETodo eTodo= adapter.getTodoAt(position);
                    Intent intent = new Intent(getActivity(),EditTodoActivity.class);
                    intent.putExtra("TodoId", eTodo.getId());
                    startActivity(intent);
                }
            });
            tdlDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TodoAdapter adapter = new TodoAdapter(tdlTodoViewModel.getAllTodos().getValue());
                    int position = getAdapterPosition();
                    ETodo eTodo= adapter.getTodoAt(position);
                    Intent intent = new Intent(getActivity(),EditTodoActivity.class);
                    intent.putExtra("TodoId", eTodo.getId());
                    startActivity(intent);
                }
            });
        }
        public void bind(ETodo todo){
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            tdlTitle.setText(todo.getTitle());
            tdlDesprition.setText(todo.getDescription());
            tdlDate.setText(dateFormatter.format(todo.getTodo_date()));
        }

    }
}