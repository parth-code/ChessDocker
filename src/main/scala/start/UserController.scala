package start

import com.typesafe.config.ConfigFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api"))
class UserController {
  @Autowired
  var gameService:GameService = _

  @Autowired
  var userConsumeService:UserConsumeService = _

  val config = ConfigFactory.load("application.conf")
  config.checkValid(ConfigFactory.defaultReference(), "ChessVAP")

  @PostMapping(value = Array("/firststart"), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def startGamesRequest(@RequestBody startEntity: StartEntity, @ResponseBody startEntityReq: StartEntity):StartEntity = {
    val username:String = startEntity.username
    val usercolor:String = startEntity.color
    val from_pos:String = startEntity.from_pos
    val to_pos:String = startEntity.to_pos
    val result = gameService.start(username)
    startEntityReq.username_(result(0))
    startEntityReq.color_(result(1))
    startEntityReq.from_pos_(result(2))
    startEntityReq.to_pos_(result(3))
    //REST TEMPLATE
    if(gameService.checkIfPlayingAgainstAnotherAI) {
      userConsumeService.startGame(startEntityReq)
    }
    startEntityReq
  }

  @PostMapping(value = Array("/start"), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def startRequest(@RequestBody startEntity: StartEntity, @ResponseBody moveEntity: MoveEntity):MoveEntity = {
    val username:String = startEntity.username
    val usercolor:String = startEntity.color
    val from_pos:String = startEntity.from_pos
    val to_pos:String = startEntity.to_pos
    val result = gameService.start(username, usercolor, from_pos, to_pos)
    moveEntity.username_(result(0))
    moveEntity.from_pos_(result(1))
    moveEntity.to_pos_(result(2))
    moveEntity.promoted_(result(3))
    //REST TEMPLATE
    if(gameService.checkIfPlayingAgainstAnotherAI) {
      userConsumeService.sendMoveFromApp(moveEntity)
    }
    moveEntity
  }

  @PostMapping(value = Array("/move"), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def moveRequest(@RequestBody moveEntity: MoveEntity):MoveEntity = {
    val username:String = moveEntity.username
    val from_pos:String = moveEntity.from_pos
    val to_pos:String = moveEntity.to_pos
    val promoted:String = moveEntity.promoted
    if(gameService.checkName(username)) {
      val result = gameService.move(username, from_pos, to_pos, promoted)
      moveEntity.username_(result(0))
      moveEntity.from_pos_(result(1))
      moveEntity.to_pos_(result(2))
      moveEntity.promoted_(result(3))
      if(result(1) != "" && gameService.checkIfPlayingAgainstAnotherAI) {
        //REST TEMPLATE
        userConsumeService.sendMoveFromApp(moveEntity)
      }
    }
    moveEntity
  }

  @GetMapping(value = Array("/end"), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def endRequest(@RequestBody endEntity: EndEntity): EndEntity ={
    val username:String = endEntity.username
    val message:String = endEntity.message
    val result = gameService.end(username, message)
    if(gameService.checkIfPlayingAgainstAnotherAI){
      userConsumeService.sendEndOfGame(endEntity)
    }
    endEntity.username_(result(0))
    endEntity.message_(result(1))
    endEntity
  }
}
