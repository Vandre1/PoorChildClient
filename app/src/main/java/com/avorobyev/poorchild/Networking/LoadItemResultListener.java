package com.avorobyev.poorchild.Networking;

public interface LoadItemResultListener<T> {
    void LoadSuccess(T item);
    void LoadError(Exception exception);
    void LoadCompleted();
}