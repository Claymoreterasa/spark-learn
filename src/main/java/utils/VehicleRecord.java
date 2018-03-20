package utils;

import java.io.Serializable;

/**
 * Author: cwz
 * Time: 2018/3/20
 * Description:
 */
public class VehicleRecord implements Serializable{
    private String eid;
    private long time;
    private int placeId;
    private String address;
    private double longitude;
    private double latitude;

    public VehicleRecord(){

    }

    public VehicleRecord(String eid, long time, int placeId, String address, double longitude, double latitude) {
        this.eid = eid;
        this.time = time;
        this.placeId = placeId;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("utils.VehicleRecord{");
        sb.append("eid='").append(eid).append('\'');
        sb.append(", time=").append(time);
        sb.append(", placeId=").append(placeId);
        sb.append(", address='").append(address).append('\'');
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append('}');
        return sb.toString();
    }
}
