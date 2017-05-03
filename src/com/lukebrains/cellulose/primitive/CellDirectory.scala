package com.lukebrains.cellulose.primitive

import java.io._

object cellDirectory {
  def directoryCreationThread(path: String, ensure: String = "present") = {
      val dirCreator  = new Thread(new Runnable {
        def run() {
          val dir = new File(path)
      
          if(ensure == "Present" || ensure == "present") {
            try {
              if(dir.exists()){
                Thread.`yield`()
              }
            else {
              dir.mkdir()
            }
          }
            catch {
              case e : IOException => {
                e.printStackTrace()
              }
            }
          }
      
         if(ensure == "Absent" || ensure == "absent") {
           if(dir.exists()) {
             try {
                dir.delete()
                 if(dir.exists() == false) {
                   println(">>> Warden has successfully removed the directory.")
                 }
             }
             catch {
               case io : IOException => {
                 println(">>> Error occured due to " + io + ".")
               }
             }
           }
         }
       } 
     }).start()
   }
}