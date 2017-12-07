package com.detect.domain;

/**
 * Created by Administrator on 2017/12/07.
 */
public class CameraDO {
    private static final long serialVersionUID = 5159827367447917221L;
    private String id;
    private String cameraid;
    private int exposuretime;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCameraid() {
        return cameraid;
    }
    public void setCameraid(String cameraid) {
        this.cameraid = cameraid;
    }
    public int getExposuretime() {
        return exposuretime;
    }
    public void setExposuretime(int exposuretime) {
        this.exposuretime = exposuretime;
    }
}
