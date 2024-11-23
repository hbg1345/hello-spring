package hello.hello_spring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        MemoryMemberRepository repository = new MemoryMemberRepository();
        memberService = new MemberService(repository); //Dependency Injection
        // memberService가 repository에 의존해야만 가능하다.
    }

    @Test
    void testJoin() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("a");
        member2.setName("v");
        Long saveId = memberService.join(member1);
        Member foundmember = memberService.findOne(saveId).get();
        Assertions.assertThat(foundmember).isEqualTo(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, 
                                ()->memberService.join(member1));
        Assertions.assertThat(e.getMessage()).isEqualTo("중복된 닉네임입니다.");
    }

    @Test
    void testFindMembers() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("James");
        member2.setName("Rusy");
        memberService.join(member1);
        memberService.join(member2);
        //when
        List<Member> result = memberService.findMembers();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void testFindOne() {
        Member member1 = new Member();
        Long id = memberService.join(member1);
        System.out.println(id);
        Assertions.assertThat(memberService.findOne(id).get()).isEqualTo(member1);
    }
}
