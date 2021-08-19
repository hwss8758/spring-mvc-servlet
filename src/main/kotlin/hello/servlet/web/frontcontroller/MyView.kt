package hello.servlet.web.frontcontroller

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyView(private val viewPath: String) {

    @Throws(ServletException::class, IOException::class)
    fun render(request: HttpServletRequest?, response: HttpServletResponse?) {
        val dispatcher = request?.getRequestDispatcher(viewPath)
        dispatcher?.forward(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    fun render(model: MutableMap<String, Any>, request: HttpServletRequest?, response: HttpServletResponse?) {
        modelToRequestAttribute(model, request)
        val dispatcher = request?.getRequestDispatcher(viewPath)
        dispatcher?.forward(request, response)
    }

    private fun modelToRequestAttribute(
        model: MutableMap<String, Any>,
        request: HttpServletRequest?
    ) {
        model.forEach { (key, value) -> request?.setAttribute(key, value) }
    }
}