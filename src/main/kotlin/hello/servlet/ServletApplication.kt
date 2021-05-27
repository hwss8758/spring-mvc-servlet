package hello.servlet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
open class ServletApplication

fun main(args: Array<String>) {
	runApplication<ServletApplication>(*args)
}
