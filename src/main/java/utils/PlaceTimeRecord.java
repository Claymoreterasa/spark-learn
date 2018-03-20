package utils;

import java.io.Serializable;

/**
 * Author: cwz
 * Time: 2018/3/20
 * Description: 编号，地点，出现时间
 */
public class PlaceTimeRecord implements Serializable{
    private int id;
    private int placeId;
    private long time;

    public PlaceTimeRecord(){

    }

    public PlaceTimeRecord(int id, int placeId, long time) {
        this.id = id;
        this.placeId = placeId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("utils.PlaceTimeRecord{");
        sb.append("id=").append(id);
        sb.append(", placeId=").append(placeId);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
