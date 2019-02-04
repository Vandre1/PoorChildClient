package com.avorobyev.poorchild;

import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Dao.Parent;
import com.avorobyev.poorchild.PreferenceHelper;
import com.orhanobut.hawk.Hawk;

public class StateManager {
    public static void ClearState() {
        Hawk.put(PreferenceHelper.IsCurrentDeviceChild, null);
        Hawk.put(PreferenceHelper.IsCurrentDeviceParent, null);
        Hawk.put(PreferenceHelper.ChildDeviceId, null);
        Hawk.put(PreferenceHelper.ParentDeviceId, null);
    }

    public static void SetChildState(String childrenId) {
        ClearState();
        Hawk.put(PreferenceHelper.IsCurrentDeviceChild, true);
        Hawk.put(PreferenceHelper.ChildDeviceId, childrenId);
    }

    public static void SetParentState(String parentId) {
        ClearState();
        Hawk.put(PreferenceHelper.IsCurrentDeviceParent, true);
        Hawk.put(PreferenceHelper.ParentDeviceId, parentId);
    }
}
