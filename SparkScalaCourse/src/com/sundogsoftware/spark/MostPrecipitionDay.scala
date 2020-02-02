package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max

object MostPrecipitionDay {
  
  def parseLine(line:String)= {
    val fields = line.split(",")
    val dayOfYear = fields(1)
    val entryType = fields(2)
    val precipitate = fields(3).toInt
    (dayOfYear, entryType, precipitate)
  }
    /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MostPrecipitionDay")
    
    // Read each line of input data
    val lines = sc.textFile("../1800.csv")
    
    // Convert to (stationID, entryType, temperature) tuples
    val parsedLines = lines.map(parseLine)
    
    // Filter out all but TMIN entries
    val pcptRec = parsedLines.filter(x => x._2 == "PRCP") //.filter( x => x._3 != 0)
    
       // Convert to (stationID, temperature)
    val dayPcpt = pcptRec.map(x => (x._1, x._3.toInt))
    
    // Reduce by stationID retaining the minimum temperature found    
    val flipped = dayPcpt.map(x => (x._2, x._1))
   
      // Find the max # of connections
    val mostPrecipitate = flipped.max()
    
    // Look up the name (lookup returns an array of results, so we need to access the first result with (0)).
  //  val mostPopularDay = dayPcpt.lookup(mostPrecipitate._2)(0)
    
    // Print out our answer!
    println(s"${mostPrecipitate._2} is the Day with maximum precipitate at ${mostPrecipitate._1} .") 
    
  }
}