package com.avorobyev.poorchild.Networking;

import java.util.ArrayList;

public interface RequestResultListener {
    void RequestSuccess();
    void RequestNotFound();
    void RequestError(Exception exception);
    void RequestCompleted();
}