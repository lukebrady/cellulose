

import com.lukebrains.cellulose.matricula._
import com.lukebrains.cellulose.versioning.Version
import com.lukebrains.cellulose.CallableThread
import java.util.ArrayList

object main {
  def main(args : Array[String]) : Unit = {
    val c = new CallableThread()
    val cellOne = c.cell
    val cellTwo = c.cell
    val mt = matriculaIO
    val configArray = new ArrayList[Object]()
    val version = new Version()
    cellOne.file (
              path = "C:/Users/lbrad23105/Desktop/test.txt", 
              content = "Hello World" )
    // Cell One Jobs will occur first.///////////////////////////////
    configArray.add("""cellOne.file (
              path = "C:\\Users\\lbrad23105\\Desktop\\test.txt", 
              content = "Hello World" )
              psgkpskg;sd
              klsgksl;gklsdgs
              skldgkl;sdkg;lsdkgl;sdkgl;sdg
              kll;skdg;glksd;glksdgl;ksdgl;sd
              
              kslgfsdk;glksdl;gkkkksldllsdl;sd;gfksg
              
              skl;gksdg;ksd;glksdl;gksdglsdgk""")
    version.initializeVersion(19)
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    version.cacheConfiguration(configArray, "C:/Users/lbrad23105/Desktop/cache/")
    
    val config = version.restoreConfiguration(23, "C:/Users/lbrad23105/Desktop/cache")
    println(config)
  }
}