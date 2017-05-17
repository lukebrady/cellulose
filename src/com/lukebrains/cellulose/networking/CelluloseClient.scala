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

package com.lukebrains.cellulose.networking

import java.net.Socket
import java.io.DataInputStream
import java.net.SocketException
import java.io.IOException

class CelluloseClient {
  var port : Int = 5907
  var hostname : String = null
  def initializeClient(port : Int, hostname : String) {
    this.port = port
    this.hostname = hostname
  }
  def startClient() {
    val clientSocket = new Socket(hostname, port)
    while(true) {
      try {
        val input = clientSocket.getInputStream()
        val inputStream = new DataInputStream(input)
        inputStream.readUTF()
        clientSocket.close()
      } catch {
        case s : SocketException => s.printStackTrace()
        case i : IOException => i.printStackTrace()
      }
    }
  }
}