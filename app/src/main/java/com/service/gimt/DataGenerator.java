package com.service.gimt;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.content.res.AppCompatResources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("ResourceType")
public class DataGenerator {

    public static List<HomeCategoryData> getHomeCategory(Context ctx) {
        List<HomeCategoryData> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.home_category_icon);
        String title_arr[] = ctx.getResources().getStringArray(R.array.home_category_title);
        for (int i = 0; i < drw_arr.length(); i++) {
            HomeCategoryData obj = new HomeCategoryData();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.title = title_arr[i];
            obj.imageDrw = AppCompatResources.getDrawable(ctx, obj.image);
            items.add(obj);
        }
        return items;
    }

    public static List<HomeCategoryData> getHomeCategoryT(Context ctx) {
        List<HomeCategoryData> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.home_category_icon_t);
        String title_arr[] = ctx.getResources().getStringArray(R.array.home_category_title_t);
        for (int i = 0; i < drw_arr.length(); i++) {
            HomeCategoryData obj = new HomeCategoryData();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.title = title_arr[i];
            obj.imageDrw = AppCompatResources.getDrawable(ctx, obj.image);
            items.add(obj);
        }
        return items;
    }

}
