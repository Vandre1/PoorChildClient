package com.avorobyev.poorchild.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.avorobyev.poorchild.Tasks.CloseAppTasks;
import com.avorobyev.poorchild.Tasks.LockScreenTasks;

public class LockScreenService extends Service {

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LockScreenBinder extends Binder {
        LockScreenService getService() {
            return LockScreenService.this;
        }
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LockScreenBinder();

    private LockScreenTasks lockScreenTask;

    @Override
    public void onCreate() {
        // The service is being created

        lockScreenTask = new LockScreenTasks(this);
        lockScreenTask.execute();
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
        if (lockScreenTask != null && !lockScreenTask.isCancelled()) {
            lockScreenTask.cancel(true);
        }
    }
}
