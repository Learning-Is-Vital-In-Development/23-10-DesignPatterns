 # 싱글톤 패턴

 ## 싱글톤 패턴의 특징

 - 전역변수처럼 `어디에서든지 접근`가능하게 만들수 있고, 객체의 `인스턴스를 하나로 유지` 할 수 있다.  
 - 핵심은 `인스턴스를 하나로 유지` 하도록 강제 할 수 있다는 것!

### 인스턴스를 하나로 유지해야 하는 상황
- 게임 클라이언트 => 사운드 매니저
- 백엔드 => 커넥션 풀
.
.
.


### 🤔 그냥 전역 변수를 사용하면 안될까?
전역변수도 어디에서든지 접근 가능하고, 인스턴스도 하나로 유지되지 않나?

전역변수의 단점 
- 전역변수는 라이프사이클이 길다 (lazy 방식으로 생성 못함): 
전역변수는 앱 실행부터 종료까지로 라이프사이클이 길기 때문에 리소스를 많이 차지하는 전역변수의 경우 사용하지 않을때도 리소스를 차지하고 있는다.
- 자바스크립트에서는 전역변수의 경우 스코프 체인 최 상단에 위치 하기 때문에 전역변수에 접근하는 성능이 안좋을 수 있다.(-> 현대 컴퓨팅 성능에서는 문제될 수준은 아닐수 있다.)
- 네임스페이스 오염 : 너무 많은 전역객체를 만들경우 네임스페이스가 지저분 해 질 수 있다.

=> 싱글톤 패턴을 활용하면, 필요할 때 lazy 하게 객체를 생성해서 사용 할 수 있다.

## 고전적인 싱글톤 패턴
```typescript
class Singleton {
  private static instance: Singleton;

  // private 생성자
  private constructor() {
    // 초기화 코드
  }

  // 인스턴스 반환 메소드
  public static getInstance(): Singleton {
    if (!Singleton.instance) {
      Singleton.instance = new Singleton();
    }
    return Singleton.instance;
  }

  // 싱글톤의 기능 메소드
  public doSomething(): void {
    console.log('Singleton is doing something');
  }
}

// 사용 예시
const singleton1 = Singleton.getInstance();
singleton1.doSomething(); // 출력: Singleton is doing something

const singleton2 = Singleton.getInstance();
singleton2.doSomething(); // 출력: Singleton is doing something

console.log(singleton1 === singleton2); // 출력: true
```

## 싱글톤 패턴은 Node.js 에 존재 하지 않는다?

Node.js의 모듈 시스템은 싱글톤을 만드는것과 유사하게 작동한다.

대표적인 모듈 import, export 예시
```typescript
// logger.ts

class Logger {
  constructor(name: string) {
    this.count = 0;
    this.name = name;
  }

  log(message: string): void {
    this.count++;
    console.log(`[${this.name}] ${message}`);
  }
}

export const logger = new Logger('DEFAULT');
```

```typescript
// main.ts

import { logger } from './logger';
logger.log('This is an message');
```
이때, export 되는 모듈은 내부적으로 캐시되기 때문에 logger 모듈을 필요로 하는 모든 다른 모듈들은 실제로 동일한 인스턴스를 받아 상태를 공유하게 된다.

하지만, 위의 전통적인 싱글톤 패턴에서 처럼 전체 어플리케이션 내에서 인스턴스의 고유성을 완전히 강제하지는 못 한다.

ex) 같은 패키지의 서로 다른 버전을 참조하는 경우 클래스 는 같지만, 다른 node_modules 에서 가져오면 의도한대로 싱글톤 생성이 안될수도 있음
```
main.ts => logger.ts(1.1)
        => web-logger.ts => logger.ts(2.0)
```
main 모듈에서 사용하는 logger 와 web-logger 모듈 에서 사용하는 logger 인스턴스는 버전이 다르기 때문에 캐싱이 적용되지 않아, 동일 인스턴스가 아니게 된다.

=> 이럴 경우, logger.ts를 패키지의 최 상위에서 관리하도록 패키지 매니저를 통해 종속성 트리를 수정해야 한다. 즉 모듈간 종속성 관리만 잘 하면 별도로 싱글톤을 만들어 사용할 일이 없다.

# 전통적인 싱글톤 방식은 과연 어떤경우에서든지 안전할까?


전통적인 싱글톤 방식
```Java
class Singleton {
  private static Singleton singleton;

  private Singleton() {}

  public static Singleton getInstance() {
    if (singleton == null) {
      singleton = new Singleton();
    }
    return singleton;
  }
}
```

멀티 쓰레드 환경에서는 쓰레드 세이프 하지 않다.

쓰레드 A  if (singleton == null)  ----> singleton = new Singleton();  
쓰레드 B  --------if (singleton == null)-------> singleton = new Singleton(); 

쓰레드 세이프한 싱글톤 방식 : synchronized 키워드 사용(모니터링 방식으로 동시성 문제 해결)
```Java
class Singleton {
  private static Singleton singleton;

  private Singleton() {}

  public static synchronized Singleton getInstance() {
    if (singleton == null) {
      singleton = new Singleton();
    }
    return singleton;
  }
}
```

getInstance 를 동기화 처리 하면 성능이 100배 정도 저하된다? 
=> 그래서 getInstance에 동기화 처리를 하면 저 부분이 병목으로 작용 할 수 도 있다.

해결 1. lazy 방식 포기
```
class Singleton {
  private static Singleton singleton = new Singleton();

  private Singleton() {}

  public static Singleton getInstance() {
    return singleton;
  }
}
```

멀티 쓰레드 환경이라도 JVM 에서는 클래스로딩은 한번만 일어나는가?

해결 2. DCL(double checked locking) 방식적용하여 동기화 되는 부분 최소화
```
class Singleton {
  private volatile static Singleton singleton;

  private Singleton() {}

  public static Singleton getInstance() {
    if (singleton == null) {
      synchronized (Singleton.class) {
        if (singleton == null) {
          singleton = new Singleton();
        }
      }
    }

    return singleton;
  }
}
```
