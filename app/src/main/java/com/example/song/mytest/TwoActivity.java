package com.example.song.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

//import rx.Observable;

public class TwoActivity extends AppCompatActivity {

    AutoScrollRecyclerView recyclerView;

    ReAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        recyclerView = (AutoScrollRecyclerView) findViewById(R.id.recyclerview);
        init();
    }

    private void init() {
        final List<TestBean> list1 = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list1.add(new TestBean("a123r++++ saw3 aedac" + i + "   asdqfdf qe"));
        }
        mAdapter = new ReAdapter(this);
        mAdapter.setDatas(list1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
//        //设置滚动
        recyclerView.setSmoothScroll(this,recyclerView);
        //设置间隔时间
//        recyclerView.intervalTime=1;
        //设置动画执行时间
//        recyclerView.runTime=1;

        recyclerView.setAdapter(mAdapter);

Button btn1 = (Button) findViewById(R.id.btn1);
Button btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(TwoActivity.this, MainActivity.class);
              startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.stopAuto();
            }
        });


//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(TwoActivity.this, "ssss"+position+"]]]]///sas", Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        mAdapter.setOnRecyclerViewItemClickListener(new ReAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(TwoActivity.this, "ssss"+position+"]]]]///sas", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


//
    @Override
    protected void onStop() {
        super.onStop();
        recyclerView.stopAuto();
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.startAuto();
    }

}
