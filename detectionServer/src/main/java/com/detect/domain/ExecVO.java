package com.detect.domain;

/**
 * Created by Administrator on 2017/12/08.
 */
public class ExecVO {
    private static final long serialVersionUID = 5159827367447917221L;
    private String id;
    private String execid;
    private String ctlid;
    private String camid;
    private int status;
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
    public String getCtlid() {
        return ctlid;
    }
    public void setCtlid(String ctlid) {
        this.ctlid = ctlid;
    }
    public String getCamid() {
        return camid;
    }
    public void setCamid(String camid) {
        this.camid = camid;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
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
