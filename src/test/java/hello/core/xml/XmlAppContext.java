package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

    /*
     * 최근에는 스프링 부트를 많이 사용하면서 XML기반의 설정은 잘 사용하지 않는다.
     * 아직 많은 레거시 프로젝트들이 XML로 되어 있고,
     * 또 XML을 사용하면, 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점도 있다.
     *
     * src/main/resources/appConfig.xml
     * https://spring.io/projects/spring-framework
     */

    @Test
    void xmlAppContext() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
