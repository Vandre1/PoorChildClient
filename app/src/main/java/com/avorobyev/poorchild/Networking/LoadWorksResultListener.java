package com.avorobyev.poorchild.Networking;

import com.avorobyev.poorchild.Model.Work;
import java.util.ArrayList;

public interface LoadWorksResultListener {
        void LoadWorksSuccess(ArrayList<Work> works);
        void LoadWorksError(Exception exception);
        void LoadWorksCompleted();
}
