package com.example.hw4.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;
import com.example.hw4.databinding.FragmentOnBoardBinding;
import com.example.hw4.model.BoardModel;
import com.example.hw4.item.ItemListener;
import com.example.hw4.R;
import com.example.hw4.adapter.BoardAdapter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OnBoardFragment extends Fragment implements ItemListener {

    FragmentOnBoardBinding binding;

    SharedPreferences preferences;

    final String FILE_NAME="board_file";
    final String IS_SHOW_KEY="isShow";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chekOnShowBoard();
        initAdapter();
        pagerListener();


        preferences = requireActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        BoardModel boardModel = new BoardModel(1234567890,"описание","privet");

        String jsonInString = new Gson().toJson(boardModel);

        preferences.edit().putString("json", jsonInString).apply();
        preferences.getString("json","");


        try {
            JSONObject mJSONObject = new JSONObject(jsonInString);
            Log.e("ololo","OnViewCreated" +mJSONObject);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void chekOnShowBoard() {
        preferences = requireActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        boolean isShow = preferences.getBoolean(IS_SHOW_KEY, false);
        if (isShow) {
            Navigation.findNavController(requireView()).navigate(R.id.task_fragment);
        }
    }

    private ArrayList<BoardModel> getBoardList() {
        ArrayList<BoardModel> list = new ArrayList<>();
        list.add(new BoardModel(R.drawable.photo1, "Экономь время","дальше"));
        list.add(new BoardModel(R.drawable.photo2, "Достигай целей","дальше"));
        list.add(new BoardModel(R.drawable.photo3, "Развивайся","начинаем"));
        return list;
    }

    private void initAdapter() {
        BoardAdapter adapter = new BoardAdapter(this, getBoardList());
        binding.pager.setAdapter(adapter);
        binding.wormDotsIndicator.setViewPager2(binding.pager);
    }

    private void pagerListener() {

        binding.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void itemClick(){
        if (binding.pager.getCurrentItem()==2){
            preferences = requireActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            preferences.edit().putBoolean(IS_SHOW_KEY,true).apply();
            Navigation.findNavController(requireView()).navigate(R.id.create_task_fragment);

        }
    }
}




