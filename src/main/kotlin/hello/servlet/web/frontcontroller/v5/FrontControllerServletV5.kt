package hello.servlet.web.frontcontroller.v5

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV5", urlPatterns = ["/front-controller/v5/*"])
class FrontControllerServletV5 : HttpServlet() {
    private val handlerMappingMap: MutableMap<String, Any> = hashMapOf()
    private val handlerAdapters: MutableList<MyHandlerAdapter> = arrayListOf()

    init {
        initHandlerMappingMap()
        initHandlerAdapters()
    }

    private fun initHandlerMappingMap() {
        handlerMappingMap["/front-controller/v5/v3/members/new-form"] = MemberFormControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members/save"] = MemberSaveControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members"] = MemberListControllerV3()
    }

    private fun initHandlerAdapters() {
        handlerAdapters.add(ControllerV3HandlerAdapter())
    }

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val handler = getHandler(req)
        if (handler == null) {
            resp?.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val adapter = getHandlerAdapter(handler)
        val mv = adapter.handle(req, resp, handler)

        val viewName = mv.viewName

        viewResolver(viewName).render(mv.model, req, resp)
    }

    private fun getHandlerAdapter(handler: Any?): MyHandlerAdapter {

        handlerAdapters.forEach {
            if (handler?.let { it1 -> it.supports(it1) } == true) return it
        }
        throw IllegalArgumentException("handler adapter를 찾을 수 없습니다.")
    }

    private fun getHandler(req: HttpServletRequest?): Any? {
        val requestURI = req?.requestURI
        return handlerMappingMap[requestURI]
    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/" + viewName + ".jsp")
}