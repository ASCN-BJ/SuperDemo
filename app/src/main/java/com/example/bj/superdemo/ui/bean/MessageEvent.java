package com.example.bj.superdemo.ui.bean;

/**
 * Created by bj on 2016/9/7.
 */
public class MessageEvent {
    private int dataInt;
    private Object dataObj;
    private String dataString;

    public MessageEvent(int dataInt, Object dataObj, String dataString) {
        this.dataInt = dataInt;
        this.dataObj = dataObj;
        this.dataString = dataString;
    }

    public MessageEvent() {

    }

    public MessageEvent(int dataInt) {

        this.dataInt = dataInt;
    }

    public int getDataInt() {
        return dataInt;
    }

    public void setDataInt(int dataInt) {
        this.dataInt = dataInt;
    }

    public Object getDataObj() {
        return dataObj;
    }

    public void setDataObj(Object dataObj) {
        this.dataObj = dataObj;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
}
