package hello.servlet.web.servletmvc

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(
    name = "mvcMemberFormServlet",
    urlPatterns = ["/servlet-mvc/members/new-form"]
)
class MvcMemberFormServlet : HttpServlet() {
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        val viewPath = "/WEB-INF/views/new-form.jsp"
        val dispatcher = request?.getRequestDispatcher(viewPath)
        dispatcher?.forward(request, response)
    }
}