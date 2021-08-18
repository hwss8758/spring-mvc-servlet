package hello.servlet.web.frontcontroller.v2.controller

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v2.ControllerV2
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberSaveControllerV2 : ControllerV2 {

    val memberRepository = MemberRepository.getMemberInstance()

    override fun process(request: HttpServletRequest?, response: HttpServletResponse?): MyView {
        val username = request?.getParameter("username")
        val age = request?.getParameter("age")?.toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        //Model에 보관
        request?.setAttribute("member", member)

        return MyView("/WEB-INF/views/save-result.jsp")
    }
}