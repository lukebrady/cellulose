

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
              path = "C:\\Users\\lbrad23105\\Desktop\\test.txt", 
              content = "Hello World" )
  }
}