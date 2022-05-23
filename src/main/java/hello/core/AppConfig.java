package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    /*
     * AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
     * AppConfig는 생성한 객체의 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입(연결)해준다.
     *
     * MemberServiceImpl 입장에서 생성자를 통해서 어떤 객체(구현체)가 들어올지(주입될지)는 알 수 없다.
     * MemberServiceImpl 생성자를 통해서 어떤 구현 객체를 주입할지는 외부(AppConfig)에서 결정된다.
     * MemberServiceImpl는 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.
     *
     * 객체의 생성과 연결은 AppConfig가 담당한다.
     *  -> 관심사의 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
     *
     * AppConfig의 등장으로 애플리케이션이 크게 사용 영역과,
     * 객체를 생성하고 구성(Configuration)하는 영역으로 분리되었다.
     */

    public MemberService memberService() {

        /*
         * memoryMemberRepository 객체를 생성하고 그 참조값을  MemberServiceImpl을 생성하면서 생성자로 전달한다.
         * 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서
         * DI(Dependency Injection), 의존관계 주입 또는 의존성 주입이라 한다.
         */

        // return new MemberServiceImpl(new MemoryMemberRepository());
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        // return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {

        /*
         * AppConfig에서 할인 정책 역할을 담당하는 구현을
         * FixDiscountPolicy에서 RateDiscountPolicy 객체로 변경했다.
         * 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다.
         */

        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
