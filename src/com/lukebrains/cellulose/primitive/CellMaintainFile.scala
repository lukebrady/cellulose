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
object cellMaintainFile {
  def maintainFileThread(path : String, content : String) = {
    val fileMaintainer  = new Thread(new Runnable {
      def run() {
        if(Files.exists(Paths.get(path))) {
          val file = new File(path)
          val source = scala.io.Source.fromFile(file)
          val lines = try source.mkString finally source.close()
            
          if(lines != content) {
            val writer = new PrintWriter(file)
            writer.write(content)
            writer.close()
          }
        }
      }
    }).start()
  }
}