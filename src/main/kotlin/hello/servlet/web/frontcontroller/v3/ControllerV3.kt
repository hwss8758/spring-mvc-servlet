package hello.servlet.web.frontcontroller.v3

import hello.servlet.web.frontcontroller.ModelView

interface ControllerV3 {
    fun process(paramMap: Map<String, String>): ModelView
}