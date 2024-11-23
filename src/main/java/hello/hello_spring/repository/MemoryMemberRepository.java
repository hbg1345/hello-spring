package hello.hello_spring.repository;

import java.util.List;
import java.util.Optional;


import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import hello.hello_spring.domain.Member;


public class MemoryMemberRepository implements MemberRepository{
    private Map<Long, Member> store = new HashMap<>();
    private long sequence = 0l;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(sequence, member);
        return member;
    }
    
    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream().filter(
            member -> member.getName().equals(name)
        ).findAny();
    }
    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    
    public void clearStore(){
        store.clear();
    }
    
}
