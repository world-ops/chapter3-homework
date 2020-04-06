package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Objects;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView animationView;
    private ListView lvItems;
    private AnimatorSet animatorSet;
    private ArrayAdapter<Item> adapterItems;

    /*====================后加=============*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create arraylist from item fixtures
        ArrayList<Item> items = Item.getItems();
        adapterItems = new ArrayAdapter<Item>(Objects.requireNonNull(getActivity()),
                android.R.layout.simple_list_item_activated_1, items);
    }
    /*====================================*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        View view = inflater.inflate(R.layout.fragment_placeholder, container,
                false);
        // Bind adapter to ListView
        lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapterItems);
        lvItems.setAlpha(0.0f);

        animationView = view.findViewById(R.id.loading);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(animationView,"Alpha",1.0f, 0.0f);
                alphaAnimator.setInterpolator(new LinearInterpolator());
                alphaAnimator.setDuration(1000);
                ObjectAnimator alpha2Animator = ObjectAnimator.ofFloat(lvItems,"Alpha",0.0f, 1.0f);
                alphaAnimator.setInterpolator(new LinearInterpolator());
                alphaAnimator.setDuration(1000);
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(alpha2Animator,alphaAnimator);
                animatorSet.start();

            }
        }, 5000);
    }
}
