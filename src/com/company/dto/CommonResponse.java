package com.company.dto;

import org.apache.commons.lang3.ObjectUtils;

public class CommonResponse {
private String uid;
private long timestamp = System.currentTimeMillis();
private int statusCode;
private String message;
private  Object object;
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "uid='" + uid + "\n" +
                ", timestamp=" + timestamp + "\n"+
                ", statusCode=" + statusCode + "\n"+
                ", message='" + message + "\n" +
                ", object=" + object + "\n"+
                '}';
    }
}
