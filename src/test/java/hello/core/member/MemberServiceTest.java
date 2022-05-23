package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class MemberServiceTest {
    // MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    // 각 테스트를 실행하기 전에 호출된다.
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        assertThat(member).isEqualTo(findMember);
    }
}