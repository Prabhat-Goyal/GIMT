package com.service.gimt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.HashMap;
import java.util.List;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.indicators.IndicatorShape;

public class HomeActivityS extends AppCompatActivity {

    private View parent_view;
    private Slider slider;

    private RecyclerView recyclerView;
    private HomeCategoryAdapterS mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);
        parent_view = findViewById(R.id.parent_view);

        Slider.init(new PicassoImageLoadingService(this));
        setupViews();



        initComponent();
    }


    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<HomeCategoryData> items = DataGenerator.getHomeCategory(this);

        mAdapter = new HomeCategoryAdapterS(this, items);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HomeCategoryAdapterS.OnItemClickListener() {

            @Override
            public void onItemClick(View view, HomeCategoryData obj, int position) {

            }
        });

    }

    private void setupViews(){
                slider = findViewById(R.id.slider);
                slider.setAdapter(new MainSliderAdapter());
                slider.setSelectedSlide(0);
                slider.setInterval(5000);
    }



}
