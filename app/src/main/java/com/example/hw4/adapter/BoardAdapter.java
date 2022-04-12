package com.example.hw4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw4.databinding.ItemBoardBinding;
import com.example.hw4.item.ItemListener;
import com.example.hw4.model.BoardModel;


import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    ItemListener itemListener;

    public BoardAdapter(ItemListener itemListener, ArrayList<BoardModel> list) {
        this.itemListener = itemListener;
        this.list = list;
    }

    ArrayList<BoardModel> list;

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBoardBinding binding =
                ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent , false);

        return new BoardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
     holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        ItemBoardBinding binding;

        public BoardViewHolder(@NonNull ItemBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void onBind(BoardModel boardModel) {
            binding.boardIm.setImageResource(boardModel.getImage());
            binding.descriptionTv.setText(boardModel.getDescription());
            binding.nextBtn.setText(boardModel.getNextBtn());
            binding.nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.itemClick();
                }
            });

        }
    }
}
