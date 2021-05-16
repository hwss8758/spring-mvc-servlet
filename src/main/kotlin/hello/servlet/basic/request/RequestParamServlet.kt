package hello.servlet.basic.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 1. 파라키터 전송 기능
 * http://localhost:8080/request-param?username=kim&username=hwang&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet: HttpServlet() {
    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("RequestParamServlet.service")
        println("[전체파라미터 조회] - start")

        req?.parameterNames?.asIterator()?.forEachRemaining {
            paramName -> println("${paramName} = ${req?.getParameter(paramName)}")
        }

        println("[전체파라미터 조회] - end")
        println()

        println("[단일파라미터 조회] - start")
        val username = req?.getParameter("username")
        val age = req?.getParameter("age")

        println("username = ${username}")
        println("age = ${age}")
        println()

        println("[이름이 같은 복수 파라미터 조회]")
        val usernames = req?.getParameterValues("username")
        if (usernames != null) {
            for (username in usernames) {
                println("username = ${username}")
            }
        }
        println("[단일파라미터 조회] - end")
        println()

        resp?.writer?.write("ok")
    }
}