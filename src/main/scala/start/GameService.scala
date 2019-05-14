package start

import com.typesafe.config.ConfigFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.art.lach.mateusz.javaopenchess.JChessApp
import pl.art.lach.mateusz.javaopenchess.core.Game

@Service
class GameService extends GameWrapper {

  @Autowired
  var gameObjectStoreService:GameObjectStoreService = _

  val config = ConfigFactory.load("application.conf")
  config.checkValid(ConfigFactory.defaultReference(), "ChessVAP")

  //For starting the game, if its the first application
  override def start(playerName:String): List[String] = {
    val jchessApp = new JChessApp
    jchessApp.startFunction()
    val game = new Game
    val username:String = config.getString("user2")
    game.newGame(playerName, "White")
    //Storing object in map for another API
    gameObjectStoreService.storeinMap(playerName, game)
    game.nextMove()
    val result:List[String] = username:: "Black" :: game.performComputerMove.toList.dropRight(1)
    result
  }

  //for starting the game, if second
  //TODO:REPLACE all results with HttpStatus
  override def start(playerName: String, playerColor: String, moveFromPos: String, moveToPos: String): List[String] ={
    val jchessApp = new JChessApp
    jchessApp.startFunction()

    //Starting new Game
    val game = new Game
    game.newGame(playerName, playerColor)
    gameObjectStoreService.storeinMap(playerName, game)
//    val username: String = if
//      (game.getSettings.getPlayerBlack.getPlayerType == PlayerType.COMPUTER)
//      game.getSettings.getPlayerBlack.getName
//      else game.getSettings.getPlayerWhite.getName
    val username:String = config.getString("user2")
    game.nextMove(moveFromPos, moveToPos, null)
    //Switching to the next player
    game.nextMove()
    val result:List[String] = username :: game.performComputerMove.toList
//    print(result)
    result
  }

  //for moving the pieces
  override def move(playerName:String, moveFromPos:String, moveToPos:String, promoted:String): List[String] = {
    //get object for session
    val game: Game = gameObjectStoreService.getFromMap(playerName)
    //perform other games moves
    game.nextMove(moveFromPos, moveToPos, promoted)
    //switch to this game
    game.nextMove()
    //check for checkmate or draw
    val res: String = game.computerMove()
//    val username: String = if (game.getSettings.getPlayerBlack.getPlayerType == PlayerType.COMPUTER)
//      game.getSettings.getPlayerBlack.getName
//    else game.getSettings.getPlayerWhite.getName
    val username:String = config.getString("user2")
    //End or complete
    if (res == "Fine") {
      val result: List[String] = username :: game.performComputerMove.toList
      result
    } else {
      end(playerName, res)
      val result: List[String] = List(username, "", "", "")
      result
    }
  }
  //Ending the game
  override def end(playerName:String,message: String): List[String] ={
    val game:Game = gameObjectStoreService.getFromMap(playerName)
    game.endGame(message)
    gameObjectStoreService.removeFromMap(playerName)
    val username:String = config.getString("user2")
    val result:List[String] = List(username,message)
    result
  }

  def checkName(plName:String): Boolean={
    if(plName != config.getString("user2")){
      true
    } else{
      false
    }
  }

  def checkIfPlayingAgainstAnotherAI: Boolean = {
//    print(config.getInt("otherappl"))
    if(config.getInt("otherappl") > 0) {
      true
    }
    else {
      false
    }
  }


}
