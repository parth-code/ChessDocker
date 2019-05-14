package start

import com.typesafe.config.ConfigFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class UserConsumeService() {
//  @PostMapping(value = Array("template/api/move"))
//  def sendMoveRequest(@ResponseBody moveEntity: MoveEntity): Unit ={
//    val headers = new HttpHeaders
//    headers.setAccept(util.Arrays.asList(MediaType.APPLICATION_JSON))
//    val entity = new HttpEntity[MoveEntity](moveEntity, headers)
//    restTemplate.exchange("http://localhost:8080/api/move", HttpMethod.POST, entity, moveEntity.getClass).getBody
//  }
  var restTemplate = new RestTemplate

  import org.springframework.http.converter.StringHttpMessageConverter
  import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

  restTemplate.getMessageConverters.add(new MappingJackson2HttpMessageConverter)
  restTemplate.getMessageConverters.add(new StringHttpMessageConverter)

  val config = ConfigFactory.load("application.conf")
  config.checkValid(ConfigFactory.defaultReference(), "ChessVAP")

  val startURI:String = config.getString("startURI")
  val moveURI:String = config.getString("moveURI")
  val endURI:String = config.getString("endURI")

  def UserConsumeService(restTemplateBuilder:RestTemplateBuilder) = {
    this.restTemplate = restTemplateBuilder.build()
  }

  restTemplate.setErrorHandler(new ErrorHandler)

  //Used to make other game start when the player is White and firstgame is called
  @throws(classOf[Exception])
  def startGame(startEntity: StartEntity): Unit = {
//    val headers = new HttpHeaders
//    headers.set("Content-Type", "application/json")
//    val starting: HttpEntity[StartEntity]  = new HttpEntity[StartEntity](startEntity, headers)
    val response = restTemplate.postForObject(startURI, startEntity, classOf[String])
  }


  @throws(classOf[Exception])
  def sendMoveFromApp(moveEntity:MoveEntity): Unit = {
//    val headers = new HttpHeaders
//    headers.set("Content-Type", "application/json")
//    val moving: HttpEntity[MoveEntity]  = new HttpEntity[MoveEntity](moveEntity, headers)
    val response = restTemplate.postForObject(moveURI, moveEntity, classOf[String])
  }

  @throws(classOf[Exception])
  def sendEndOfGame(endEntity: EndEntity):Unit = {
    val response = restTemplate.postForObject(endURI, endEntity, classOf[String])
  }
}
