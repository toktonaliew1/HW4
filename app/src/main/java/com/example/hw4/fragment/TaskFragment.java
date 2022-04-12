package com.example.hw4.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.hw4.App;
import com.example.hw4.databinding.TaskFragmentBinding;
import com.example.hw4.model.TaskModel;
import com.example.hw4.adapter.TaskAdapter;

import java.util.List;


public class TaskFragment extends Fragment implements TaskAdapter.Listener {

    private TaskFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = TaskFragmentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
        initAdapter();
    }



    private void initAdapter() {
        App.getApp().getDb().taskDao().getData().observe(getViewLifecycleOwner(), task -> {
            TaskAdapter taskAdapter = new TaskAdapter((List<TaskModel>) task, this::OnLongClick);
            binding.homeRecycler.setAdapter(taskAdapter);
        });
    }

    private void initClickers() {
        binding.openCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateTaskFragment createTaskFragment = new CreateTaskFragment();
                createTaskFragment.show(requireActivity().getSupportFragmentManager(), "");
            }
        });
    }


    @Override
    public void OnLongClick (TaskModel model) {

                String setMessage = "Вы уверены, что хотите удалить?";
                String setTitle= "Удалить";
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(setTitle);
        builder.setMessage(setMessage);
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        App.getApp().getDb().taskDao().delete(model);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }


}