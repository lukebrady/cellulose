package com.lukebrains.cellulose.networking

import scala.sys.process._

object PushConfigFile {
  
  var nodes : Array[String] = null; // Stateful nodes Array.
  var configPath : String = null;
  
  def setNodes(nodes : Array[String]) {
    this.nodes = nodes
  }
  
  def getNodes : Array[String] = {
    return nodes
  }
  
  def setConfigPath(configPath : String) {
    this.configPath = configPath  
  }
  
  def getConfigPath : String = {
    return configPath
  }
  
  def pushConfigFile() {
    
  }
}