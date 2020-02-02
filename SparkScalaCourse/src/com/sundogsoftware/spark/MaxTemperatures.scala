package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max

object MaxTemperatures {
  
  def parseFile(line: String)={
    val eachRec=line.split(",")
    val stationID=eachRec(0)
    val tempType=eachRec(2)
    val tempVal=eachRec(3).toFloat * 0.1f * (9.0f/5.0f) + 32.0f
    (stationID,tempType,tempVal)
  }
  
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc= new SparkContext("local[*]","MaxTemperatures")
    
    val lines = sc.textFile("../1800.csv")
   
    val parsedLines = lines.map(parseFile)
    
    val maxTempLines = parsedLines.filter(x => x._2 == "TMAX" )
    
    val stationTemp = maxTempLines.map(x => (x._1, x._3))
    
    val maxStationTemp = stationTemp.reduceByKey((x,y) => max(x,y))
    
    val result= maxStationTemp.collect()
    
    for(eachResult <- result.sorted) {
      val station=eachResult._1
      val maxTemp=eachResult._2
      val formattedMaxTemp=f"$maxTemp%.2f F"
      
      println(s"Max temparature for Station $station: $formattedMaxTemp ")
      
    }
    
   
  }
}