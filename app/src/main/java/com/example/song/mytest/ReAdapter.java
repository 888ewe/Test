package com.example.song.mytest;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2018/11/16.
 */

public class ReAdapter extends  AutoScrollRecyclerView.Adapter<ReAdapter.MyViewHolder> {

    private Activity mContext;
    private List<TestBean> list=new ArrayList<>();

    public ReAdapter(Activity twoActivity) {
        this.mContext=twoActivity;
    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void setDatas(List<TestBean> list1) {
        this.list=list1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false));
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //判断是否设置了监听器
        int newPosition = position % list.size();
        holder.item_text.setText(list.get(newPosition).text);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_text;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int count =  list.size();
        //刚开始进入包含该类的activity时,count为0。就会出现0%0的情况，这会抛出异常，所以我们要在下面做一下判断
        if (count <= 0) {
            count = 1;
        }
        int  newPosition = position % count;
        return super.getItemViewType(newPosition);
    }

}
