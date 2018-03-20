package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Author: cwz
  * Time: 2018/3/20
  * Description: 压缩PlaceTimeRecord
  *              压缩规则：id，placeId相同，time间隔小于给定时间间隔，则保留最新记录，
  *                       最终得到的记录条数应与SendSocket::compact方法结果输出相同
  */
object Compact {
  def main(args: Array[String]): Unit = {
    // 初始化spark streaming，至少需要两个线程，一个用来接收，一个用来处理
    val conf = new SparkConf().setMaster("local[2]")
      .setAppName("CompactStream")

    // 每秒接收到的一组数据生成一个RDD，进行处理
    val ssc = new StreamingContext(conf, Seconds(1))

    // 从localhost 8888 端口接收socket数据
    val lines = ssc.socketTextStream("localhost", 8888)

    //TODO 实现流的压缩过程


    // 输出结果
    lines.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate
  }
}
