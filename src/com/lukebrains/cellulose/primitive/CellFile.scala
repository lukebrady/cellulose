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

package com.lukebrains.cellulose.primitive

import java.io._
import java.nio.file.{Files,Paths}

object cellFile {
  def fileCreationThread(name: String, path: String, content: String, ensure: String = "present") = {
    val fileCreator = new Thread(new Runnable {
      def run() {
        val directPath = Files.exists(Paths.get(path+"/"+name))
        if(ensure == "present" || ensure == "Present") {
          if(directPath == false) {
            try {
              val writer = new PrintWriter(new File(path+"/"+name))
              writer.write(content)
              writer.close()
            }
            catch {
              case e : IOException => println(e.printStackTrace()) 
            }
          } 
        }
        else if(ensure == "absent" || ensure == "Absent") {
          val file = new File(path+"/"+name)
          file.delete()
        }
      } 
    }).start()
  }
}