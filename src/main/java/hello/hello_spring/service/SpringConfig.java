package hello.hello_spring.service;

import java.sql.Time;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {
    // private DataSource dataSource;
   
    private final MemberRepository memberRepository;
    
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
    // @Bean
    // public TimeTraceAop timeTraceAop(){
    //     return new TimeTraceAop();
    // }
    // @Bean
    // public MemberRepository memberRepository(){
    //     // return new MemoryMemberRepository();
    //     // return new JdbcTemplateMemberRepository(dataSource);
    //     return new JpaMemberRepository(em);
    // }

}
