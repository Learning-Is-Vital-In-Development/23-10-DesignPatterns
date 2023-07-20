---
marp: true
---

# 싱글톤 패턴

## 최 혁

---

# 싱글톤 패턴

    클래스 인스턴스를 하나만 만들고, 그 인스턴스로의 전역 접근을 제공한다.

```java
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
```

---

# 멀티스레딩 환경에서 문제

**위와 같이 getInstance시 값이 null이면 인스턴스를 가져오도록 코드를 구현하면 멀티스레드 환경에서 두 개 이상의 인스턴스가 만들어질 수 있다!**

1. getInstance 메서드에 synchronized 키워드 사용
2. JVM에 클래스 로딩 시 바로 객체를 만들도록 구현(정적 초기화 부분에 구현)
3. DCL(Double-Checked Locking)을 사용하여 생성
4. enum으로 생성
