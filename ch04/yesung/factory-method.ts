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

// Factory 구상 클래스 -> 추상화 클래스
abstract class ProductFactory {
  abstract createProduct(type: string): Product; // 제품을 생성하는 "팩토리 메서드" 사용
}

class ProductFactory1 extends ProductFactory {
  createProduct(type: string): Product {
    let product: Product;
    
    switch (type) { // 제품 종류가 바뀔때마다 Store 코드를 계속 바꾸어 주어야 한다. => 객체 생성 부분을 위임
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
    
    switch (type) { // 제품 종류가 바뀔때마다 Store 코드를 계속 바꾸어 주어야 한다. => 객체 생성 부분을 위임
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
const store1: ProductStore = new ProductStore(new ProductFactory1());
const store2: ProductStore = new ProductStore(new ProductFactory1());

const productA1: ConcreteProductA = store1.orderProduct('A');
const productA2: ConcreteProductA = store2.orderProduct('A');

const productB1: ConcreteProductB = store1.orderProduct('B');
const productB2: ConcreteProductB = store2.orderProduct('B');


