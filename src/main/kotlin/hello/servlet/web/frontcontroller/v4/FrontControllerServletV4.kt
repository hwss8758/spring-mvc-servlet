package hello.servlet.web.frontcontroller.v4

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4() : HttpServlet() {

    private val controllerMap: MutableMap<String, ControllerV4> = hashMapOf()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("FrontControllerServletV4.service")

        val requestURI = req?.requestURI
        val controller = controllerMap[requestURI]

        if (controller == null) {
            resp?.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val paramMap: MutableMap<String, String> = createParamMap(req)
        val model: MutableMap<String, Any> = hashMapOf()

        val viewName = controller.process(paramMap, model)
        val view = viewResolver(viewName)

        view.render(model, req, resp)
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