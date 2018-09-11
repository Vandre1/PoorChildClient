package com.avorobyev.poorchild.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.avorobyev.poorchild.Services.CloseOtherAppsService;
import com.avorobyev.poorchild.Services.LockScreenService;


public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(context, LockScreenService.class);
        context.startService(myIntent);

    }
}
