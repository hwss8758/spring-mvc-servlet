package hello.servlet.web.servlet

import hello.servlet.domain.member.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "memberListServlet",
        urlPatterns = ["/servlet/members"])
class MemberListServlet : HttpServlet() {

    private val memberRepository: MemberRepository = MemberRepository.getMemberInstance()

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val findAll = memberRepository.findAll()

        resp?.contentType = "text/html"
        resp?.characterEncoding = "utf-8"

        val w = resp?.writer
        w?.write("<html>")
        w?.write("<head>")
        w?.write("    <meta charset=\"UTF-8\">")
        w?.write("    <title>Title</title>")
        w?.write("</head>")
        w?.write("<body>")
        w?.write("<a href=\"/index.html\">메인</a>")
        w?.write("<table>")
        w?.write("    <thead>")
        w?.write("    <th>id</th>")
        w?.write("    <th>username</th>")
        w?.write("    <th>age</th>")
        w?.write("    </thead>")
        w?.write("    <tbody>")


        for (member in findAll) {
            w?.write("    <tr>")
            w?.write("<td>" + member.id + "</td>")
            w?.write("<td>" + member.username + "</td>")
            w?.write("<td>" + member.age + "</td>")
            w?.write("    </tr>")
        }
        w?.write("    </tbody>")
        w?.write("</table>")

        w?.write("</body>")
        w?.write("</html>")
    }
}
