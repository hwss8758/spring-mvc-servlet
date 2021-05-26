package hello.servlet.domain.member

class MemberRepository private constructor() {

    companion object {
        val store: MutableMap<Long, Member?> = hashMapOf()
        var sequence: Long = 0L

        // 실글톤 처리
        val instance = MemberRepository()

        fun getMemberInstance(): MemberRepository {
            return instance
        }
    }

    fun save(member: Member): Member {
        member.id = sequence + 1
        sequence += 1
        store.put(member.id!!, member)
        return member
    }

    fun findById(id: Long): Member = store[id]!!

    fun findAll(): MutableList<Member> {

        val mutableListOf = mutableListOf<Member>()

        for (value in store.values) {
            mutableListOf.add(value!!)
        }

        return mutableListOf
    }

    fun clearStore() {
        store.clear()
    }


}


