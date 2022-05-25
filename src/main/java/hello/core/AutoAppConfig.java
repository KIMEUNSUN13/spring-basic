package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @Filter(type=FilterType.ANNOTATION, classes=Configuration.class))
public class AutoAppConfig {

    /*
     * 컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에,
     * AppConfig, TestConfig 등 이미 만들어두었던 설정 정보도 함께 등록되고, 실행된다.
     * 그래서 excludeFilters를 이용해 컴포넌트 스캔 대상에서 제외한다.
     *
     * 컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
     * @Configuration이 컴포넌트 스캔의 대상이 된 이유도 @Configuration 소스코드를 열어보면 @Component 애노테이션이 붙어있기 때문
     *
     * 컴포넌트 스캔은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
     * 이때 스프링 빈의 기본 이름은 클래스 명을 사용하되 맨 앞글자만 소문자를 사용한다.
     * 빈의 이름을 직접 지정하고 싶으면, @Component("빈이름지정") 이런식으로 이름을 부여하면 된다.
     *
     * 생성자에 @Autowired를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
     * 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입힌다.
     *
     * 탐색 위치와 기본 스캔 대상
     * 탐색할 패키지의 시작 위치 지정
     *   -> 모든 자바 클래스를 다 컴포턴트 스캔하면, 시간이 오래 걸린다.
     *      그래서 꼭 필요한 위치부터 탐색하도록 시작위치를 지정할 수 있다.
     *      @ComponentScan(basePackages="",)
     *      basePackages = {"hello.core", "hello.service"} 처럼 여러개 지정도 가능
     *      탐색할 패키지의 시작 위치를 지정하면, 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
     * 만약 지정하지 않으면, @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
     *
     * 권장
     *  -> 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것, 스프링 부트도 이 방법을 기본으로 제공한다.
     * 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례
     * (그리고 이 설정안에 바로 @ComponentScan 이 들어있다!)
     *
     * 컴포넌트 스캔 기본 대상
     * @Component : 컴포넌트 스캔에서 사용
     * @Controlller : 스프링 MVC 컨트롤러에서 사용
     * @Service : 스프링 비즈니스 로직에서 사용
     * @Repository : 스프링 데이터 접근 계층에서 사용
     * @Configuration : 스프링 설정 정보에서 사용
     *
     * useDefaultFilters 옵션은 기본으로 켜져있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다.
     * includeFilters : 컴포넌트 스캔 대상을 추가로 지정한다.
     * excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정한다.
     */

}
