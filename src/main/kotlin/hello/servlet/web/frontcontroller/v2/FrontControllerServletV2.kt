package hello.servlet.web.frontcontroller.v2

import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV2", urlPatterns = ["/front-controller/v2/*"])
class FrontControllerServletV2() : HttpServlet() {

    private val controllerMap: MutableMap<String, ControllerV2> = hashMapOf()

    init {
        controllerMap.put("/front-controller/v2/members/new-form", MemberFormControllerV2())
        controllerMap.put("/front-controller/v2/members/save", MemberSaveControllerV2())
        controllerMap.put("/front-controller/v2/members", MemberListControllerV2())
    }

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("FrontControllerServletV2.service")

        val requestURI = req?.requestURI
        val controller = controllerMap[requestURI]

        if (controller == null) {
            resp?.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val view = controller.process(req, resp)
        view.render(req, resp)
    }
}