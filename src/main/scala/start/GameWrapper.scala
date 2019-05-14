package start

import org.springframework.stereotype.Service

@Service
trait GameWrapper{
  def start(playername:String):List[String]

  def start(playerName: String, playerColor: String, moveFromPos: String, moveToPos: String):List[String]

  def move(playerName:String, playerColor:String, moveFromPos:String, moveToPos:String):List[String]

  def end(playerName:String, message: String):List[String]
}


