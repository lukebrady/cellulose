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