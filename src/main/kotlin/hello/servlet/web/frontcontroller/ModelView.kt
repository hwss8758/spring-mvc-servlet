package hello.servlet.web.frontcontroller

data class ModelView(
    val viewName: String,
    var model: MutableMap<String, Any> = hashMapOf()
)