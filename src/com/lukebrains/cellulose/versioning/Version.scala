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

package com.lukebrains.cellulose.versioning

import java.io.{ObjectOutputStream, ObjectInputStream, FileOutputStream, FileInputStream}
import scala.collection.immutable.HashMap
import java.io.IOException

class Version {
  var cache : HashMap[Int, Object] = HashMap()
  var versionLimit : Int = 1
  var versionID : Int = 1
  
  def initializeVersion(versionLimit : Int) {
    this.versionLimit = versionLimit
    versionID += 1
  }
  
  def cacheConfiguration(configuration : Object, outputDirectory : String) {
    if(versionID <= versionLimit) 
      cache.updated(versionID, configuration)
    else {
      val cacheFile = new FileOutputStream(outputDirectory + "/" + versionID)
      val cacheOutputStream = new ObjectOutputStream(cacheFile)
      try {
        cacheOutputStream.writeObject(configuration)
        cacheOutputStream.flush()
      } catch {
        case i : IOException => i.printStackTrace()
      } finally {
        cacheOutputStream.close()
      }
    }
  }
  
  def restoreConfiguration(version : Int, inputDirectory : String) : Object = {
    var configuration : Object = null
    if(cache.contains(version)) {
      configuration = cache.get(version)
    }
    else {
      val cacheFile = new FileInputStream(inputDirectory + "/" + version)
      val cacheInputStream = new ObjectInputStream(cacheFile)
      try {
        configuration = cacheInputStream.readObject()
      } catch {
        case i : IOException => i.printStackTrace()
      } finally {
        cacheInputStream.close()
      }
    }
    return configuration
  }
}