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

import scala.sys.process._
object cellPackage {
  /*
   * @param name Give the name of the required software package that should be installed.
   * @param provider Supply the package provider that will install the requested software.
   */
  def installPackage(name : String, provider : String) {
    val pkgThread = new Thread(new Runnable {
      def run(){
        provider match {
          case "apt" => val install = "apt-get install -y " + name
                        install !
          case "yum" => val install = "yum install -y " + name
                        install !
          case _ => val err = name + " is not a proper provider."
                    println(err)
        }
      }  
    }).run()
  }
  /*
   * @param name Give the name of the package that will be uninstalled
   * by the package provider.
   * @param provider Supply the provider that will be uninstalling the package.
   */
  def uninstallPackage(name : String, provider : String) {
    val pkgThread = new Thread(new Runnable {
      def run() {
        provider match {
          case "apt" => val uninstall = "apt-get remove -y " + name
                        uninstall !
          case "yum" => val install = "yum remove -y " + name
                        install !
          case _ => val err = name + " is not a proper provider."
                    println(err)
        }    
      }
    }).run()
  }
}