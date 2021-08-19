package hello.servlet.web.frontcontroller

data class ModelView(
    val viewName: String,
    val model: MutableMap<String, Any> = hashMapOf()
)