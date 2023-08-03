---
marp: true
---

# 어댑터 패턴과 퍼사드 패턴

## 최 혁

---

# 어댑터 패턴

    특정 클래스 인터페이스를 다른 인터페이스로 변환하는 패턴

객체 어댑터: composition을 활용하여 어댑터 패턴 구현

클래스 어댑터: 다중 상속을 활용하여 어댑터 패턴 구현 (C++과 같이 다중 상속을 지원하는 언어만 가능)

**요약: 하나의 인터페이스를 다른 인터페이스로 변환**

---

# 어댑터 패턴 예제 (객체 어댑터)

```java
public class DuckTestDrive {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        Turkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        testDuck(duck);
        testDuck(turkeyAdapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
```

---

# 퍼사드 패턴

    라이브러리, 프레임워크, 다른 클래스의 복잡한 집합에 대한 단순화된 인터페이스를 제공하는 디자인 패턴

- 퍼사드는 움직이는 부분이 많이 포함된 복잡한 하위 시스템에 대한 간단한 인터페이스를 제공하는 클래스이다.
- 퍼사드 패턴은 Facade로도 불린다.
- 퍼사드는 인터페이스를 단순하게 만들고 클라이언트와 구성 요소로 이루어진 서브시스템을 분리하는 역할도 한다.

**퍼사드 패턴과 어댑터 패턴의 차이는 인터페이스를 단순하게 만드냐, 다른 인터페이스로 변환하냐의 차이이다.**

---

# 퍼사드 패턴 예제

```java
public class HomeTheaterTestDriver {
    public void run() {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(projector, screen, lights);

        homeTheater.watchMovie();
        homeTheater.endMovie();
    }
}
@AllArgumentConstructor
public class HomeTheaterFacade {
    Projector projector;
    Screen screen;
    TheaterLights lights;
    public void watchMovie() {
        screen.down();
        projector.on();
        lights.dim(10);
    }
    public void endMovie() {...}
}
```

---

# 최소 지식 원칙(Principle of Least Knowledge)

    객체 사이의 상호작용은 아주 가까운 친구 사이에만 허용하는 원칙

- 여러 클래스가 복잡하게 얽혀 있어 변경점이 많아지는 문제를 방지할 수 있다.
- 의존성을 줄일 수 있다.

---

# 퍼사드 패턴의 장단점

- 장점: 복잡한 하위 시스템에서 코드를 별도로 분리할 수 있다.
- 단점: 퍼사드는 앱의 모든 클래스에서 결합된 전지전능한 객체가 될 수 있다.

**요약: 인터페이스를 간단하게 변경**
