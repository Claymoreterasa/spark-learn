package utils;

import java.util.Random;

/**
 * Author: cwz
 * Time: 2018/3/20
 * Description:
 */
public class PlaceTimeRecordGenerator {
    private final Random RANDOM = new Random();
    private final int ID_UPPER_LIMIT = 100;
    private final int PLACEID_UPPER_LIMIT = 20;
    private final long INTERVAL_TIME = 100;


    private static class InnerHolder{
        private static final PlaceTimeRecordGenerator generator = new PlaceTimeRecordGenerator();
    }

    public static PlaceTimeRecordGenerator get(){
        return InnerHolder.generator;
    }

    public PlaceTimeRecord next(){
        try {
            Thread.sleep(INTERVAL_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int id = RANDOM.nextInt(ID_UPPER_LIMIT) + 1;
        int placeId = RANDOM.nextInt(PLACEID_UPPER_LIMIT) + 1;
        return new PlaceTimeRecord(id, placeId, System.currentTimeMillis());
    }

}
