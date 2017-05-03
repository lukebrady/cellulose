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
                case e : IOException => {
                  println(e.printStackTrace()) 
                }
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