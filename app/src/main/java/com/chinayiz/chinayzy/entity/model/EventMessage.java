package com.chinayiz.chinayzy.entity.model;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 16:00
 * Class EventModel Event Bus数据包
 */
public class EventMessage extends BaseMessage{
    /**
     * 数据类型 暂定以URL来匹配
     */
    private String dataType;
    /**
     * 数据体
     */
    private Object data;
    /**
     * 数据类型 暂定以URL来匹配
     */

    /**
     *
     * @param msgType 消息类型
     * @param dataType 数据类型
     * @param data 数据
     */
    public EventMessage(int msgType, String dataType, Object data) {
        super(msgType);
        this.dataType = dataType;
        this.data = data;
    }
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "dataType='" + dataType + '\'' +
                ", data=" + data +
                '}';
    }
}
