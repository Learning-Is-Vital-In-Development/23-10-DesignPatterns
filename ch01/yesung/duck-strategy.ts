interface FlyStrategy {
  fly(): void;
}

interface QuackStrategy {
  quack(): void;
}

class FlyWithWing implements FlyStrategy {
  fly() {
    console.log('날개로 납니다.');
  }
}

class FlyNoWay implements FlyStrategy {
  fly() {
    console.log('날지 못합니다.');
  }
}

class QuackDefault implements QuackStrategy {
  quack() {
    console.log('기본적인 꿱');
  }
}

class QuackRubber implements QuackStrategy {
  quack() {
    console.log('고무오리가 내는 꿱소리')
  }
}

class Duck {
  constructor(
    private flyStrategy: FlyStrategy,
    private quackStrategy: QuackStrategy,
    private name: string
  ) {}

  display(): void {
    console.log(`${this.name} 오리 입니다.`);
  }

  fly(): void {
    this.flyStrategy.fly();
  }

  quack(): void {
    this.quackStrategy.quack();
  }
}

class MallardDuck extends Duck{
  display(): void {
    console.log(`안녕하세요 ${this.name}입니다.`);
  }
}

class RubberDuck extends Duck{
  display(): void {
    console.log(`노란색의 ${this.name}입니다.`);
  }
}

const mallardDuck: Duck = new MallardDuck(
  new FlyWithWing(),
  new QuackDefault(),
  "몰라드"
);

const rubberDuck: Duck = new RubberDuck(
  new FlyNoWay(),
  new QuackRubber(),
  "러버덕"
);

mallardDuck.display(); // 출력: 몰라드가 헤엄치고 있습니다.
mallardDuck.fly(); // 출력: 몰라드가 날고 있습니다.
mallardDuck.quack(); // 출력: 몰라드가 꽥꽥 소리를 내고 있습니다.

rubberDuck.display(); // 출력: 러버가 헤엄치고 있습니다.
rubberDuck.quack(); // 출력: 러버가 꽥꽥 소리를 내고 있습니다.

rubberDuck.fly(); // rubberDuck.fly is not a function 