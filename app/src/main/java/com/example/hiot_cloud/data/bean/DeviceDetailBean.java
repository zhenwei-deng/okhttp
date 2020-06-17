package com.example.hiot_cloud.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 设备详情对象
 */
public class DeviceDetailBean implements Serializable {
    /**
     * 设备id
     */
    private String deviceId;

    private String title;

    private String status;

    private String deviceimg;

    private List<UpdatastreamDataDto> updatastreamDataDtoList;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceimg() {
        return deviceimg;
    }

    public void setDeviceimg(String deviceimg) {
        this.deviceimg = deviceimg;
    }

    public List<UpdatastreamDataDto> getUpdatastreamDataDtoList() {
        return updatastreamDataDtoList;
    }

    public void setUpdatastreamDataDtoList(List<UpdatastreamDataDto> updatastreamDataDtoList) {
        this.updatastreamDataDtoList = updatastreamDataDtoList;
    }
}
