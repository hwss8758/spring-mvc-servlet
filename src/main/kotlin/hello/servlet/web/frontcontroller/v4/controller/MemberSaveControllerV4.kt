package hello.servlet.web.frontcontroller.v4.controller

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.v4.ControllerV4

class MemberSaveControllerV4 : ControllerV4 {

    val memberRepository = MemberRepository.getMemberInstance()

    override fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String {
        val username = paramMap["username"]
        val age = paramMap["age"]?.toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        model["member"] = member
        return "save-result"
    }
}