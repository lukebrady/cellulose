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
import java.nio.file.{Files,Paths}
import java.util._
import scala.collection.JavaConversions._
import scala.sys.process._
import scala.concurrent._
import scala.concurrent.duration._

class CallableThread {
  object CallableThread {
    
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
    
    def maintainFileThread(path : String, content : String) = {
      val fileMaintainer  = new Thread(new Runnable {
        def run() {
          val file = new File(path)
          val source = scala.io.Source.fromFile(file)
          val lines = try source.mkString finally source.close()
          
          if(lines != content) {
            val writer = new PrintWriter(file)
            writer.write(content)
            writer.close()
          }
        }
      }).start()
    }
    
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
    
    def powershellThread(script : String = null, path : Array[String] = null, directory : String = null) = {
      val scriptThread = new Thread(new Runnable {
        def run() {
          val scriptToRun : String = script
          val powershell : String = "powershell.exe"
            if(scriptToRun != null && path == null && directory == null){
              try {
                powershell + " " + scriptToRun !
              }
              catch {
                case io : IOException => {
                  throw io
                }
                case ex : Throwable => {
                  throw ex
                }
              }
            }
            if(path != null && script == null && directory == null) {
              for(script <- path) {
                try { 
                  powershell + " " + script !
                }
                catch {
                  case e : IOException => {
                    e.printStackTrace()
                  }
                  case ex : Throwable => {
                    throw ex
                  }
                } 
              }
            }
            if(directory != null && script == null && path == null) {
              val dir = new File(directory)
              val files = dir.listFiles()
              for(file <- files) {
                try {
                  powershell + " " + file ! 
                }
                catch {
                  case e : IOException => {
                    e.printStackTrace()
                  }
                }
              }
           } 
         }
      }).start() 
    }
    
    /*
     * Installs external software package using repo of choice.
     * The default repo is the PSGallery but can be configured to \
     * use Chocolatey instead. This method is used to call the package provider \ 
     * executable or interface with PowerShell, it does not install the package itself.
    */
    //Needs Thread support.
    def installPackage(name : String, provider: String) = {
      val powershell : String = "powershell.exe"
      val aptGet : String = "apt-get install -y"
      val yum : String = "yum install -y"
      
      if(provider == "chocolatey" || provider == "Chocolatey") {
        try {
          val chocolatey = powershell + " choco install -y " + name !
        } 
        catch  {
          case e : Throwable => {
            e.printStackTrace()
          }
        }
      }
      
      else if(provider == "apt-get" || provider == "apt") {
        try {
          val apt = aptGet + " " + name ! 
        }
        catch {
          case e : IOException => {
            e.printStackTrace()
          }
          case i : Throwable => {
            i.printStackTrace()
          }
        }
      }
    
      else if(provider == "yum") {
        try {
          val yumInstall = yum + " " + name ! 
        }
        catch {
          case e : IOException => {
            throw e
          }
          case i : Throwable => {
            throw i
          }
        }
      }
    
      else {
        println(">>> Unknown provider " + provider + " entered.")
      }
    }
    
    // Needs Thread support.
    def uninstallPackage(name: String, provider: String) = {
      val powershell : String = "powershell.exe"
      if(provider == "chocolatey" || provider == "Chocolatey") {
        try {
          println(">>> Package " + name + " from provider " + provider + " is being uninstalled.")
          val chocolatey = powershell + " choco uninstall -y " + name !
        } 
        catch  {
           case e : Throwable =>{
             println(">>> Error: Could not install package because of " + e + ".")
           }
        }
      }
    }
  

    def installWindowsFeature(name : Array[String]) = {
      val powershell : String = "powershell.exe Install-WindowsFeature "
      val featureThread = new Thread( new Runnable {
        def run() {
          for(feature <- name) {
            try {
              powershell + " " + feature
            }
            catch {
              case e : Throwable => {
                e.printStackTrace()
              }
            }
          }
        }
      }).start()
    }
    
    def uninstallWindowsFeature(name : Array[String]) = {
      val powershell : String = "powershell.exe Remove-WindowsFeature "
      val unfeatureThread = new Thread( new Runnable {
        def run() {
          for(feature <- name) {
            try {
              powershell + " " + feature !
            }
            catch {
              case e : Throwable => {
                e.printStackTrace()
              }
            }
          }
        }
      }).start()
    }
    
  }
}