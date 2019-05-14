package start

import com.fasterxml.jackson.annotation.JsonProperty

class StartEntity {
  @JsonProperty("username")
  private var _username:String = ""
  @JsonProperty("color")
  private var _color:String = ""
  @JsonProperty("from_pos")
  private var _from_pos:String = ""
  @JsonProperty("to_pos")
  private var _to_pos:String = ""
  

  //Getter
  def username = _username
  //Setter
  def username_(value:String) = _username = value
  //Getter
  def color = _color
  //Setter
  def color_(value:String) = _color = value
  //Getter
  def from_pos = _from_pos
  //Setter
  def from_pos_(value:String) = _from_pos = value
  //Getter
  def to_pos = _to_pos
  //Setter
  def to_pos_(value:String) = _to_pos = value
}
