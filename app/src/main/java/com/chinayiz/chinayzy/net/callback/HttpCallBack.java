package com.chinayiz.chinayzy.net.callback;


public abstract class HttpCallBack<T>
    {
        public abstract void success(T t);
        public abstract void fail(String msg,Exception e);
        public  void onResponse(T response){
        }
    }



