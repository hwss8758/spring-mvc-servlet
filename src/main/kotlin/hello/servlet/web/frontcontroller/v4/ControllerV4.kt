package hello.servlet.web.frontcontroller.v4

interface ControllerV4 {
    fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String
}