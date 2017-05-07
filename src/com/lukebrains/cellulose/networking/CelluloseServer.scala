package com.lukebrains.cellulose.networking

import java.io.{InputStream,OutputStream}
import java.net.ServerSocket
import java.net.SocketTimeoutException
import java.io.IOException
import java.io.DataInputStream

class CelluloseServer extends Thread {
  val serverSocket = new ServerSocket(5097)
  override def run() {
    while(true) {
      try {
        val server = serverSocket.accept()
        val message = new DataInputStream(server.getInputStream)
        println(message.readUTF())
      } catch {
        case s : SocketTimeoutException => s.printStackTrace()
        case i : IOException => i.printStackTrace()
      }
    }  
  } 
}