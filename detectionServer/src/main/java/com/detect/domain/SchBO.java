package com.detect.domain;

/**
 * Created by Administrator on 2017/12/08.
 */
public class SchBO {
    private static final long serialVersionUID = 5159827367447917221L;
    private String id;
    private String schid;
    private String execid;
    private int status;
    private String stoptime;
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
    public String getSchid() {
        return schid;
    }
    public void setSchid(String schid) {
        this.schid = schid;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getStoptime() {
        return stoptime;
    }
    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
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
