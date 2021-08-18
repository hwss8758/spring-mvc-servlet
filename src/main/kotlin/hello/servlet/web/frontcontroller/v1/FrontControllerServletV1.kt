package hello.servlet.web.frontcontroller.v1

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV1", urlPatterns = ["/front-controller/v1/*"])
class FrontControllerServletV1() : HttpServlet() {

    private val controllerMap: MutableMap<String, ControllerV1> = hashMapOf()

    init {
        controllerMap.put("/front-controller/v1/members/new-form", MemberFormControllerV1())
        controllerMap.put("/front-controller/v1/members/save", MemberSaveControllerV1())
        controllerMap.put("/front-controller/v1/members", MemberListControllerV1())
    }

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("FrontControllerServletV1.service")

        val requestURI = req?.requestURI
        val controller = controllerMap[requestURI]

        if (controller == null) {
            resp?.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        controller.process(req, resp)
    }
}