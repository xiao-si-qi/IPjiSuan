package com.example.xiaosiqi.ipjisuan;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaos on 2018/3/17.
 */

public class MyAdapter extends BaseAdapter{
   private List<IPclass> z子网列表数据=new ArrayList<>();
   private Context context;
   private LayoutInflater mInflater;

    public MyAdapter(List<IPclass> z子网列表数据, Context context) {
        this.z子网列表数据 = z子网列表数据;
        this.context = context;
    }

    @Override
    public int getCount() {
        return z子网列表数据.size();
    }

    @Override
    public Object getItem(int i) {
        return z子网列表数据.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
        {
            mInflater=LayoutInflater.from(context);
            view=mInflater.inflate(R.layout.line,null);
        }
        IPclass y一行数据=z子网列表数据.get(i);
        TextView tv网络编号= (TextView) view.findViewById(R.id.tv网络编号);
        TextView tv本网网络地址= (TextView) view.findViewById(R.id.tv本网网络地址);
        TextView tv本网第一个主机地址= (TextView) view.findViewById(R.id.tv本网第一个主机地址);
        TextView tv本网最后一个主机地址= (TextView) view.findViewById(R.id.tv本网最后一个主机地址);
        TextView tv本网段广播地址= (TextView) view.findViewById(R.id.tv本网段广播地址);
        tv网络编号.setText(y一行数据.getW网络编号()+"");
        tv本网网络地址.setText(y一行数据.getW网络地址());
        tv本网第一个主机地址.setText(y一行数据.getD第一个主机());
        tv本网最后一个主机地址.setText(y一行数据.getZ最后一个主机());
        tv本网段广播地址.setText(y一行数据.getG广播地址());
        return view;
    }
}
