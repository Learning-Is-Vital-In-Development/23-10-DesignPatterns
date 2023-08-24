# 상태 패턴

상태에 따라 객체 내부의 행동이 바뀌어야 하는 경우 사용, 상태를 별도의 클래스로 캡슐화한 다음 현재 상태를 나타내는 그 객체에게 행동을 위임하는 패턴

이점 : 객체의 내부 상태가 변경될 때 객체의 행동도 함께 변경될 수 있게 된다.


### 음악 플레이어의 예시

상태 인터페이스 정의
```typescript
interface State {
    play(): void;
    pause(): void;
    stop(): void;
}
```

상태 구상 클래스 정의
-> 각 상태들은 자신의 다음 상태를 알고 있으며, 음악 플레이어의 행위를 위임 한다.
```typescript
class PlayingState implements State {
    player: MusicPlayer;

    constructor(player: MusicPlayer) {
        this.player = player;
    }

    play(): void {
        console.log("Already playing music.");
    }

    pause(): void {
        console.log("Music paused.");
        this.player.setState(this.player.pausedState);
    }

    stop(): void {
        console.log("Music stopped.");
        this.player.setState(this.player.stoppedState);
    }
}

class PausedState implements State {
    player: MusicPlayer;

    constructor(player: MusicPlayer) {
        this.player = player;
    }

    play(): void {
        console.log("Music resumed.");
        this.player.setState(this.player.playingState);
    }

    pause(): void {
        console.log("Already paused.");
    }

    stop(): void {
        console.log("Music stopped from paused state.");
        this.player.setState(this.player.stoppedState);
    }
}

class StoppedState implements State {
    player: MusicPlayer;

    constructor(player: MusicPlayer) {
        this.player = player;
    }

    play(): void {
        console.log("Music started.");
        this.player.setState(this.player.playingState);
    }

    pause(): void {
        console.log("Can't pause, music is stopped.");
    }

    stop(): void {
        console.log("Already stopped.");
    }
}
```

MusicPlayer 객체는 모든 상태를 알고 있으며, 
필요한 행위들을 상태에 위임 한다.

*MusicPlayer는 상태 별로 복잡한 동작 과정에 대해 신경 쓸 필요가 없다. 그 일은 각 상태 내부에서 신경쓴다!

그 결과 객체의 내부 상태가 변경될 때 객체의 행동도 함께 변경될 수 있게 됨.

```typescript
class MusicPlayer {
    playingState: State;
    pausedState: State;
    stoppedState: State;

    currentState: State;

    constructor() {
        this.playingState = new PlayingState(this);
        this.pausedState = new PausedState(this);
        this.stoppedState = new StoppedState(this);

        this.currentState = this.stoppedState;  // 초기 상태
    }

    setState(state: State): void {
        this.currentState = state;
    }

    play(): void {
        this.currentState.play();
    }

    pause(): void {
        this.currentState.pause();
    }

    stop(): void {
        this.currentState.stop();
    }
}
```


사용 부분
```typescript
const player = new MusicPlayer();
player.play();
player.pause();
player.play();
player.stop();
player.play();
```


# Q&A 정리

### 1. 반드시 구상 클래스에서 다음 상태를 결정해야 할까요?
=> 꼭 그래야 하는 것은 아니다. 상태 클래스 내에서 다음 상태를 결정 하면, 각 상태클래스간 의존성이 생기게 되기 때문에 상태 전환이 동적으로 이루어 지는 것이 아니라 상태 전환이 고정되어 있다면 Context 객체 (= 예시에서 MusicPlayer) 에 전환 로직을 추가 해도 된다.

만약 상태를 Context 객체 (= 예시에서 MusicPlayer) 에서 결정하게 된다면?

구상 클래스는 더이상 Context 객체를 참조하지 않아도 된다.
```typescript
class PlayingState implements State {
    play(): State | null {
        console.log("Already playing music.");
        return null;
    }

    pause(): State | null {
        console.log("Music paused.");
        return new PausedState();
    }

    stop(): State | null {
        console.log("Music stopped.");
        return new StoppedState();
    }
}

```

하지만,  Context 객체 내에서 동적으로 상태를 결정하기 위해서 결국 if문과 같은 부가적인 코드가 추가 된다.

```typescript
class MusicPlayer {
    currentState: State;

    constructor() {
        this.currentState = new StoppedState();  // 초기 상태
    }

    setState(state: State): void {
        this.currentState = state;
    }

    play(): void {
        const newState = this.currentState.play();
        if (newState) this.setState(newState);
    }

    pause(): void {
        const newState = this.currentState.pause();
        if (newState) this.setState(newState);
    }

    stop(): void {
        const newState = this.currentState.stop();
        if (newState) this.setState(newState);
    }
}
```

### 2. 클라이언트에서 상태 객체와 직접 연락 하는 경우도 있나요?
=> 그런 일은 없습니다.
=> 상태를 관리하는 일은 전적으로 Context 객체 (= 예시에서 MusicPlayer)에서 담당합니다.

### 3. 여러 Context끼리 상태 객체를 공유할 수도 있나요?
=> 각 상태를 정적 인스턴스 변수에 할당해, 공유 할 수 있습니다.

### 4. 상태 패턴을 쓰면 결국 작성해야 하는 클래스가 많아질것 같은데 이러면 오히려 더 복잡해지는거 아닌가요?
=> 유연성을 확장하는데 드는 비용
=> 한번쓰고 버릴 코드가 아니라면 조금 귀찮아도 여러 코드를 작성하는게 유지보수에 좋다.

### 5. State 를 인터페이스로 작성했는데, 추상클래스를 사용해도 될까요?
=> 상태별로 공통으로 수행해야 하는 행위가 있다면 추상클래스를 사용해도 된다. 나중에 각 상태별 구상클래스를 건드리지 않고, 추상클래스에 바로 매서드를 정의하면 되니까!

