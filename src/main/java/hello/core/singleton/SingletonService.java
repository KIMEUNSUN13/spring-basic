package hello.core.singleton;

public class SingletonService {

    /*
     * 클래스 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
     * private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야한다.
     *
     * 싱글톤 패턴을 구현하는 방법은 여러가지가 있다.
     * 객체를 미리 생성해두는 가장 단순한 방법만 사용한 예제이다.
     *
     * 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라,
     * 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
     *
     * But, 싱글톤 패턴의 문제점
     * 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
     * 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
     * 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
     * 테스트하기 어렵다.
     * 내부 속성을 변경하거나 초기화 하기 어렵다.
     * private 생성자로 자식 클래스를 만들기 어렵다.
     * 결론적으로 유연성이 떨어진다.
     * 안티패턴으로 불리기도 한다
     *
     */

    // 1. static 영역에 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {}
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
