class Duck {
  constructor(private name: string) {
    this.name = name;
  }

  quack(): void {
    console.log(`${this.name}가 꽥꽥 소리를 내고 있습니다.`);
  }

  display(): void {
    console.log(`${this.name} 오리 입니다.`);
  }

  // 새로 추가된 항목
  fly(): void {
    console.log(`${this.name}가 날고 있습니다.`);
  }
}

class MallardDuck extends Duck {
}

class RubberDuck extends Duck {
  display(): void {
    console.log(`이것은 날수 없는 고무로 된 ${this.name}입니다.`);
  }
}

const mallardDuck = new MallardDuck("몰라드");
mallardDuck.display();
mallardDuck.quack();
mallardDuck.fly();

const rubberDuck = new RubberDuck("러버");
rubberDuck.display();
rubberDuck.quack();

// 날지 못하는데 슈퍼클래스에 변경에 영향을 강제 주입 받아서 날게 됨...!?
rubberDuck.fly(); 