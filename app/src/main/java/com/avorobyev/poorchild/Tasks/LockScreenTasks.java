package com.avorobyev.poorchild.Tasks;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.avorobyev.poorchild.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class LockScreenTasks extends AsyncTask<Void, Void, ArrayList<String>> {
    private android.content.Context Context;

    public LockScreenTasks(Context context) {
        this.Context = context;
    }

    /** The system calls this to perform work in a worker thread and
     * delivers it the parameters given to AsyncTask.execute() */
    @Override
    protected ArrayList<String> doInBackground(Void... urls) {
        ArrayList<String> result = new ArrayList<String>();

        while (!this.isCancelled()) {
            ActivityManager am = (ActivityManager) this.Context.getSystemService(this.Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;

            String currentActivityPackageName = componentInfo.getPackageName();

            if (!currentActivityPackageName.startsWith("com.avorobyev.poorchild")) {
                Intent startLockScreenActivityIntent = new Intent(this.Context, MainActivity.class);
                this.Context.startActivity(startLockScreenActivityIntent);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
