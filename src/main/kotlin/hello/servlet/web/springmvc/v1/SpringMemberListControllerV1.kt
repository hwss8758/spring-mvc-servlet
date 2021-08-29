package hello.servlet.web.springmvc.v1

import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.ModelView
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SpringMemberListControllerV1 {

    val memberRepository = MemberRepository.getMemberInstance()

    @RequestMapping("/springmvc/v1/members")
    fun process(): ModelAndView {

        val members = memberRepository.findAll()
        val mv = ModelAndView("members")
        mv.addObject("members", members)

        return mv
    }
}