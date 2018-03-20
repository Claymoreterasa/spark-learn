# spark learn for starter


# 软件版本
- IntelliJ IDEA
- JDK 1.8
- Scala 2.11
- Maven
- Git
- Spark 1.6.0

## 准备环境

- 安装[JDK-1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)，并[设置环境变量](http://jingyan.baidu.com/article/925f8cb836b26ac0dde0569e.html)
- 安装[Scala-2.11.11](http://www.scala-lang.org/download/2.11.11.html),并设置[环境变量](http://www.runoob.com/scala/scala-install.html)
- 安装IntelliJ IDEA
- 下载此项目源码
  - `git clone https://github.com/Claymoreterasa/xdu-cloudcourse-spark.git`
  - [打包下载ZIP](https://github.com/Claymoreterasa/spark-learn/archive/master.zip)

## 导入项目

使用IntelliJ IDEA导入此项目，导入时使用Maven作为构建工具，之后下一步到完成即可。导入后需要下载依赖包，先确保电脑联网状态。如未自动下载，可以点击右侧`Maven Project`的`Reimport`继续下载依赖包。

## 注意
<b>标明RDD和SQL的，分别用RDD和SQL API实现，标明两种实现方式每种也都要实现</b>

## 训练题目
### 1. 各地点过车次数统计（RDD SQL）
统计每个地点经过的车辆数量
#### 涉及文件
- data/record.json
- src/main/java/utils/VehicleRecord.java
- src/main/scala/rdd/PassbyCount.scala
- src/main/scala/sql/PassbyCount.scala(待实现)
### 2. 车辆相遇次数（RDD SQL）
相遇定义：两车之间出现在同一地点的时间间隔小于一分钟
#### 实现1
自身以地点为键进行join操作，计算除自身外的车辆是否相遇
```
(placeId，(eid，time)) join (placeId，(eid，time)) ->
(placeId, (eid1, time1, eid2, time2)) filter (eid1 != eid2 && |time1 - time2| < 60) ->
((eid1, eid2), 1) reduceByKey ->
((eid1, eid2), count)
```
#### 实现2
 以地点为键进行分组，同一组内的数据按照时间进行排序，遍历整个列表，看哪些车辆之间满足时间间隔
#### 涉及文件
- data/record.json
- src/main/java/utils/VehicleRecord.java
- src/main/scala/rdd/MeetCount.scala(待实现)
- src/main/scala/sql/MeetCount.scala(待实现)
### 3. 车辆轨迹压缩（Streaming）
id，placeId相同，time间隔小于给定时间间隔，则保留最新记录， 最终得到的记录条数应与SendSocket::compact方法结果输出相同
#### 涉及文件
- src/main/java/utils/PlaceTimeRecord.java
- src/main/java/utils/PlaceTimeRecordGenerator.java
- src/main/java/utils/SendSocket.java
- src/main/scala/streaming/Compact.scala(待实现)
## **Q&A**

### 1 依赖下载失败

检查网络连接，并多次尝试`Maven Reimport`。

### **其他未列错误或问题，请先自行尝试搜索引擎解决。**

# 项目涉及的技术说明

## [Spark](http://spark.apache.org/)

Spark是一个基于内存计算的分布式计算框架，被广泛使用

### 文档和教程

- <https://spark.apache.org/docs/1.6.0/quick-start.html>
- <https://spark.apache.org/docs/1.6.0/programming-guide.html>
- <https://spark.apache.org/docs/1.6.0/sql-programming-guide.html>
- <https://spark.apache.org/docs/1.6.0/streaming-programming-guide.html>
