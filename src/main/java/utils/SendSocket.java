package utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: cwz
 * Time: 2018/3/20
 * Description: 发送 sendNum 条记录到Spark Streaming消费端
 */
public class SendSocket {
    private static final long intervalCompact = 10 * 1000;
    private static int sendNum = 1000;

    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();

        ArrayList<PlaceTimeRecord> records = new ArrayList<>(sendNum);
        PlaceTimeRecordGenerator generator = PlaceTimeRecordGenerator.get();

        ServerSocket socket = new ServerSocket(8888);
        Socket client = socket.accept();
        OutputStream outputStream = client.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);

        while (sendNum-- != 0){
            PlaceTimeRecord record = generator.next();
            records.add(record);
            String jsonString = gson.toJson(record);
            System.out.println(jsonString);
            printWriter.println(jsonString);
            printWriter.flush();
        }

        List<PlaceTimeRecord> compactResult = compact(records);
        compactResult.stream().forEach(r -> System.out.println(gson.toJson(r)));
        System.out.println("before : " + records.size() + " after : " + compactResult.size());
        socket.close();
    }

    public static List<PlaceTimeRecord> compact(List<PlaceTimeRecord> records){
        List<PlaceTimeRecord> results = new ArrayList<>();
       Map<String,List<PlaceTimeRecord>> idPlaceMap = records.stream().collect(Collectors.groupingBy(r -> r.getId() + "_" + r.getPlaceId()));
       for(List<PlaceTimeRecord> value : idPlaceMap.values()){
           Iterator<PlaceTimeRecord> iterator = value.iterator();

           if(iterator.hasNext()) {
               PlaceTimeRecord last = iterator.next();
               while (iterator.hasNext()) {
                   PlaceTimeRecord current = iterator.next();
                   if(current.getTime() - last.getTime() <= intervalCompact){
                       last.setTime(current.getTime());
                       iterator.remove();
                   }else{
                       last = current;
                   }
               }
           }
           results.addAll(value);
       }
       return results;
    }
}
