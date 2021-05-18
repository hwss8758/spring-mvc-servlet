package hello.servlet.basic.response

import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseHeaderServlet", urlPatterns = ["/response-header"])
class ResponseHeaderServlet : HttpServlet() {
    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {

        // status-line
        resp?.status = HttpServletResponse.SC_OK

        // response-header
        //resp?.setHeader("Content-Type", "text/plain")
        resp?.setHeader("Cache-Control", "no-cache, no-store, must-revalidate")
        resp?.setHeader("Pragma", "no-cache")
        resp?.setHeader("my-header", "hello")

        // 헤더 편의메서드
        resp?.contentType = "text/plain"
        resp?.characterEncoding = "utf-8"

        // 쿠키 편의 메서드
        val cookie = Cookie("myCookie", "good")
        cookie.maxAge = 600 // 600초
        resp?.addCookie(cookie)

        // 리다이렉트 편의메서드
        resp?.sendRedirect("/basic/hello-form.html")

        resp?.writer?.write("ok")
   }
}