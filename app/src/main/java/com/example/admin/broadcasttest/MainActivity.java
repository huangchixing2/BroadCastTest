package com.example.admin.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *
 * 动态广播注册：当网络发生变化时，弹出toast提示用户
 */

public class MainActivity extends AppCompatActivity {

    Button sendBt;
    private IntentFilter intentFilter;
    private NetWorkChangeReceiver netWorkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBt = (Button) findViewById(R.id.send_bt);
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.admin.broadcasttest.MY_BROADCAST");
                //高版本Android发送广播需要加这句,否则无法收到广播
                intent.setComponent(new ComponentName("com.example.admin.broadcasttest","com.example.admin.broadcasttest.MyReceiver"));
                sendBroadcast(intent);
            }
        });
        //注册动态广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangeReceiver); //取消注册
    }

    private class NetWorkChangeReceiver extends BroadcastReceiver {

        //当有广播到来时，onReceive会执行
        @Override
        public void onReceive(Context context, Intent intent) {
            //这是一个系统服务类，专门用于管理网络的
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //判断是否有网络
            if(networkInfo != null && networkInfo.isConnected()){
                Toast.makeText(context, "network is available",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, "network is unavailable",Toast.LENGTH_LONG).show();
            }


        }
    }
}
