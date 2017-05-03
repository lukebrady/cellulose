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