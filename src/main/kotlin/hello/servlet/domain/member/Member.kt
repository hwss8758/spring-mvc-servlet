package hello.servlet.domain.member

data class Member(
    var id: Long? = null,
    var username: String? = null,
    var age: Int? = null
) {
    constructor(username: String?, age: Int?) : this() {
        this.username = username
        this.age = age
    }
}
