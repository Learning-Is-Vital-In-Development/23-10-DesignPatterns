# 팩토리 패턴

🤔 애플리케이션을 특정 구현으로 부터 분리할수 있을까?

## 구상 클래스 사용시 문제 점

### 먼저, 구상 클래스란(Concrete class)?
new 연산자로 인스턴스를 만들 수 있는 클래스 (<-> 추상 클래스)

구상 클래스 사용시 문제 점 
- (<-> 인터페이스 사용시 장점 = 변화에 대응하기 쉽다, 어떤 클래스든 인터페이스만 구현하면 사용할 수 있다 OCP)
- 구상 클래스를 많이 사용하면 새로운 구상 클래스가 추가 될때마다 코드를 고쳐야 한다.

### 구상 클래스 사용시 문제점 예시 코드

```typescript
// 제품 인터페이스
interface Product {
  init(configFile: string): void;
}

// 구상 클래스 제품 A 구현
class ConcreteProductA implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductA initiated!');
  }
}

// 구상 클래스 제품 B 구현
class ConcreteProductB implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductB initiated');
  }
}

// 클라이언트
class ProductStore {
  orderProduct(type: string): Product {
    const product: Product = null;
    
    switch (type) { // 🥲 제품 종류가 바뀔때마다 Store 코드를 계속 바꾸어 주어야 한다. => 객체 생성 부분을 위임
      case 'A':
        product = new ConcreteProductA();
        product.init('./product-a.conf.json')
        return product;
      case 'B':
        product = new ConcreteProductB();
        product.init('./product-b.conf.json')
        return product;
      default:
        throw new Error(`Invalid product type: ${type}`);
    }
  }
}

// 사용 예시
const store = new ProductStore();

const productA = store.orderProduct('A');
const productB = store.orderProduct('B');
```

<br/>
<br/>
<br/>

# 1. 심플 팩토리 패턴
```typescript
// 제품 인터페이스
interface Product {
  init(configFile: string): void;
}

// 구상 클래스 제품 A 구현
class ConcreteProductA implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductA initiated!');
  }
}

// 구상 클래스 제품 B 구현
class ConcreteProductB implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductB initiated');
  }
}

// 객체 생성 부분을 전담하는 Simple Factory 클래스 
class ProductFactory {
  createProduct(type: string): Product {
    let product: Product;
    
    switch (type) { // 제품 종류가 바뀔때마다 Store 코드를 계속 바꾸어 주어야 한다. => 객체 생성 부분을 위임
      case 'A':
        product = new ConcreteProductA();
        product.init('./product-a.conf.json')
        return product;
      case 'B':
        product = new ConcreteProductB();
        product.init('./product-b.conf.json')
        return product;
      default:
        throw new Error(`Invalid product type: ${type}`);
    }
  }
}

// 클라이언트 - 클라이언트는 제품 종류가 추가되거나 수정되어도 바뀔 일 이 없다.
class ProductStore {
  constructor (
    private productFactory: ProductFactory
  ) {}

  orderProduct(type: string): Product {
    return this.productFactory.createProduct(type);
  }
}

// 사용 예시
const store: ProductStore = new ProductStore();

const productA: ConcreteProductA = store.orderProduct('A');
const productB: ConcreteProductB = store.orderProduct('B');
```

🤔 Simple Factory 패턴은 자주쓰이는 관용구이지 정확하게 패턴은 아니다?
패턴도 자주쓰이는 관용구 같은거 아닌가?

<br/>
<br/>
<br/>

# 2. 팩토리 메소드 패턴

