package com.avorobyev.poorchild.Tasks;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.avorobyev.poorchild.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CloseAppTasks extends AsyncTask<Void, Void, ArrayList<String>> {

    private Context Context;

    public CloseAppTasks(Context context) {
        this.Context = context;
    }

    /** The system calls this to perform work in a worker thread and
     * delivers it the parameters given to AsyncTask.execute() */
    @Override
    protected ArrayList<String> doInBackground(Void... urls) {
        ArrayList<String> result = new ArrayList<String>();

        while (!this.isCancelled()) {

            ActivityManager activityManager = (ActivityManager) this.Context.getSystemService(this.Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();

            for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcesses) {
                if (processInfo.processName.equals("com.fbreader")) {
                    activityManager.killBackgroundProcesses(processInfo.processName);
                }
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
