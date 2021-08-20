package hello.servlet.web.frontcontroller.v5.adapter

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV3HandlerAdapter : MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return (handler is ControllerV3)
    }

    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any): ModelView {
        val controller = handler as ControllerV3

        val paramMap: MutableMap<String, String> = createParamMap(request)

        val mv = controller.process(paramMap)
        return mv
    }

    private fun createParamMap(request: HttpServletRequest?): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = hashMapOf()
        request?.parameterNames?.asIterator()?.forEachRemaining { paramName ->
            paramMap[paramName] = request.getParameter(paramName)
        }
        return paramMap
    }
}