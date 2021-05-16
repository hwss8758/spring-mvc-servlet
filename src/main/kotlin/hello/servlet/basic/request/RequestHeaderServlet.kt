package hello.servlet.basic.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet : HttpServlet() {
    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        printStartLine(req)
        printHeaders(req)
        printHeaderUtils(req)
    }

    fun printStartLine(request: HttpServletRequest?) {
        println("--- REQUEST-LINE - start ---")
        println("request?.getMethod() = " + request?.method); //GET
        println("request?.getProtocal() = " + request?.protocol); // HTTP/1.1
        println("request?.getScheme() = " + request?.scheme); //http
        println("request?.getRequestURL() = " + request?.requestURL);
        println("request?.getRequestURI() = " + request?.requestURI);
        println("request?.getQueryString() = " + request?.queryString);
        println("request?.isSecure() = " + request?.isSecure); //https 사용 유무
        println("--- REQUEST-LINE - end ---");
        println();
    }

    fun printHeaders(request: HttpServletRequest?) {
        println("--- HEADERS - start ---")

        request?.headerNames?.asIterator()?.forEachRemaining { headerName ->
            println("$headerName : $headerName ")
        }

        println("--- HEADERS - end ---")
    }

    // Header 편리한 조회
    fun printHeaderUtils(request: HttpServletRequest?) {
        println("--- Header 편의 조회 start ---")
        println("[Host 편의 조회]");
        println("request?.serverName : " + request?.serverName) // host header
        println("request?.serverPort : " + request?.serverPort) // host port
        println()

        println("[Accept-Language 편의 조회]")
        request?.locales?.asIterator()?.forEachRemaining {
            println("locale = $it")
        }
        println("requset?.locale : ${request?.locale}")
        println()

        println("[cookie 편의 조회]")
        if (request?.cookies != null) {
            for (cookie in request.cookies) {
                println("${cookie.name} : ${cookie.value}")
            }
        }
        println()

        println("[Content 편의 조회]")
        println("request?.contentType : ${request?.contentType}")
        println("request?.contentLength : ${request?.contentLength}")
        println("request?.characterEncoding : ${request?.characterEncoding}")

        println("--- Header 편의 조회 end ---")
        println()
    }
}