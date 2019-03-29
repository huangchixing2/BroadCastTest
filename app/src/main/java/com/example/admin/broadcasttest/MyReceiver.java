package com.example.admin.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {


    public MyReceiver() {
    }

    /**
     * 标准广播
     *
     * @param context
     * @param intent
     */


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Received in MyBroadCastReceiver", Toast.LENGTH_LONG).show();
    }
}
