package com.example.song.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    MarqueeView marqueeView1,marqueeView2,marqueeView3;
    private boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        marqueeView2 = (MarqueeView) findViewById(R.id.marqueeView2);
        marqueeView3 = (MarqueeView) findViewById(R.id.marqueeView3);

        TextView text = (TextView) findViewById(R.id.text);

        final List<String> list1=new ArrayList();
        List<String> list2=new ArrayList();
        List<String> list3=new ArrayList();
        for(int i = 0; i < 10; i++) {
            list1.add("a123r++++  "+i+"   asdqfdf qe");
            list2.add("1wdafgfg++++  "+i+"  ++12234");
            list3.add("saw3++133++  "+i+"  aedac");
        }

        if (marqueeView1.isFlipping()) {
            marqueeView1.stopFlipping();
            marqueeView2.stopFlipping();
            marqueeView3.stopFlipping();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    marqueeView1.startWithList(list1);
                    marqueeView2.startWithList(list1);
                    marqueeView3.startWithList(list1);
                    marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, TextView textView) {
                            isFirst=false;
                            Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                            startActivity(intent);
                            marqueeView1.stopFlipping();
                            marqueeView2.stopFlipping();
                            marqueeView3.stopFlipping();
                            Toast.makeText(MainActivity.this, "position【"+position+"】  text"+textView.getText(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }, 3000);

        } else {

            marqueeView1.startWithList(list1);
            marqueeView2.startWithList(list1);
            marqueeView3.startWithList(list1);
            marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                @Override
                public void onItemClick(int position, TextView textView) {
                    isFirst=false;
                    Toast.makeText(MainActivity.this, "position【"+position+"】  text"+textView.getText(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                    startActivity(intent);
                    marqueeView1.stopFlipping();
                    marqueeView2.stopFlipping();
                    marqueeView3.stopFlipping();
                }
            });
        }



//        marqueeView1.startWithList(list1);
//        marqueeView2.startWithList(list2);
//        marqueeView3.startWithList(list3);
//
//        marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, TextView textView) {
//                Toast.makeText(MainActivity.this, "position【"+position+"】  text"+textView.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        marqueeView2.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, TextView textView) {
//                Toast.makeText(MainActivity.this, "position【"+position+"】  text"+textView.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        marqueeView3.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, TextView textView) {
//                Toast.makeText(MainActivity.this, "position【"+position+"】  text"+textView.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

//
//    @Override
//    public void onStart() {
//        super.onStart();
//        marqueeView1.startFlipping();
//        marqueeView2.startFlipping();
//        marqueeView3.startFlipping();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        marqueeView1.stopFlipping();
//        marqueeView2.stopFlipping();
//        marqueeView3.stopFlipping();
//    }


    @Override
    protected void onResume() {
        super.onResume();
        if(marqueeView1!=null&&!isFirst) {
            marqueeView1.startFlipping();
            marqueeView2.startFlipping();
            marqueeView3.startFlipping();
        }
    }
}
