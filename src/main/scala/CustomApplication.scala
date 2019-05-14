import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

object CustomApplication{
  def main(args: Array[String]) : Unit = SpringApplication.run(classOf[CustomApplication])
}
@ComponentScan(Array("start"))
@SpringBootApplication
class CustomApplication {
}