package rdd

import com.google.gson.Gson
import org.apache.spark.{SparkConf, SparkContext}
import utils.VehicleRecord

/**
  * Author: cwz
  * Time: 2018/3/20
  * Description: 每个地点经过多少辆车
  */
object PassbyCount {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("PassbyCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    sc.textFile("data/record.json")
      .mapPartitions(lines => {
        val gson = new Gson()
        lines.map(gson.fromJson(_, classOf[VehicleRecord]))
      })
      .map(r => (r.getPlaceId, 1))
      .reduceByKey(_ + _)
      .foreach(println(_))

    sc.stop()
  }
}
