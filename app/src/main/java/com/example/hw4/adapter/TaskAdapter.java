package com.example.hw4.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw4.databinding.ItemTaskBinding;
import com.example.hw4.model.TaskModel;
import com.example.hw4.databinding.ItemTaskBinding;
import com.example.hw4.model.TaskModel;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private ItemTaskBinding binding;
    List<TaskModel> list;
    Listener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void delete(TaskModel model) {
        list.remove(model);
        notifyDataSetChanged();
    }

    public TaskAdapter(List<TaskModel> list, Listener listener){
        this.list = list;
        this.listener = listener;

    }


    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class TaskHolder extends RecyclerView.ViewHolder {
        public TaskHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
        }

        public void onBind(TaskModel model) {
            binding.titleTv.setText(model.getTask());
            binding.dateTv.setText(model.getDate());
            binding.repeatTv.setText(model.getRepeat());
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnLongClick(model);
                    return false;
                }
            });
        }
    }

    public interface Listener{
        void OnLongClick(TaskModel model);
    }
}


