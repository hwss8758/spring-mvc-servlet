package hello.servlet.web.frontcontroller.v5.adapter

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v4.ControllerV4
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter : MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return (handler is ControllerV4)
    }

    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any): ModelView {
        val controller = handler as ControllerV4

        val paramMap: MutableMap<String, String> = createParamMap(request)
        val model: MutableMap<String, Any> = hashMapOf()

        val viewName = controller.process(paramMap, model)

        val mv = ModelView(viewName)
        mv.model = model

        return mv
    }

    private fun createParamMap(req: HttpServletRequest?): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = hashMapOf()
        req?.parameterNames?.asIterator()?.forEachRemaining { paramName ->
            paramMap[paramName] = req.getParameter(paramName)
        }
        return paramMap
    }
}