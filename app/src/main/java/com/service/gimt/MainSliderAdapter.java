package com.service.gimt;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("https://firebasestorage.googleapis.com/v0/b/gimt-sms.appspot.com/o/banner%2Fcampus.jpg?alt=media&token=ce3504f7-8c16-42ab-a880-8ac700535ca0");
                break;
            case 1:
                viewHolder.bindImageSlide("https://firebasestorage.googleapis.com/v0/b/gimt-sms.appspot.com/o/banner%2Fcampus0.jpg?alt=media&token=39860c10-bc08-42d7-96b0-dc88e5a9a3e3");
                break;
            case 2:
                viewHolder.bindImageSlide("https://firebasestorage.googleapis.com/v0/b/gimt-sms.appspot.com/o/banner%2F55.jpg?alt=media&token=7ad5e20d-37ec-47e6-8a0f-c8b8cb9219e2");
                break;
        }
    }

}

