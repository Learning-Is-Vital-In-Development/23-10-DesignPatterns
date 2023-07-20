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

// 사용 예시
const store = new ProductStore();

const productA = store.orderProduct('A');
const productB = store.orderProduct('B');