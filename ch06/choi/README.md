---
marp: true
---

# 커맨드 패턴

## 최 혁

---

# 문제 상황

    집안의 여러 전자기기들의 ON, OFF 스위치를 등록할 수 있는 7개의 슬록이 있다. 각 슬롯에 원하는
    제품을 연결한 다음 버튼을 조작할 수 있어야 한다.

**각 전자제품은 단순히 켜고 끄는 작업을 제외한 다른 여러 동작(볼륨 크기, 속도 등)을 수행하고, 각 전자제품은 저마다 다른 api를 가지고 있기에 등록과 변경시 슬롯 등록 코드를 손봐야 한다.**

-> 이 떄 커맨드 패턴을 사용하면 도음이 된다!

---

# 커맨드 패턴

    요청을 요청에 대한 모든 정보가 포함된 독립실행형 객체로 변환하는 디자인 패턴

- invoker: 요청을 수행하는 객체로 필드에 커맨드 객체의 참조를 갖고 있다.
- receiver: 비즈니스 로직이 포함된 객체로 실제 작업을 수행한다.
- command interface: 커맨드를 실행하기 위한 단일 메서드만을 선언한다.
- command object: 다양한 유형의 요청을 구현하며, 실제 작업은 receiver에게 위임한다.

---

# 커맨드 패턴을 적용한 리모컨

```java
    SimpleRemoteControl remote = new SimpleRemoteControl(); // invoker
    Light light = new Light();  // receiver
    Computer computer = new Computer(); // receiver
    LightOnCommand lightOn = new LightOnCommand(light); // command object
    ComputerOnCommand computerOn = new ComputerOnCommand(computer);
    LightOffCommand lightOff = new LightOffCommand(light);

    remote.setCommand(lightOn);
    remote.setCommand(computerOn);
    remote.setCommand(lightOff);
    remote.on(); // computerOn, lightOn 실행
    remote.off();   // lightOff 실행
```

---

# 람다를 활용한 커맨드 패턴

```java
    SimpleRemoteControl remote = new SimpleRemoteControl(); // invoker
    Light light = new Light();  // receiver
    // LightOnCommand lightOn = new LightOnCommand(light); // command object

    remote.setCommand(() -> light.on());
    remote.setCommand(light::off);
    remote.buttonWasPressed();
```

함수 객체를 활용하여 command object를 만들지 않고 커맨드 패턴을 적용할 수 있다.

---

# 매크로 커맨드

```java
public class MacroCommand implements Command {
    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    public void execute() {
        for (int i = 0; i < commands.length; i++>) {
            commands[i].execute();
        }
    }
}
```

**버튼 한 개만 누르면 여러 커맨드 명령들을 동시에 수행하는 커맨드 명령을 만들 수도 있다!**

---

# 커맨드 패턴 활용하기

    커맨드 패턴을 활용하면 요청 정보를 큐나 스택에 넣어 이전 행동의 정보를 저장할 수 있다.
    따라서 커맨드 패턴은 스케줄러나 쓰레드 풀, 작업 큐와 같은 다양한 작업에 적용될 수 있다.

- **예시**
  스프레드시트 애플리세이션에서 매번 데이터가 변경될 때마다 디스크에 저장하지 않고, 특정 체크 포인트 이후의 모든 행동을 로그에 기록하는 방식으로 복구 시스템을 구축할 수 있다.

---

# 정리

**요청 내역을 객체로 캡슐화해서 객체를 서로 다른 요청 내역에 따라 매개변수화할 수 있다. 이러면 요청을 큐에 저장하거나 로그로 기록하거나 작업 취소 기능을 사용할 수 있다.**

**커맨드 패턴을 사용하여 요청하는 객체와 요청을 수행하는 객체를 분리할 수 있다.**
