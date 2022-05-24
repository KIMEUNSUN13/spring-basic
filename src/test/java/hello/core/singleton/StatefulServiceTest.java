package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;


class StatefulServiceTest {

    /*
     * 예를들어, ThreadA가 사용자A 코드를 호출하고, ThreadB가 사용자B 코드를 호풀한다고 가정했을 때,
     * StatefulService의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
     * 이러한 경우에 장애가 발생할 수 있다.
     * 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자.
     */

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}