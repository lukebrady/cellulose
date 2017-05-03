/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.lukebrains.cellulose.matricula


import scala.io._
import scala.collection.JavaConversions._
import java.io._
import scala.sys.process._
import java.util.HashMap
import org.json.simple._

object matriculaIO {
  
  def getAvailableMemory : JSONObject = {
    val availMem = java.lang.Runtime.getRuntime.totalMemory().toDouble
    val availHash = new HashMap[String,Double]
    availHash.put("Available Memory", availMem)
    val memJSON = new JSONObject(availHash)
    return memJSON
  }
  
  def getProcessorCount : JSONObject = {
    val procCount = java.lang.Runtime.getRuntime.availableProcessors()
    val procHash = new HashMap[String,Int]
    procHash.put("Processors", procCount)
    val procJSON = new JSONObject(procHash)
    return procJSON 
  }
  
  def getEnvVariables : JSONObject = {
    val envVariables = System.getenv
    val envHash = new HashMap[String,String]
    try {
      for((k ,v) <- envVariables) {
        envHash.put(k, v) 
      }
    }
    catch {
      case e : IOException => {
        e.printStackTrace()
      }
    }
    val envJSON = new JSONObject(envHash)
    return envJSON
  }
  
  def getProperties : JSONObject = {
    val properties = System.getProperties
    val propHash = new HashMap[String,String]
    try {
      for((y,z) <- properties){
        propHash.put(y, z)
      }
    }
    catch {
      case e : IOException => {
        e.printStackTrace()
      }
    }
    val propJSON = new JSONObject(propHash)
    return propJSON
  }
  
}