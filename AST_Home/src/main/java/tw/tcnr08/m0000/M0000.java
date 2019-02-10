package tw.tcnr08.m0000;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class M0000 extends AppCompatActivity
{
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.m0000);
//    }
    ViewPager viewPager;
    SliderViewPagerAdapter adapter;
    LinearLayout sliderDots;
    private int dotCounts;
    private ImageView[] dots;
    private TextView t001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0000);
        setupViewComponent();
    }

    private void setupViewComponent()
    {
        t001 = (TextView)findViewById(R.id.m0000_t001);
        viewPager = findViewById(R.id.viewPager);
        adapter = new SliderViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // 動態調整高度 抓取使用裝置尺寸
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // String px = displayMetrics.widthPixels + " x " +
        // displayMetrics.heightPixels;
        // String dp = displayMetrics.xdpi + " x " + displayMetrics.ydpi;
        // String density = "densityDpi = " + displayMetrics.densityDpi +
        // ", density=" + displayMetrics.density + ", scaledDensity = " +
        // displayMetrics.scaledDensity;
        // myname.setText(px + "\n" + dp + "\n" +density + "\n" +
        // newscrollheight);
        int newviewPagerheight = displayMetrics.heightPixels * 4 / 10; // 設定ScrollView使用尺寸的4/5
        int newviewPagerwidth = displayMetrics.widthPixels * 1 / 12; // 設定ScrollView使用尺寸的4/5
        int newviewPagerheight_tb = displayMetrics.heightPixels * 1 / 60; // 設定ScrollView使用尺寸的4/5
        viewPager.getLayoutParams().height = newviewPagerheight;  // 重定ScrollView大小
        //viewPager.getLayoutParams().width = newviewPagerwidth;  // 重定ScrollView大小
        viewPager.setPadding( newviewPagerwidth , newviewPagerheight_tb , newviewPagerwidth , newviewPagerheight_tb);  //左上右下

        int newtextheight = displayMetrics.heightPixels * 3 / 10; // 設定ScrollView使用尺寸的4/5
        int newtextwidth = displayMetrics.widthPixels * 89 / 100; // 設定ScrollView使用尺寸的4/5
        t001.getLayoutParams().width = newtextwidth;
        t001.getLayoutParams().height = newtextheight;


        sliderDots = findViewById(R.id.SliderDots);


        dotCounts = adapter.getCount();
        dots = new ImageView[dotCounts];

        for(int i=0;i<dotCounts;i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.slidershow_nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 2, 8, 0);
            sliderDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.slidershow_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<dotCounts;i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.slidershow_nonactive_dot));
                }	                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.slidershow_dot));
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }	        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),3000,6000);
    }

    private class MyTimerTask extends TimerTask
    {
        @Override
        public void run() {
            M0000.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }else if(viewPager.getCurrentItem()==3) {
                        viewPager.setCurrentItem(4);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
