package com.sundogsoftware.spark

import org.apache.spark._
object FriendsByFirstName {
  
  def parseLine(line: String) = {
    val eachLine=line.split(",")
    val firstName=eachLine(1).toString
    val numOfFriends=eachLine(3).toInt
    (firstName,numOfFriends)
  }
  
    /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    //Logger.getLogger("org").setLevel(Level.ERROR)
        
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "FriendsByFirstName")
    
    val lines = sc.textFile("../fakefriends.csv")
    
    val rdd = lines.map(parseLine)
    
    val totalFriends = rdd.mapValues(x => (x,1)).reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2))
    
    val avgByName = totalFriends.mapValues(x => x._1/x._2)
    
    val result = avgByName.collect()
    result.sorted.foreach(println)
    
    val valByAge = avgByName.map(x => (x._2,x._1))
    val result_1 = valByAge.collect()
    result_1.sorted.foreach(println)
    
        
  }
}