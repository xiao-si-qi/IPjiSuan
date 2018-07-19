package com.example.xiaosiqi.ipjisuan.activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaosiqi.ipjisuan.tools.IPpanDuan;
import com.example.xiaosiqi.ipjisuan.tools.NetIp;
import com.example.xiaosiqi.ipjisuan.R;
import com.example.xiaosiqi.ipjisuan.tools.Toosl;
import com.example.xiaosiqi.ipjisuan.tools.City_class;

import org.json.JSONObject;



public class IPGuishuDiActivity extends AppCompatActivity {
    private static final String TAG = "IPGuishuDiActivity";
    private Button bt查询归属地;
    private Button bt查询本机公网IP信息;
    private EditText edIP;
    ProgressDialog d等待框;
    private City_class city_class;
    private String strIP;
    private TextView tvIP;
    private TextView tvIP地址信息;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:{  //返回0 查询失败，重新查询；
                    MyThread myThread=new MyThread(strIP);
                    myThread.start();
                }break;
                case 1:{//返回 1查询成功
                    d等待框.dismiss();
                    city_class= (City_class) msg.obj;
                    tvIP.setText(city_class.getIP());
                    tvIP地址信息.setText("   "+city_class.get国家()+" "+city_class.get省()+" "+city_class.get城市()+" "+city_class.get城市ID()+"  "+city_class.get运营商());
                }break;
                case 2:{ ///返回2 //查询本机公网IP；
                   String ip= (String) msg.obj;
                   edIP.setText(ip);
                }break;
                case 3:{ ///返回 //网有问题；
                    d等待框.dismiss();
                    Toast.makeText(IPGuishuDiActivity.this,"没有网络",Toast.LENGTH_LONG).show();
                }break;
            }

              }



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipguishu_di);
        setTitle("查询IP归属地");
        tvIP=findViewById(R.id.tvIP);
        bt查询归属地=findViewById(R.id.bt查询归属地);
        tvIP地址信息=findViewById(R.id. tvIP地址信息);
        edIP=findViewById(R.id.edIP);
        bt查询本机公网IP信息=findViewById(R.id.bt查询本机IP归属地);
        bt查询本机公网IP信息.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetIP getIP=new GetIP();
                getIP.start();
            }
        });
        bt查询归属地.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IPpanDuan.panDuan(edIP.getText().toString()))
                {
                    strIP=edIP.getText().toString();
                    MyThread myThread=new MyThread(strIP);
                    myThread.start();
                    d等待框 = ProgressDialog.show(IPGuishuDiActivity.this, "正在查询", "请稍侯……");

                }else
                {
                    Toast.makeText(IPGuishuDiActivity.this,"输入的IP不正确",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    class MyThread extends Thread{
        String IP;

        public MyThread(String IP) {
            this.IP = IP;

        }
        @Override
        public void run() {
            super.run();
            Message message=new Message();
            City_class city_class=new City_class();
            message.what=1;

            Toosl toosl=new Toosl();
            try {
                String 服务器返回的数据= toosl.HttpUrl(IP);
                JSONObject jsonObject=new JSONObject( 服务器返回的数据);
                int code=jsonObject.getInt("code");
                if (code==1)
                {
                    message.what=0;
                    handler.sendMessage(message);
                    return;
                }
                String data=jsonObject.getString("data");
                JSONObject jsonObjectData=new JSONObject(data);
                city_class.setIP(jsonObjectData.getString("ip"));
                city_class.set国家(jsonObjectData.getString("country"));
                city_class.set省(jsonObjectData.getString("region"));
                city_class.set城市(jsonObjectData.getString("city"));
                city_class.set运营商(jsonObjectData.getString("isp"));
                city_class.set城市ID(jsonObjectData.getString("city_id"));
                message.obj=city_class;
                message.what=1;
                handler.sendMessage(message);
            } catch (Exception e) {
                message.what=3;
                handler.sendMessage(message);
                e.printStackTrace();
            }
        }
    }
    class GetIP extends Thread{
        @Override
        public void run() {
            super.run();
            Message message=new Message();
            message.what=2;
          NetIp netIp=new NetIp();
            try {
                String 服务器返回的数据=netIp.GetNetIp();
                Log.d(TAG, "公网IP: "+服务器返回的数据);
                message.obj=服务器返回的数据;
                handler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
