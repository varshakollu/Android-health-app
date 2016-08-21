package com.example.varsha.navtry;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FifthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fifth_layout, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(adapter);
        return view;

    }

    public class ImageAdapter extends PagerAdapter
    {
        Context context;
        private int[] GalImages = new int[] {
                R.drawable.one, R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,
                R.drawable.six, R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten,
                R.drawable.eleven, R.drawable.twelve,R.drawable.thirteen, R.drawable.fourteen, R.drawable.fifteen,
                R.drawable.sixteen,R.drawable.seventeen, R.drawable.eighteen, R.drawable.ninteen, R.drawable.twenty
        };
        ImageAdapter(Context context)
        {
            this.context=context;
        }
        @Override
        public int getCount() {
            return GalImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(GalImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}

