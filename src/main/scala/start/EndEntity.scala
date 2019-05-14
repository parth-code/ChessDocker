package start

import com.fasterxml.jackson.annotation.JsonProperty

class EndEntity {
  @JsonProperty("username")
  private var _username:String = ""
  @JsonProperty("message")
  private var _message:String = ""

  //Getter
  def username = _username
  //Setter
  def username_(value:String) = _username = value
  //Getter
  def message = _message
  //Setter
  def message_(value:String) = _message = value

}
