package com.example.arsalankhan.imagesliderdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
private ViewPager viewPager;
    int page=0;
    int counter=0;
    ImageView[] dots;
    LinearLayout linearLayout;
    private MyPagerAdapter adapter;

    // for Auto Sliding

    final Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if(adapter.getCount()==page){
                page=0;
            }else{
                viewPager.setCurrentItem(page++);
            }
            handler.postDelayed(this,1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        adapter = new MyPagerAdapter(this);
        viewPager.setAdapter(adapter);
        counter= adapter.getCount();

        linearLayout= (LinearLayout) findViewById(R.id.dotPanel);

        handler.postDelayed(runnable,1000);

        dots=new ImageView[counter];

        for (int i=0;i<counter;i++){
            dots[i]=new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.unactive_dot));
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            linearLayout.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i <counter; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.unactive_dot));
                }
//                if(position<counter)
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        }
    }

