package com.detect.domain;

/**
 * Created by Administrator on 2017/12/07.
 */
import java.io.Serializable;

public class ResponseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回成功判断
     */
    private boolean success;
    /**
     * 响应状态码
     */
    private String code;
    /**
     * 相应数据
     */
    private Object data;
    /**
     * 提示语句
     */
    private String message;

    /**
     * 线程任务ID
     */
    private String threadId;
    /**
     * 新增数据id(用于新增数据接口，返回新数据id)
     */
    private Long id;
    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

