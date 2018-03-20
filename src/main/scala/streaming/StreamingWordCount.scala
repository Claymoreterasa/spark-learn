package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Author: cwz
  * Time: 2018/3/20
  * Description: spark streaming example in http://spark.apache.org/
  * Tools: tools\nc64.exe -lp 9999
  */
object StreamingWordCount {

  def main(args: Array[String]): Unit = {
    // 初始化spark streaming，至少需要两个线程，一个用来接收，一个用来处理
    val conf = new SparkConf().setMaster("local[2]")
      .setAppName("NetworkWordCount")
    // Interval at which data received by Spark Streaming receivers is chunked into blocks of data， 200ms作为一个分区进行处理
    //  .set("spark.streaming.blockInterval","200")
    // Seconds(1)每秒接收到的一组数据生成一个RDD，进行处理
    val ssc = new StreamingContext(conf, Seconds(1))
    // 从localhost 9999 端口接收socket数据
    val lines = ssc.socketTextStream("localhost", 9999)
    // 计算每个单词出现的次数
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    // 输出结果
    wordCounts.print()

    // 启动上述操作
    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate
  }

}
