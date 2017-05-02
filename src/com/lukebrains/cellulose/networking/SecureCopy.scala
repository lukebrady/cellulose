package com.lukebrains.cellulose.networking

import java.nio.file.Path
import scala.sys.process._

object SecureCopy {
  
  val scp : String  = "scp "
  var nodes : Array[String] = null; // Stateful nodes Array.
  var configPath : String = null;
  
  def setNodes(nodes : Array[String]) {
    this.nodes = nodes
  }
  
  def getNodes : Array[String] = {
    return nodes
  }
 
  def secureCopyFile(nodes : Array[String], user : String, path: Path, destination : String) {
     if(path.isAbsolute())
       nodes.foreach { x => scp + path + " " + user + "@" + x + ":" + destination }
     
     else
       println("Error: File does not exist.")
  }  
  
}