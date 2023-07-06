interface Flyable {
  fly(): void;
}

interface Quackable {
  quack(): void;
}

class Duck {
  constructor(private name: string) {}

  display(): void {
    console.log(`${this.name} 오리 입니다.`);
  }
}

class MallardDuck extends Duck implements Quackable, Flyable {
  fly(): void {
    console.log(`${this.name}가 날고 있습니다.`);
  }

  quack(): void {
    console.log(`${this.name}가 꽥꽥 소리를 내고 있습니다.`);
  }
}

class RubberDuck extends Duck implements Quackable {
  quack(): void {
    console.log(`${this.name}가 꽥꽥 소리를 내고 있습니다.`);
  }
}

const mallardDuck: MallardDuck = new MallardDuck("몰라드");
mallardDuck.display(); // 출력: 몰라드가 헤엄치고 있습니다.
mallardDuck.fly(); // 출력: 몰라드가 날고 있습니다.
mallardDuck.quack(); // 출력: 몰라드가 꽥꽥 소리를 내고 있습니다.

const rubberDuck: RubberDuck = new RubberDuck("러버");
rubberDuck.display(); // 출력: 러버가 헤엄치고 있습니다.
rubberDuck.quack(); // 출력: 러버가 꽥꽥 소리를 내고 있습니다.

rubberDuck.fly(); // error rubberDuck.fly is not a function 