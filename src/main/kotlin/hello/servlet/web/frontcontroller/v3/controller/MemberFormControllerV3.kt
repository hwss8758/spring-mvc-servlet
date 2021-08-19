package hello.servlet.web.frontcontroller.v3.controller

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3

class MemberFormControllerV3 : ControllerV3 {
    override fun process(paramMap: Map<String, String>): ModelView {
        return ModelView("new-form")
    }
}