package com.example.admin.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 *
 * 动态广播注册：当网络发生变化时，弹出toast提示用户
 */

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetWorkChangeReceiver netWorkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
