package hello.servlet.web.servletmvc

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(
    name = "mvcMemberSaveServlet",
    urlPatterns = ["/servlet-mvc/members/save"]
)
class MvcMemberSaveServlet : HttpServlet() {

    val memberRepository = MemberRepository.getMemberInstance()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        val username = request?.getParameter("username")
        val age = request?.getParameter("age")?.toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        //Model에 보관
        request?.setAttribute("member", member)

        val viewPath = "/WEB-INF/views/save-result.jsp"
        val dispatcher = request?.getRequestDispatcher(viewPath)
        dispatcher?.forward(request, response)
    }
}