package hello.servlet.domain.member

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class MemberRepositoryTest {

    val memberRepository = MemberRepository.getMemberInstance()

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun save() {
        //given
        val member = Member(username = "hello", age = 20)

        //when
        val save = memberRepository.save(member)

        //than
        val findById = memberRepository.findById(save.id!!)

        assertThat(findById).isEqualTo(save)
    }

    @Test
    fun findALL() {
        //given
        val member1 = Member(username = "member1", age = 20)
        val member2 = Member(username = "member2", age = 30)

        //when
        memberRepository.save(member1)
        memberRepository.save(member2)

        //than
        val result = memberRepository.findAll()

        println("result = ${result}")
        assertThat(result.size).isEqualTo(2)
        assertThat(result).contains(member1, member2)
    }

}