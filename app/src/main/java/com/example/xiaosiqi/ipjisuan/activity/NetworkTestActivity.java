package com.example.xiaosiqi.ipjisuan.activity;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.xiaosiqi.ipjisuan.R;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
public class NetworkTestActivity extends AppCompatActivity {
    private static final String TAG = "NetworkTestActivity";
    private Button btn_ping;
    private Button bt清空;
   private Button bt执行其他命令;
    EditText et_ip, et_count, et_size, et_time;
    EditText et其他命令;
    String ip, count, size, time;
    TextView tv_show;
    String ping;
    String lost = "";// 丢包
    String delay = "";// 延迟
    private static final String tag = "TAG";// Log标志
    Thread a = null;

    Handler handler1 = new Handler() {// 创建一个handler对象 ，用于监听子线程发送的消息
        public void handleMessage(Message msg)// 接收消息的方法
        {
            // String str = (String) msg.obj;// 类型转化
            // tv_show.setText(str);// 执行
            switch (msg.what) {
                case 10:
                    String resultmsg = (String) msg.obj;
                    refreshLogView(resultmsg);
                    Log.i(tag, "====handlerThread====:" + Thread.currentThread().getId());
                    Log.i(tag, "====resultmsg====:" + msg.what);
                    Log.i(tag, "====resultmsg====:" + resultmsg);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_test);
        setTitle("网络测试命令");
        btn_ping = (Button) findViewById(R.id.btn_ping);
        bt清空=findViewById(R.id.btn_清空);
        bt执行其他命令=findViewById(R.id.btn_执行其他命令);
        et_ip = (EditText) findViewById(R.id.edit_ip);
        et_count = (EditText) findViewById(R.id.edit_count);
        et_size = (EditText) findViewById(R.id.edit_size);
        et_time = (EditText) findViewById(R.id.edit_time);
        et其他命令=findViewById(R.id.ed其他命令);
        tv_show=findViewById(R.id.tv_show);
        tv_show.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip = et_ip.getText().toString();   //IP地址
                count = et_count.getText().toString();
                size = et_size.getText().toString();
                time = et_time.getText().toString();
                String countCmd = " -c " + count + " ";
                String sizeCmd = " -s " + size + " ";
                String timeCmd = " -i " + time + " ";
                String ip_adress = ip;
                ping = "ping" + countCmd + timeCmd + sizeCmd + ip_adress;
                command(ping);
            }
        });
        bt执行其他命令.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                command(et其他命令.getText().toString());
            }
        });
        bt清空.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_show.setText("");
            }
        });
    }

    void refreshLogView(String msg){
        tv_show.append(msg);
        int offset=tv_show.getLineCount()*tv_show.getLineHeight();
        if(offset>tv_show.getHeight()){
            tv_show.scrollTo(0,offset-tv_show.getHeight());
        }
    }
    private void command(final String sh)
    {
        a = new Thread()// 创建子线程
        {
            public void run() {
                delay = "";
                lost = "";
                Process process = null;
                BufferedReader successReader = null;
                BufferedReader errorReader = null;
                DataOutputStream dos = null;
                try {
                    process = Runtime.getRuntime().exec(sh);
                    Log.i(tag, "====receive====:");
                    InputStream in = process.getInputStream();
                    OutputStream out = process.getOutputStream();
                    // success
                    successReader = new BufferedReader(new InputStreamReader(in));
                    // error
                    errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String lineStr;
                    while ((lineStr = successReader.readLine()) != null) {
                        Log.i(tag, "====receive====:" + lineStr);
                        Message msg = handler1.obtainMessage();
                        msg.obj = lineStr + "\r\n";
                        msg.what = 10;
                        msg.sendToTarget();
                    }

                    while ((lineStr = errorReader.readLine()) != null) {
                        Log.i(tag, "==error======" + lineStr);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (dos != null) {
                            dos.close();
                        }
                        if (successReader != null) {
                            successReader.close();
                        }
                        if (errorReader != null) {
                            errorReader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (process != null) {
                        process.destroy();
                    }
                }
            }
        };
        a.start();
    }

}
