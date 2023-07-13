---
marp: true
---

# 옵저버 패턴

## 최 혁

---

# 기상 모니터링 애플리케이션

### 상황

    기상 Station으로부터 습도, 온도, 기압 데이터를 받아 다양한 디스플레이에 송출하고 싶다!

### 구현 목표

    - WeatherData 클래스에 온도, 습도, 기압 측정값과 게터를 가지고 있다.
    - 새로운 기상 측정 데이터가 들어올 때마다 다양한 디스플리이를 갱신하고 싶다.
    - 디스플레이가 추가될 수 있다.

---

# 아래 코드의 문제점

```java
public class WeatherData {
    public void meaturementChanged() {
        float temp = getTemperture();
        float humidity = getHumidity();
        currentConditionDisplay.update(temp, humidity);
        statisticDisplay.update(temp, humidity);
    }
}
```

1. **인터페이스가 아닌 구체적인 구현을 바탕으로 코딩하고 있다.**
2. **새로운 디스플레이 항목이 추가될 때마다 코드를 변경해야 한다.**
3. **실행 중에 디스플리에 항목을 추가하거나 제거할 수 없다.**
4. 디스플레이 항목들이 공통적인 인터페이스를 구현하지 않는다.
5. **바뀌는 부분을 캡슐화하지 않는다.**
6. WeatherData 클래스를 캡술화하지 않는다.

---

# 옵저버 패턴을 도입한다면?

```java
public class WeatherData {
    private List<Observer> observers;
    public void meaturementChanged() {
        float temp = getTemperture();
        float humidity = getHumidity();
        observers.forEach(observer -> observer.update(temperature, humidity));
    }
}
```

<br>

**옵저버 패턴: 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체에게 연락이 가고 자동으로 내용이 갱신되는 방식으로 일대다 의존성을 정의한다.**

---

# 옵저버 패턴

    Subject: 정보를 전달하는 객체
    Observer: 정보를 받는 객체
    Subject가 변경되면 다른 Observer들에게 연락이 간다.(일대다 의존성)

### 느슨한 결합

**객체들이 상호작용하지만, 서로 잘 모르는 관계**

- Subject는 Observer가 특정 인터페이스를 구현했다는 사실만 안다.
- 옵저버는 실시간으로 추가될 수 있다.
- 새로운 옵저버의 추가나 변경이 subject에 영향을 끼치지 않는다.

---

# 풀 방식으로 코드 바꾸기

```java
public class CurrentConditionDisplay implements Observer, DisplayElement {
    private Subject weatherData;

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }
}
```

- 풀 방식은 옵저버가 subject의 상태를 선택하여 가져올 수 있다.

(양방향 참조? 인터페이스에 상태 의존?)

---

# 디자인 원칙

- **애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분과 분리한다.**

옵저버 패턴에서 변하는 것은 주제의 상태와 옵저버의 개수, 형식이다. 옵저버 패턴에서는 주제를 바꾸지 않고도 주제의 상태에 의존하는 객체들을 바꿀 수 있다.

- **구현보다는 인터페이스에 맞춰서 프로그래밍한다**

주제와 옵저버에서 모두 인터페이스를 사용하여 느슨한 결합을 만들 수 있다.
(subject도 인터페이스를 도입할 필요가 있을까?)

- **상속보다는 구성을 활용한다**

옵저버 패턴은 composition을 활용한다.
