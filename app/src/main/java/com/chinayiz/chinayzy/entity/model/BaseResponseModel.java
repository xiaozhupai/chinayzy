package com.chinayiz.chinayzy.entity.model;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 9:12
 * Class ResponseResult 响应基类
 */
//{
//        "time": "2017-01-07 11:12:29",
//        "data": {
//        "userid": 1
//        },
//        "code": "100",
//        "msg": "获取成功！"
//        }
public class BaseResponseModel  {

    private String time;//响应时间
    private String code;//响应码
    private String msg;//响应消息

    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }

    @Override
    public String toString() {
        return "BaseResponseModel{" +
                "time='" + time + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
