package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

/*
 * 스프링 빈의 이벤트 라이프 사이클
 * 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입
 * 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 종료
 *
 * 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료 된 후 호출
 * 소멸전 콜백: 빈이 소멸되기 직전에 호출
 *
 * 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원
 *   1. 인터페이스(InitializingBean, DisposableBean)
 *   2. 설정 정보에 초기화 메서드, 종료 메서드 지정
 *   3. @PostConstruct, @PreDestroy 애노테이션 지원
*/

class NetworkClientTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();  // 스프링 컨테이너를 종료, ConfigurableApplicationContext 필요
    }

    @Configuration
    static class LifeCycleConfig {
        // 설정 정보에 초기화 소멸 메서드 지정
        // @Bean(initMethod = "init", destroyMethod = "close")

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}