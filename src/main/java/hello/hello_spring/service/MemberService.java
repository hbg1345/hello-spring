package hello.hello_spring.service;

import hello.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
public class MemberService {
    private final MemberRepository repository;
    
    public MemberService(MemberRepository repository){
        this.repository = repository;
    }
    
    // 회원가입
    public Long join(Member member){
        checkDuplicateName(member);
        repository.save(member);
        return member.getId();
    }

    private void checkDuplicateName(Member member) {
        repository.findByName(member.getName()).ifPresent(
            m -> {throw new IllegalStateException("중복된 닉네임입니다.");}
        );
    }
    
    // 전체 회원 조회
    public List<Member> findMembers(){
        return repository.findAll();
    }

    // 아이디로 정보 가져오기
    public Optional<Member> findOne(Long id){
        return repository.findById(id);
    }
}   
