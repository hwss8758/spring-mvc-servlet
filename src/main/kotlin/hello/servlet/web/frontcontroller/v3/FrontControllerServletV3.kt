package hello.servlet.web.frontcontroller.v3

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3() : HttpServlet() {

    private val controllerMap: MutableMap<String, ControllerV3> = hashMapOf()

    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members"] = MemberListControllerV3()
    }

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("FrontControllerServletV3.service")

        val requestURI = req?.requestURI
        val controller = controllerMap[requestURI]

        if (controller == null) {
            resp?.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val paramMap: MutableMap<String, String> = createParamMap(req)
        val mv = controller.process(paramMap)
        val viewName = mv.viewName
        val view = viewResolver(viewName)

        view.render(mv.model, req, resp)
    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/" + viewName + ".jsp")

    private fun createParamMap(req: HttpServletRequest?): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = hashMapOf()
        req?.parameterNames?.asIterator()?.forEachRemaining { paramName ->
            paramMap[paramName] = req.getParameter(paramName)
        }
        return paramMap
    }
}