package hello.hello_spring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import hello.hello_spring.domain.Member;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void aftereach(){
        repository.clearStore();
    }
    
    @Test
    public void save(){
        Member member = new Member();
        member.setName("James");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("James");
        member2.setName("Rusy");
        repository.save(member1);
        repository.save(member2);
        assertThat((member1)).isEqualTo(repository.findByName(member1.getName()).get());
        assertThat((member2)).isEqualTo(repository.findByName(member2.getName()).get());
    }
    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("James");
        member2.setName("Rusy");
        repository.save(member1);
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
