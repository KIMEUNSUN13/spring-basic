package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * @Configuration과 바이트 코드
 * 스프링 컨테이너 싱글톤 레지스트리다.
 * 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다.
 * 그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.
 */

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 모두 같은 인스턴스를 참고하고 있다.
        System.out.println("memberService -> memberRepository= " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository= " + orderService.getMemberRepository());
        System.out.println("memberRepository= " + memberRepository);

        // 모두 같은 인스턴스를 참조하고 있다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean= " + bean.getClass());
        // 출력: bean= class hello.core.AppConfig$$EnhancerBySpringCGLIB$$5bbd9900
    }
}
