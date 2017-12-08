package com.detect.domain;

/**
 * Created by Administrator on 2017/12/08.
 */
public class ProcVO {
    private static final long serialVersionUID = 5159827367447917221L;
    private String id;
    private String procid;
    private String execid;
    private int type;
    private String filename;
    private String createtime;
    private String updatetime;
    private String deprecatetime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getExecid() {
        return execid;
    }
    public void setExecid(String execid) {
        this.execid = execid;
    }
    public String getProcid() {
        return procid;
    }
    public void setProcid(String procid) {
        this.procid = procid;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    public String getDeprecatetime() {
        return deprecatetime;
    }
    public void setDeprecatetime(String deprecatetime) {
        this.deprecatetime = deprecatetime;
    }
}
