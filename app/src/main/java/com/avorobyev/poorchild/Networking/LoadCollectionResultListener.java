package com.avorobyev.poorchild.Networking;

import java.util.ArrayList;

public interface LoadCollectionResultListener<T> {
    void LoadSuccess(ArrayList<T> items);
    void LoadError(Exception exception);
    void LoadCompleted();
}