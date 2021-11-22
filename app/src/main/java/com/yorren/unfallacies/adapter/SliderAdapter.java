package com.yorren.unfallacies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.yorren.unfallacies.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.ad_hominem_illust,
            R.drawable.ad_hominem_illust,
            R.drawable.ad_hominem_illust
    };

    public String[] slide_headings = {
            "Ad Hominem",
            "Ad Hominem",
            "Ad Hominem"
    };

    public String[] slide_descs = {
            "Ad hominem adalah logical fallacy atau sesat pikir paling terkenal yang sering muncul karena kurangnya pengetahuan atau keterampilan penalaran.\n" +
                    "\n" +
                    "Dalam kasus ad hominem, mereka mencoba mendiskreditkan argumen lawan dengan mengkritik penampilan atau asal usulnya, yang tentu aja gak ada hubungannya dengan perdebatan tersebut.\n" +
                    "\n",
            "Sebagai contoh, sesat pikir ini pada dasarnya menyerang pribadi seseorang:\n" +
                    "\n" +
                    "Kamu adalah orang yang saya benci, jadi semua yang kamu katakan pasti salah.\n" +
                    "Kamu adalah seorang yang konservatif, jadi kamu pasti salah.\n" +
                    "Kamu adalah seorang sosialis, jadi kamu pasti salah.\n" +
                    "\n",
            "Salah satu kasus paling terkenal mungkin terjadi pada tahun 2019 ketika kaum milenial menciptakan istilah \"OK Boomer\" dalam perselisihan dengan generasi baby boomer. Hal itu mungkin terdengar lucu mengingat mayoritas pengguna internet adalah kaum milenial.\n" +
                    "\n" +
                    "Tapi, hal ini ternyata menjadi \"serangan ad hominem\" terburuk dalam sejarah. Dalam kasus \"OK Boomer,\" sesat pikir itu berbentuk: \"Oke, kamu sudah tua, jadi kamu pasti salah.\""

    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imgIllust);
        TextView slideHeading = (TextView) view.findViewById(R.id.tvTitle);
        TextView slideDesc = (TextView) view.findViewById(R.id.tvDesc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

}
