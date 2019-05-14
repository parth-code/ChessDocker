package start

import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.DefaultResponseErrorHandler

@Component
class ErrorHandler extends DefaultResponseErrorHandler{
  override def handleError(response: ClientHttpResponse): Unit ={
    //do nothing
  }
}
