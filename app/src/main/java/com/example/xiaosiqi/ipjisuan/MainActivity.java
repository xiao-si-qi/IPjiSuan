package com.example.xiaosiqi.ipjisuan;

import android.renderscript.Int4;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText etIP;
    private EditText etMask;
    private Button btJisuan;
    int[] intIp={0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIP=findViewById(R.id.etIP);
        etMask=findViewById(R.id.etMask);
        btJisuan=findViewById(R.id.btJiSuan);

        btJisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:di ");
               String strIP=etIP.getText().toString();
                if (IPpanDuan.panDuan(strIP)){
                    String[] ip = strIP.split("[.]");
                    for (int i = 0; i <ip.length ; i++) {
                        Log.d(TAG, "onClick: "+ip[i]);
                         intIp[i]= Integer.valueOf(ip[i]);
                    }
                    String string = Integer.toBinaryString(intIp[1]);
                    etMask.setText(string+"");
                }
                else {
                    Toast.makeText(MainActivity.this,"IP输入有误",Toast.LENGTH_LONG).show();
                }

            }

        });


    }
}
