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

package com.lukebrains.cellulose

import java.io._
import java.util._
import scala.collection.JavaConversions._
import scala.sys.process._
import scala.concurrent._
import scala.concurrent.duration._
import com.lukebrains.cellulose.primitive._

class CallableThread {
  object cell {
    
    def file(path: String, content: String) = {
      val fileThread = cellFile
      val fileCheckThread = new Thread(new Runnable {
        def run() {
          while(true) {
            fileThread.fileCreationThread(path, content)
            Thread.sleep(2000)
          }
        }
      }).start()
    }
    
    // This needs to be changed at some point.
    def maintainFile(path : String, content : String) = {
      val thread = cellMaintainFile
      val maintainFileThread = new Thread(new Runnable {
        def run() {
          while(true) {
            thread.maintainFileThread(path,content)
            Thread.sleep(2000)
          }
        }
      }).start()
    }
    
    /*
     * This method checks to see if the files you define if code remain the same.
     * cellulose will compare the contents of the configuration file to that of the file.
     * This allows Warden to change back any files that may have been tampered with.
     * @param path: The path that your file has been saved. Defined in configuration file.
     * @param name : The name of the file that needs to be read.
     * @param compareObject : Object or List that will be compared against the created file.
     * 
     */
    
    def directory(path: String, ensure: String = "Absent") = {
      val dirThread = cellDirectory
      val dirCheckThread = new Thread(new Runnable {
        def run() {
          while(true) {
            dirThread.directoryCreationThread(path, ensure)
            Thread.sleep(2000)
          }
        }
      }).start()
    }
  }
}