package hello.servlet.basic.response

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseHtmlServlet", urlPatterns = ["/response-html"])
class ResponseHtmlServlet : HttpServlet() {
    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp?.contentType = "text/html"
        resp?.characterEncoding = "utf-8"

        val writer = resp?.writer
        writer?.println("<html>")
        writer?.println("<body>")
        writer?.println("<div>안녕?</div>")
        writer?.println("</body>")
        writer?.println("</html>")
    }
}