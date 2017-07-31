package com.pramy.common;


public class CommonResult<T> {
    public final static boolean SUCCESS = true;
    public final static boolean FAIL = false;
    /**
     * 操作是否成功
     */
    private boolean isSuccess;
    /**
     * 操作结果信息，主要用来存储操作失败时对异常信息做出的提示信息
     */
    private String message;
    /**
     * 操作结果数据集，用来存储要返回给前端的数据，可以是对象、集合等等
     */
    private T data;

    public CommonResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public CommonResult(boolean isSuccess, T data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public CommonResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
