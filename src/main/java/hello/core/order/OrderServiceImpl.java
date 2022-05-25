package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService {

    /*
     * 1. 할인 정책을 변경해야 한다면?
     *  new FixDiscountPolicy() -> new RateDiscountPolicy()
     *  구현체의 소스 코드 변경이 일어난다.
     *
     * 2. 인터페이스에만 의존하도록 코드를 변경한다면?
     *  private DiscountPolicy discountPolicy;
     *  NPE(null pointer exception)가 발생
     *
     * 3. 해결방안
     *  OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.
     *
     * 4. AppConfig의 등장
     *  애플리케이션 전체 동작 방식을 구성(config)하기 위해,
     *  구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만들어 보자.
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    
    // 테스트 용도
    // public MemberRepository getMemberRepository() { return memberRepository; }

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