```typescript
// 제품 인터페이스
interface Product {
  init(configFile: string): void;
}

// 구상 클래스 제품 A1 구현
class ConcreteProductA1 implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductA1 initiated!');
  }
}

// 구상 클래스 제품 A2 구현
class ConcreteProductA2 implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductA2 initiated!');
  }
}

// 구상 클래스 제품 B1 구현
class ConcreteProductB1 implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductB1 initiated');
  }
}

// 구상 클래스 제품 B2 구현
class ConcreteProductB2 implements Product {
  init(configFile: string): void {
    console.log('ConcreteProductB2 initiated');
  }
}

// Factory 구상 클래스 -> 추상화 클래스로 변경
abstract class ProductFactory {
  abstract createProduct(type: string): Product; // 제품을 생성하는 추상 메서드 사용
}

// 여러 종류의 구상 Factory를 만드는 것이 가능해짐.
class ProductFactory1 extends ProductFactory {
  createProduct(type: string): Product {
    let product: Product;
    
    switch (type) {
      case 'A':
        product = new ConcreteProductA1();
        product.init('./product-a1.conf.json')
        return product;
      case 'B':
        product = new ConcreteProductB1();
        product.init('./product-b1.conf.json')
        return product;
      default:
        throw new Error(`Invalid product type: ${type}`);
    }
  }
}

class ProductFactory2 extends ProductFactory {
  createProduct(type: string): Product {
    let product: Product;
    
    switch (type) {
      case 'A':
        product = new ConcreteProductA2();
        product.init('./product-a2.conf.json')
        return product;
      case 'B':
        product = new ConcreteProductB2();
        product.init('./product-b2.conf.json')
        return product;
      default:
        throw new Error(`Invalid product type: ${type}`);
    }
  }
}

// 클라이언트
class ProductStore {
  constructor (
    private productFactory: ProductFactory
  ) {}

  orderProduct(type: string): Product {
    return this.productFactory.createProduct(type);
  }
}

// 사용 예시
const store1: ProductStore = new ProductStore(new ProductFactory1());
const store2: ProductStore = new ProductStore(new ProductFactory1());

const productA1: ConcreteProductA = store1.orderProduct('A');
const productA2: ConcreteProductA = store2.orderProduct('A');

const productB1: ConcreteProductB = store1.orderProduct('B');
const productB2: ConcreteProductB = store2.orderProduct('B');
```


# 의존성 역전
먼저 헷갈리는 용어들에대해 정리를 해보자

### Inversion of control (IoC, 제어 역전)
시스템간의 느슨한 결합을 위해 "컨트롤"의 주체를 "역전"시키자는 원칙.  
`컨트롤` : 주요 책임 외에 클래스가 같는 모든 추가 책임들을 말한다. (의존하고 있는 객체의 생성 등)  
`역전` : 처음에는 이 역전이라는 말이 안와 닿았다. "역전" 보다는 "위임"이라는 말이 더 적절한 것이 아닐까 생각 되었다. 그런데 내가 상대방에게 어떤 일의 처리를 위임한다는 것은 그 일의 처리자가 나에서 상대방으로 역전되었다고 볼수있다. 내가 직접 컨트롤 하는 입장에서 컨트롤을 다른 시스템에 넘김으로서 컨트롤의 주체가 나에서 -> 넘겨진 시스템으로 바뀌게(역전) 된다  

예시) 자동차 직접 몰기 VS 택시 이용하기
자동차를 직접 몰면 내가 자동차를 다 제어해야 하지만, 택시를 타면 내가 자동차를 제어하지 않아도 된다. 자동차 제어의 주체가 나에서 택시기사라는 새로운 객체로 역전된다.

### Dependency Injection (DI, 의존성 주입)
IoC 원칙에 기반한 종속 객체 생성에 대한 패턴 입니다.  
클래스 내부에서 필요한 다른 객체를 직접 생성해서 사용하는 것이아니라 클래스 외부에서 생성 하고, 외부에서 생성된 종속성 객체를 여러 방법으로 가져와 사용하는 패턴.  

## Dependency Inversion Principle (DIP, 의존 역전의 원칙)
SOLID 원칙의 D  
상위 수준의 모듈이 하위 수준의 모듈에 직접 의존해서는 안된다.  
`상위 수준`? `하위 수준`?: 변화하기 쉬운 부분이 하위 수준, 변화 하기 어려운 부분이 상위 수준이라고 생각 할 수 있다. 즉 상위 시스템이 변화 하기 쉬운 하위 부분에 직접 의존하는 관계를 지양하자는 원칙이다. 상위 수준은 기능이 추상화된 인터페이스에만 의존하고, 하위 수준들은 그 인터페이스를 통해 구현하는 방식을 적용 할 수 있다.  


# 3. 추상 팩토리 패턴
```typescript
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
```


## 정리
### 1. 팩토리 매소드 패턴과 추상 팩토리 패턴
- 팩토리 매소드 패턴의 경우 created~* 로 시작하는 추상 팩토리 함수를 사용해서 한 제품을 생산하는 데 필요한 인터페이스 제공
- 추상 팩토리 패턴의 경우 관련 있는 여러 제품 군을 생산하는 추상 인터페이스를 제공

### 2. 팩토리 패턴은 어느경우에 사용하는 것이 좋을까?
- 애플리케이션을 특정 구현으로 부터 분리하고 싶을 때
