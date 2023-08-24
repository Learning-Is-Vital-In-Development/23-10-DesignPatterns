---
marp: true
---

# 상태 패턴

## 최 혁

---

# 상황

    상태(동전 있음, 동전 없음, 판매, 매진)를 갖는 뽑기 기계를 유연하게 디자인 해주세요.

```java
    private State state;
    public void insertQuater() {
        if (state == HAS_QUARTER) {
            System.out.println("동전은 한 개만 넣어주세요");
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("동전을 넣으셨습니다.");
        } else if (state == SOLD_OUT) {
            System.out.println("매진되었습니다.");
        }
    }
    public void ejectQuater() {
        if (state == HAS_QUARTER) {
            System.out.println("동전이 반환됩니다.");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("동전을 넣어주세요.");
        } else if (state == SOLD_OUT) {
            System.out.println("동전을 넣지 않으셨습니다. 동전이 반환되지 않습니다.");
        }
    }
```

---

# 위 코드의 문제점은?

1. 상태가 추가될 때마다 모든 메서드를 수정해야 한다.(OCP 위반)
2. 변화하는 부분에 대한 캡슐화가 되지 않았다.
3. 새로운 기능을 추가하는 과정에서 기존 코드에 없던 새로운 버그가 생길 가능성이 높다.

---

# 상태 패턴

    상태를 기반으로 하는 행동을 캡슐화하고 행동을 현재 상태에게 위임하는 디자인 패턴

- Context: 현재 상태에게 행동을 위임
- State: 인터페이스나 추상 클래스로 선언하여 상태들의 행동을 정의
- ConcreteStates: 상태별 메서드들에 대한 자체적인 구현을 제공

---

# 상태 패턴으로 코드 변경

```java
public class GumballMachine { // context
    private State state = soldOutState;
	private int count = 0;

	public GumballMachine(int numberGumballs) {
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		this.count = numberGumballs;
 		if (numberGumballs > 0) {
			state = noQuarterState;
		}
	}

	public void insertQuarter() { state.insertQuarter(); }

	public void ejectQuarter() { state.ejectQuarter(); }
}
```

---

```java
@AllArgumentConstructor
public class HasQuarterState implements State {
	private GumballMachine gumballMachine;

	public void insertQuarter() {
		System.out.println("You can't insert another quarter");
	}

	public void ejectQuarter() {
		System.out.println("Quarter returned");
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}
}
@AllArgumentConstructor
public class NoQuarterState implements State {
    private GumballMachine gumballMachine;

	public void insertQuarter() {
		System.out.println("You inserted a quarter");
		gumballMachine.setState(gumballMachine.getHasQuarterState());
	}

	public void ejectQuarter() {
		System.out.println("You haven't inserted a quarter");
	}
}
```

---

# 전략 패턴과 상태 패턴의 차이

- 공통점
  - 구성과 위임으로 객체가 다른 행동과 알고리즘을 보이도록 한다.
  - 똑같은 다이어그램 구조를 갖고 있다.
- 차이점
  - 쓰이는 용도가 다르다.
    - 전략 패턴: 클라이언트가 Context 객체에게 어떤 전략 객체를 사용할지를 지정
    - 상태 패턴: Context나 상태 객체가 Context의 어떤 상태를 가질지 결정(클라이언트가 아님)
  - 전략 패턴은 컨텍스트와 위임하는 객체 간에 독립적이나 상태 패턴은 독립적이지 않기에 상태 객체가 컨텍스트가 갖고 있는 상태를 마음대로 변경할 수 있다.

---

# 요약

- 상태 패턴은 상태를 기반으로 하는 행동을 캡슐화하고 행동을 현재 상태에게 위임한다.
- 상태 패턴을 사용하면 상태에 따른 조건문들을 없앨 수 있다.
- 전략 패턴과 구조적으로 유사하나 목적이 다르다.
