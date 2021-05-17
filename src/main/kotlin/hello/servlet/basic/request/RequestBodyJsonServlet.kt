package hello.servlet.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import hello.servlet.basic.HelloData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet : HttpServlet() {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val inputStream = req?.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        println("messageBody = ${messageBody}")

        val readValue = objectMapper.readValue(messageBody, HelloData::class.java)

        println("readValue.username = ${readValue.username}")
        println("readValue.age = ${readValue.age}")

    }
}