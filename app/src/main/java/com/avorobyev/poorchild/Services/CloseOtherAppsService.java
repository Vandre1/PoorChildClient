package com.avorobyev.poorchild.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.avorobyev.poorchild.Tasks.CloseAppTasks;

public class CloseOtherAppsService extends Service {

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class CloseOtherAppsBinder extends Binder {
        CloseOtherAppsService getService() {
            return CloseOtherAppsService.this;
        }
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new CloseOtherAppsBinder();

    private CloseAppTasks closeAppsTask;

    @Override
    public void onCreate() {
        // The service is being created

        closeAppsTask = new CloseAppTasks(this);
        closeAppsTask.execute();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        if (closeAppsTask != null && !closeAppsTask.isCancelled()) {
            closeAppsTask.cancel(true);
        }
    }
}
