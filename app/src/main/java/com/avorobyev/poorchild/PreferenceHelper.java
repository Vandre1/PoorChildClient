package com.avorobyev.poorchild;

import android.content.Context;
import android.provider.Settings;

import com.avorobyev.poorchild.Test.TestData;

public class PreferenceHelper {
    public static final String ChildDeviceId = "CHILD_DEVICE_ID";
    public static final String ChildDeviceKey = "CHILD_DEVICE_KEY";
    public static final String ParentDeviceId = "PARENT_DEVICE_ID";
    public static final String IsCurrentDeviceChild = "IS_CURRENT_DEVICE_CHILD";
    public static final String IsCurrentDeviceParent = "IS_CURRENT_DEVICE_PARENT";

    public static String getAndroidId(Context context) {
        return TestData.Child1AndroId;
        // return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
