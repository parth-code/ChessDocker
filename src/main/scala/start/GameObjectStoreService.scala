package start

import org.springframework.stereotype.Service
import pl.art.lach.mateusz.javaopenchess.core.Game


//Stores game object between different REST calls
@Service
class GameObjectStoreService {

  var objectMap:Map[String, Game] = Map()

  def storeinMap(string: String, game: Game): Unit ={
    objectMap += (string->game)
  }

  def getFromMap(name:String):Game = {
    objectMap(name)
  }

  def removeFromMap(name: String)={
    objectMap -= name
  }
}
