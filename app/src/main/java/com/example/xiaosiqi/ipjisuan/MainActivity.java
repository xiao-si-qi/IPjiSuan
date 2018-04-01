package com.example.xiaosiqi.ipjisuan;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Int4;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText etIP;
    private EditText etMask;
    private EditText ed要划分网络的个数;
    private EditText ed网络中主机的个数;
    private TextView tv计算过程;
    private TextView tv划分子网后的子网掩码;
    private Button btJisuan;
    private Button bt子网划分;
    private TextView tv二进制IP;
    private TextView tv二进制掩码;
    private TextView tv掩码;
    private TextView tv网络位长度;
    private TextView tv本网网络地址;
    private TextView tv本网第一个主机地址;
    private TextView tv本网最后一个主机地址;
    private TextView tv本网最广播地址;
    private TextView tv本网段可用主机数;
    private TextView tv子网的可用主机数;
    private TextView tv总共划分的网络数;
    private TextView bt归属地;
    private TextView bt说明;
    private TextView bt关于;
    private ImageView btXuanXiang;
    private ListView ls子网列表;
    private Context context = this;
    private String str二进制掩码;
    private String str子网划分后的二进制掩码;
    private String str二进制Ip;
    DrawerLayout mMyDrawable;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    List<IPclass> iPclasses = new ArrayList<>();
    private ProgressDialog d等待框;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyAdapter myAdapter = new MyAdapter(iPclasses, context);
            ls子网列表.setAdapter(myAdapter);
            setListViewHeightBasedOnChildren(ls子网列表);
            d等待框.dismiss();
        }
    };

    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        etIP = findViewById(R.id.etIP);
        etMask = findViewById(R.id.etMask);
        btJisuan = findViewById(R.id.btJiSuan);
        bt子网划分 = findViewById(R.id.bt子网划分);
        tv二进制IP = findViewById(R.id.tv二进制IP);
        tv二进制掩码 = findViewById(R.id.tv二进制掩码);
        tv掩码 = findViewById(R.id.tv掩码);
        tv网络位长度 = findViewById(R.id.tv网络位长度);
        tv本网网络地址 = findViewById(R.id.tv本网网络地址);
        tv本网段可用主机数 = findViewById(R.id.tv本网段可用主机数);
        tv本网第一个主机地址 = findViewById(R.id.tv本网第一个主机地址);
        tv本网最后一个主机地址 = findViewById(R.id.tv本网最后一个主机地址);
        tv本网最广播地址 = findViewById(R.id.tv本网段广播地址);
        tv子网的可用主机数 = findViewById(R.id.tv子网的可用主机数);
        tv总共划分的网络数 = findViewById(R.id.tv总共划分的网络数);
        ls子网列表 = findViewById(R.id.ls子网列表);
        ed网络中主机的个数 = findViewById(R.id.ed网络中主机的个数);
        ed要划分网络的个数 = findViewById(R.id.ed要划分网络的个数);
        tv划分子网后的子网掩码 = findViewById(R.id.tv划分子网后的子网掩码);
        bt归属地=findViewById(R.id.bt归属地);
        bt说明=findViewById(R.id.bt说明);
        bt关于=findViewById(R.id.bt关于);
        tv计算过程 = findViewById(R.id.tv计算过程);
        btXuanXiang = findViewById(R.id.btXuanXiang);
        mMyDrawable = findViewById(R.id.main_drawer_layout);


        sharedPreferences = context.getSharedPreferences("data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        final String IP = sharedPreferences.getString("IP", "");
        String Mask = sharedPreferences.getString("Mask", "");
        if (!(IP.equals("") && Mask.equals(""))) {
            etIP.setText(IP);
            etMask.setText(Mask);
        }
        btXuanXiang.setOnClickListener(new View.OnClickListener() {  //处理打开和关闭侧滑菜单的事件
            @Override
            public void onClick(View view) {
                mMyDrawable.openDrawer(Gravity.LEFT);
            }
        });
        bt归属地.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,IPGuishuDiActivity.class);
                startActivity(intent);
                mMyDrawable.closeDrawers();
            }
        });
        bt说明.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.shuoming);
                dialog.show();
            }
        });
        bt关于.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.guanyu);
                dialog.show();
            }
        });

        btJisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:di ");
                String strIP = etIP.getText().toString();
                String iPmask = etMask.getText().toString();
                editor.putString("IP", strIP);
                editor.putString("Mask", iPmask);
                editor.commit();
                if (IPpanDuan.panDuan(strIP)) {

                    str二进制Ip = IP转二进制.ip转二进制(strIP);
                    tv二进制IP.setText(str二进制Ip);
                    if ((iPmask.length() == 2 || iPmask.length() == 1) && Integer.valueOf(iPmask) <= 32) {
                        int intMask = Integer.valueOf(iPmask);
                        str二进制掩码 = IP转二进制.网络长度转二进制(intMask);
                        输出结果();
                    } else if (IPpanDuan.panDuan(iPmask)) {
                        str二进制掩码 = IP转二进制.ip转二进制(iPmask);
                        输出结果();
                    } else {
                        Toast.makeText(MainActivity.this, "掩码输入有误", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "IP输入有误", Toast.LENGTH_LONG).show();
                }
            }
        });
        bt子网划分.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str要划分网络的个数 = ed要划分网络的个数.getText().toString();
                String str网络中主机的个数 = ed网络中主机的个数.getText().toString();
                if (str网络中主机的个数.equals("") && str要划分网络的个数.equals("")) {
                    Toast.makeText(context, "你没有输入要求", Toast.LENGTH_LONG).show();
                } else if (!str要划分网络的个数.equals("")) {      //通过要划分网络的个数计算子网
                    int x需要借的位数 = 0;
                    try {
                        int int要要划分网络的个数 = Integer.valueOf(str要划分网络的个数);
                        for (int i = 0; i < 32; i++) {
                            if (Math.pow(2, i) >= int要要划分网络的个数) {
                                x需要借的位数 = i;
                                break;
                            }
                        }
                        tv计算过程.setText("需要划分至少" + int要要划分网络的个数 + "个网络，必须向主机位借" + x需要借的位数 + "位");
                        str子网划分后的二进制掩码 = Z子网划分.j借位(str二进制掩码, x需要借的位数);
                        if (str子网划分后的二进制掩码.equals("-1")) {
                            throw new Exception("-1");
                        }//如果发现不够借，就抛出异常；
                        tv划分子网后的子网掩码.setText(IP转二进制.二进制转IP(str子网划分后的二进制掩码) + "/" + IP转二进制.二进制掩码转网络长度(str子网划分后的二进制掩码));
                        tv子网的可用主机数.setText(IP地址计算.j计算可用ip的个数(str子网划分后的二进制掩码) + "");
                        tv总共划分的网络数.setText((int) Math.pow(2, x需要借的位数) + "");
                        Log.d(TAG, "onClick: " + x需要借的位数);

                        iPclasses.clear();//清空列表
                        MyThin myThin = new MyThin(x需要借的位数);
                        myThin.start();
                        d等待框 = ProgressDialog.show(MainActivity.this, "正在计算中", "总共要划分" + (int) Math.pow(2, x需要借的位数) + "个子网，" + "正在计算中，请稍后……");

                    } catch (Exception e) {
                        if (e.getMessage().equals("-1")) {
                            Toast.makeText(context, "主机位不够借位", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "你的输入有误或没有点击上面的计算按钮", Toast.LENGTH_LONG).show();
                        }
                        Log.d(TAG, "onClick: " + e + "  " + x需要借的位数);
                    }
                } else {                                     //通过网络中需要主机的个数计算子网
                    int x需要保留的位数 = 0;
                    try {
                        int int网络中主机的个数 = Integer.valueOf(str网络中主机的个数) + 2;
                        for (int i = 0; i < 32; i++) {
                            if (Math.pow(2, i) >= int网络中主机的个数) {
                                x需要保留的位数 = i;
                                break;
                            }
                        }
                        tv计算过程.setText("网络中需要至少" + (int网络中主机的个数 - 2) + "个主机，必须保留" + x需要保留的位数 + "位");
                        Log.d(TAG, "需要保留的位数 " + x需要保留的位数);
                        str子网划分后的二进制掩码 = Z子网划分.j根据要保留的位数借位(str二进制掩码, x需要保留的位数);
                        if (str子网划分后的二进制掩码.equals("-1")) {
                            throw new Exception("-1");
                        }//如果发现不够借，就抛出异常；
                        tv划分子网后的子网掩码.setText(IP转二进制.二进制转IP(str子网划分后的二进制掩码) + "/" + IP转二进制.二进制掩码转网络长度(str子网划分后的二进制掩码));
                        tv子网的可用主机数.setText(IP地址计算.j计算可用ip的个数(str子网划分后的二进制掩码) + "");
                        int x需要借的位数 = (32 - (x需要保留的位数 + IP转二进制.二进制掩码转网络长度(str二进制掩码)));
                        int z总共划分的网络数 = (int) Math.pow(2, x需要借的位数);
                        tv总共划分的网络数.setText(z总共划分的网络数 + "");
                        Log.d(TAG, "onClick: " + x需要保留的位数);

                        iPclasses.clear();//清空列表

                        MyThin myThin = new MyThin(x需要借的位数);
                        myThin.start();
                        d等待框 = ProgressDialog.show(MainActivity.this, "正在计算中", "总共要划分" + (int) Math.pow(2, (32 - (x需要保留的位数 + IP转二进制.二进制掩码转网络长度(str二进制掩码)))) + "个子网，" + "正在计算中，请稍后……");

                    } catch (Exception e) {
                        if (e.getMessage().equals("-1")) {
                            Toast.makeText(context, "主机位不够", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "你的输入有误或没有点击上面的计算按钮", Toast.LENGTH_LONG).show();
                        }
                        Log.d(TAG, "错误:" + e + "" + x需要保留的位数);
                    }
                }
            }

        });
    }

    void 输出结果() {
        Log.d(TAG, "onClick: " + str二进制掩码);
        tv二进制掩码.setText(str二进制掩码);
        tv掩码.setText(IP转二进制.二进制转IP(str二进制掩码));
        tv网络位长度.setText(IP转二进制.二进制掩码转网络长度(str二进制掩码) + "");
        tv本网网络地址.setText(IP地址计算.j计算网络地址(str二进制Ip, str二进制掩码));
        tv本网第一个主机地址.setText(IP地址计算.j计算第一个ip(str二进制Ip, str二进制掩码));
        tv本网最后一个主机地址.setText(IP地址计算.j计算最后的IP地址(str二进制Ip, str二进制掩码));
        tv本网最广播地址.setText(IP地址计算.j计算广播地址(str二进制Ip, str二进制掩码));
        tv本网段可用主机数.setText(IP地址计算.j计算可用ip的个数(str二进制掩码) + "");

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        //获得adapter
        MyAdapter adapter = (MyAdapter) listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            //计算总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //计算分割线高度
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        //给listview设置高度
        listView.setLayoutParams(params);
    }

    class MyThin extends Thread {
        int x需要借的位数;

        public MyThin(int x需要借的位数) {
            this.x需要借的位数 = x需要借的位数;
        }

        @Override
        public void run() {
            Message message = new Message();
            for (int x = 0; x < Math.pow(2, x需要借的位数); x++) {
                String z子网划分前的网络地址 = IP转二进制.ip转二进制(IP地址计算.j计算网络地址(str二进制Ip, str二进制掩码));
                String z子网划分后的网络地址 = Z子网划分.计算借位后的网络地址(x, z子网划分前的网络地址, str二进制掩码, str子网划分后的二进制掩码);
                iPclasses.add(new IPclass(x, IP转二进制.二进制转IP(z子网划分后的网络地址), IP地址计算.j计算第一个ip(z子网划分后的网络地址, str子网划分后的二进制掩码), IP地址计算.j计算最后的IP地址(z子网划分后的网络地址, str子网划分后的二进制掩码), IP地址计算.j计算广播地址(z子网划分后的网络地址, str子网划分后的二进制掩码)));
            }
            message.what = 1;
            handler.sendMessage(message);
        }
    }

}



