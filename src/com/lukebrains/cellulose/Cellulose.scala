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

class Cellulose {
  object cell {
    
    def metaThreads(threads : Array[Thread]) {
      val watcher = new MetaThread()
      
      val t = threads.foreach { x => watcher.watch(x) }
      
      println(t)
      
    }
    
    /*
     * After Assemble OS type check for run compatibility.
     * This allows Warden to run on any OS without \
     * configuration.
     */
    // Will be implemented soon. val globalWritePath : PrintWriter = new PrintWriter(new File("/etc/wimba/warden/run_logs/"))
    
    def file(name: String, path: String, content: String, ensure: String) = {
      val fileThread = new CallableThread()
      val fileCheckThread = new Thread(new Runnable {
        def run() {
          while(true) {
            fileThread.CallableThread.fileCreationThread(name, path, content, ensure)
            Thread.sleep(2000)
          }
        }
      }).start()
    }
    
    // This needs to be changed at some point.
    def maintainFile(path : String, content : String) = {
      val thread = new CallableThread()
      val maintainFileThread = new Thread(new Runnable {
        def run() {
          while(true) {
            thread.CallableThread.maintainFileThread(path,content)
            Thread.sleep(2000)
          }
        }
      }).start()
    }
    
    /*
     * This method checks to see if the files you define if code remain the same.
     * Warden will compare the contents of the configuration file to that of the file.
     * This allows Warden to change back any files that may have been tampered with.
     * @param path: The path that your file has been saved. Defined in configuration file.
     * @param name : The name of the file that needs to be read.
     * @param compareObject : Object or List that will be compared against the created file.
     * 
     */
    
    def directory(path: String, ensure: String = "Absent") = {
      val dirThread = new CallableThread
      val dirCheckThread = new Thread(new Runnable {
        def run() {
          while(true) {
            dirThread.CallableThread.directoryCreationThread(path, ensure)
            Thread.sleep(2000)
          }
        }
      }).start()
    }
    
    /*
     * Script is a method designed to interface directly with PowerShell.
     * This allows a user to use a single script defined in the script param or \
     * use an array of scripts for system setup. This command is very powerful when \ 
     * used with the git resource.
     * @param script : Specify a script directly using code.
     * @param path: Define the path to a single script on your machine.
     * @param directory: Define a directory containing multiple scripts.
     */
    
    def powershell(script : String = null, path : Array[String] = null, directory : String = null) = {
      val c = new CallableThread
      val powershellThread = new Thread(new Runnable {
        def run() {
          c.CallableThread.powershellThread(script, path, directory)
        }
      }).start()
    }
    
    def installPackage(name : String, provider : String) {
      val c = new CallableThread
      val installPackageThread = new Thread(new Runnable {
        def run() {
          c.CallableThread.installPackage(name, provider) 
        }
      }).start()
    }
    
    def installWindowsFeature(name : Array[String]) = {
      val c = new CallableThread
      val featureThread = new Thread(new Runnable {
        def run() {
          c.CallableThread.installWindowsFeature(name)
        }
      }).start()
    }
    
    def uninstallWindowsFeature(name : Array[String]) = {
      val c = new CallableThread
      val featureThread = new Thread(new Runnable {
        def run() {
          c.CallableThread.uninstallWindowsFeature(name)
        }
      }).start()
    }
    
    
    // Past this point needs work! //
    
    /*
     * Create Git repository for cloning app code into.
     * @param url: Define the url's in which your code will come from.
     * @param path: Define the path of the repo on your machine.
     * @param ensure: Define if the repo is present of removed from the machine.
     */
    @Override
    def gitRepository(path :String, url : String, ensure : String  = "Absent") = {
      val git : String = "git clone"
      val init : String = "git init"
      
      if(ensure == "Present" || ensure == "present") {
        val repo : File = new File(path)
        val cd = "cd"
        repo.mkdir()
        try {
          println(">>> Creating git repository.") 
          cd + " " + path ;init + " " ; git + " " + url !
        }
        catch {
          case io : IOException => {
            println(">>> Repo could not be created due to " + io + ".")
          }
          case fe : FileNotFoundException => {
            println(">>> Repo could not be created due to " + fe + ".")
          }
        }
      }
    }
    ///////////////////////////////////////////////////////////////////////
  }
}