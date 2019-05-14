package start

import com.fasterxml.jackson.annotation.JsonProperty

class MoveEntity {
  @JsonProperty("username")
  private var _username:String = ""
  @JsonProperty("from_pos")
  private var _from_pos:String = ""
  @JsonProperty("to_pos")
  private var _to_pos:String = ""
  @JsonProperty("promoted")
  private var _promoted:String = ""

  //Getter
  def username = _username
  //Setter
  def username_(value:String) = _username = value
  //Getter
  def from_pos = _from_pos
  //Setter
  def from_pos_(value:String) = _from_pos = value
  //Getter
  def to_pos = _to_pos
  //Setter
  def to_pos_(value:String) = _to_pos = value
  //Getter
  def promoted = _promoted
  //Setter
  def promoted_(value:String) = _promoted = value
}
