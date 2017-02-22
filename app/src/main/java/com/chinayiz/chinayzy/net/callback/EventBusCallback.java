package com.chinayiz.chinayzy.net.callback;

import com.chinayiz.chinayzy.entity.model.EventMessage;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/19 17:26
 * Class EventBusCallback   由Event Bus回调的方法，事件分发
 */
public interface EventBusCallback {
    /**
     * 由EventBus回调，在UI线程中执行，专注更新UI组件 子类必须加注解 @Subscribe(threadMode = ThreadMode.MAIN)
     * @param message 由EvetBus传递过来的数据包
     */
    void runUiThread(EventMessage message);
    /**
     * 由EventBus回调，在后台线程中执行，不可更新UI组件 子类必须加注解 @Subscribe(threadMode = ThreadMode.BACKGROUND)
     * @param message 由EvetBus传递过来的数据包
     */
    void runBgThread(EventMessage message);

    /**
     * 此方法主要处理网络回调消息，子类此方法须由runUiThread（）或者 runBgThread（） 这两个函数调用
     * @param message 由EvetBus传递过来的数据包
     */
    void disposeNetMsg(EventMessage message);

    /**
     * 此方法主要处理组件通讯消息，子类此方法须由runUiThread（）或者 runBgThread（） 这两个函数调用
     * @param message 由EvetBus传递过来的数据包
     */
    void disposeInfoMsg(EventMessage message);
}
