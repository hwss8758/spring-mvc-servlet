package hello.servlet.basic.response

import com.fasterxml.jackson.databind.ObjectMapper
import hello.servlet.basic.HelloData
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet : HttpServlet() {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {

        resp?.contentType = "application/json"
        resp?.characterEncoding = "utf-8"

        val helloData = HelloData(username = "kim", age = 20)
        val result = objectMapper.writeValueAsString(helloData)

        resp?.writer?.write(result)
    }
}