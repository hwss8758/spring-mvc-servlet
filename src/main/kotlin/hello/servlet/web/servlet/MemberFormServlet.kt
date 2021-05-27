package hello.servlet.web.servlet

import hello.servlet.domain.member.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "memberFormServlet",
        urlPatterns = ["/servlet/members/new-form"])
class MemberFormServlet : HttpServlet() {

    private val memberRepository: MemberRepository = MemberRepository.getMemberInstance()

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp?.contentType = "text/html"
        resp?.characterEncoding = "utf-8"

        val w = resp?.writer
        w?.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                "    username: <input type=\"text\" name=\"username\" />\n" +
                "    age:      <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" + "</form>\n" +
                "</body>\n" +
                "</html>\n")
    }
}