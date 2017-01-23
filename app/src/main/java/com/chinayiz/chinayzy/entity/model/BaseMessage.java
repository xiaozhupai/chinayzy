package com.chinayiz.chinayzy.entity.model;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/19 16:27
 * Class Message Event Bus  消息基类
 */
public class BaseMessage {
    /**
     *  网络请求接口回调消息
     */
    public static final int NET_EVENT=1;
    /**
     *  组建之间通讯消息
     */
    public static final int INFORM_EVENT=2;
    /**
     * 事件类型
     * 类型1：NET_EVENT  网络请求接口回调消息
     * 类型2：INFORM_EVENT  组建之间通讯消息
     */
    protected int eventType;

    public BaseMessage(int msgType) {
        this.eventType = msgType;
    }

    public int getEventType() {
        return eventType;
    }
    public void setEventType(int msgType) {
        this.eventType = msgType;
    }
}
