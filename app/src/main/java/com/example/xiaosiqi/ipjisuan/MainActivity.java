package com.example.xiaosiqi.ipjisuan;

import android.renderscript.Int4;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText etIP;
    private EditText etMask;
    private Button btJisuan;
    private TextView tv二进制IP;
    private TextView tv二进制掩码;
    private TextView tv掩码;
    private TextView tv网络位长度;
    int[] intIp={0,0,0,0};
    int[] intMask={0,0,0,0};
    private  String  str二进制掩码;
    private String str二进制Ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIP=findViewById(R.id.etIP);
        etMask=findViewById(R.id.etMask);
        btJisuan=findViewById(R.id.btJiSuan);
        tv二进制IP=findViewById(R.id.tv二进制IP);
        tv二进制掩码=findViewById(R.id.tv二进制掩码);
        tv掩码=findViewById(R.id.tv掩码);
        tv网络位长度=findViewById(R.id.tv网络位长度);

        btJisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:di ");
               String strIP=etIP.getText().toString();
               String iPmask=etMask.getText().toString();

               if (IPpanDuan.panDuan(strIP)){
                    String[] ip = strIP.split("[.]");
                    for (int i = 0; i <ip.length ; i++) {
                        Log.d(TAG, "onClick: "+ip[i]);
                         intIp[i]= Integer.valueOf(ip[i]);
                    }
                    str二进制Ip =  IP转二进制.ip转二进制(intIp);
                    tv二进制IP.setText(str二进制Ip);
                   if (iPmask.length()==2&&Integer.valueOf(iPmask)<=32)
                   {
                       int intMask=Integer.valueOf(iPmask);
                       str二进制掩码=  IP转二进制.网络长度转二进制(intMask);
                      输出结果();
                   }
                   else if (IPpanDuan.panDuan(iPmask)){
                       String[] mask = iPmask.split("[.]");
                       for (int i = 0; i <mask.length ; i++) {
                           Log.d(TAG, "onClick: "+mask[i]);
                           intMask[i]= Integer.valueOf(mask[i]);
                       }
                       str二进制掩码 =  IP转二进制.ip转二进制(intMask);
                       输出结果();


                   }
                   else {
                       Toast.makeText(MainActivity.this,"掩码输入有误",Toast.LENGTH_LONG).show();
                   }
                }
                else {
                    Toast.makeText(MainActivity.this,"IP输入有误",Toast.LENGTH_LONG).show();
                }

            }

        });


    }
    void  输出结果(){
        Log.d(TAG, "onClick: "+str二进制掩码);
        tv二进制掩码.setText(str二进制掩码);
        tv掩码.setText(IP转二进制.二进制转IP(str二进制掩码));
        tv网络位长度.setText(IP转二进制.二进制掩码转网络长度(str二进制掩码)+"");



    }
}
