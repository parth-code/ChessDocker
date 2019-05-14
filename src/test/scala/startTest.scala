import org.junit.runner.RunWith
import org.scalatest.{FunSuite, GivenWhenThen}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestContextManager
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import start.{GameService, StartEntity, UserController}

@RunWith(classOf[SpringRunner]) //1
@WebMvcTest(Array(classOf[UserController]))
class startTest extends FunSuite with GivenWhenThen {

  var mvc: MockMvc = _
  @MockBean //3
  val gameService: GameService = null

  new TestContextManager(this.getClass).prepareTestInstance(this)

  test("start game") {
    var startEntity: StartEntity = null
    startEntity.username_("Bob")
    startEntity.color_("Black")
    startEntity.from_pos_("a2")
    startEntity.to_pos_("a3")
    When("posting request")
    val result = mvc.perform(
      post(s"/api/start").content(asJsonString(startEntity)).
        contentType("application/json").accept("application/json"))
    Then("return move")
    result.andExpect(status.isOk)
  }


  import com.fasterxml.jackson.databind.ObjectMapper

  def asJsonString(obj: Any): String = try
    new ObjectMapper().writeValueAsString(obj)
  catch {
    case e: Exception =>
      throw new RuntimeException(e)
  }
}