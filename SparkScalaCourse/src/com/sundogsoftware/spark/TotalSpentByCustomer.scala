package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._


object TotalSpentByCustomer {
  
  def parseLine(line:String)= {
    val fields = line.split(",")
    val customerID = fields(0).toInt
    val TxnId = fields(1)
    val spentAmt = fields(2).toFloat 
    (customerID, spentAmt)
  }
    /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "TotalSpentByCustomer")
    
    // Read each line of input data
    val lines = sc.textFile("../customer-orders.csv")
    
    // Convert to (stationID, entryType, temperature) tuples
    val parsedLines = lines.map(parseLine)
 
    val totSpent = parsedLines.reduceByKey((x,y) => (x + y))
      
    val orderedTotSpent = totSpent.map( x => (x._2, x._1) ).sortByKey()
    
     // Print the results, flipping the (count, word) results to word: count as we go.
    for (result <- orderedTotSpent) {
      val TotSpent = result._1
      val CustomerId = result._2
      println(s"$CustomerId: $TotSpent")
    }
    
   // results.foreach(println)
  }
}