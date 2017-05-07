

import com.lukebrains.cellulose.matricula._
import com.lukebrains.cellulose.CallableThread

object main {
  def main(args : Array[String]) : Unit = {
    val c = new CallableThread()
    val cellOne = c.cell
    val cellTwo = c.cell
    val mt = matriculaIO

    // Cell One Jobs will occur first.///////////////////////////////
    cellOne.file (
              name = "test.txt", 
              path = "C:\\Users\\lbrad23105\\Desktop\\example\\", 
              content = "Hello World", 
              ensure = "Present")
    cellOne.directory (
                   path = "C:\\Users\\lbrad23105\\Desktop\\example\\test2\\", 
                   ensure = "present")
    cellOne.file (
              name = "test2.txt", 
              path = "C:\\Users\\lbrad23105\\Desktop\\example\\", 
              content = "Hello World", 
              ensure = "Present")
    //////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////
    // Cell Two Jobs will go last.
    // This is how we prevent race conditions and deadlocks.
    cellTwo.file(
              name = "test.txt", 
              path = "C:\\Users\\lbrad23105\\Desktop\\example\\test2", 
              content = "Hello World", 
              ensure = "Present")
    cellTwo.directory(
                   path = "C:\\Users\\lbrad23105\\Desktop\\example\\test2\\testrace", 
                   ensure = "present")
    cellTwo.maintainFile(
                  path = "C:\\Users\\lbrad23105\\Desktop\\example\\test2\\test.txt", 
                  content = "Wow this is really cool.")
  }
}