package hello.servlet.web.frontcontroller.v1.controller

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.v1.ControllerV1
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberSaveControllerV1 : ControllerV1 {

    val memberRepository = MemberRepository.getMemberInstance()

    override fun process(request: HttpServletRequest?, response: HttpServletResponse?) {

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