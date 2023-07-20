// 제품 A 인터페이스
interface ProductA {
  operationA(): void;
}

// 제품 B 인터페이스
interface ProductB {
  operationB(): void;
}

// 제품 A1 구현
class ConcreteProductA1 implements ProductA {
  operationA(): void {
    console.log('ConcreteProductA1 operation');
  }
}

// 제품 A2 구현
class ConcreteProductA2 implements ProductA {
  operationA(): void {
    console.log('ConcreteProductA2 operation');
  }
}

// 제품 B1 구현
class ConcreteProductB1 implements ProductB {
  operationB(): void {
    console.log('ConcreteProductB1 operation');
  }
}

// 제품 B2 구현
class ConcreteProductB2 implements ProductB {
  operationB(): void {
    console.log('ConcreteProductB2 operation');
  }
}

// 추상 팩토리 인터페이스 - 관련 있는 제품 군을 만들때 사용
interface AbstractFactory {
  createProductA(): ProductA;
  createProductB(): ProductB;
}

// 구체적인 팩토리 A
class ConcreteFactory1 implements AbstractFactory {
  createProductA(): ProductA {
    return new ConcreteProductA1();
  }

  createProductB(): ProductB {
    return new ConcreteProductB1();
  }
}

// 구체적인 팩토리 B
class ConcreteFactory2 implements AbstractFactory {
  createProductA(): ProductA {
    return new ConcreteProductA2();
  }

  createProductB(): ProductB {
    return new ConcreteProductB2();
  }
}

// 사용 예시
class ProductStore {
  constructor (
    private productFactory: ProductFactory
  ) {}

  orderProduct(type: string): Product {
    return this.productFactory.createProduct(type);
  }
}

const store1 = new ProductStore(new ConcreteFactory1());
const store2 = new ProductStore(new ConcreteFactory2());