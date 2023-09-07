# 프록시 패턴과 데코레이터 패턴

프록시 패턴과 데코레이터 패턴은 구조적으로 매우 유사
두 패턴 모두 원래 객체에 대한 참조를 유지하면서 해당 객체의 인터페이스를 구현하는 형태

두 패턴의 차이점은 그 의도에 있다.

**프록시 패턴**: 원래 객체에 대한 접근을 제어하거나 지연시키기 위한 것

객체의 생성을 지연시키거나,
원격 서버의 객체에 접근하거나, 
객체에 대한 접근을 제한하는 등의 작업

데코레이터 패턴 : 객체의 기능을 동적으로 확장하거나 추가하기 위한 것

데코레이터 패턴에서는 여러 개의 데코레이터 객체를 중첩하여 사용가능


프록시 패턴 사용예시

캐시 서비스: 

```ts
interface DataService {
    fetchData(id: string): Promise<string>;
}

class RealDataService implements DataService {
    async fetchData(id: string): Promise<string> {
        // 예제를 위한 가상의 데이터베이스 호출
        await new Promise(resolve => setTimeout(resolve, 1000));
        return `Data for ID: ${id}`;
    }
}

class CachedDataServiceProxy implements DataService {
    private realDataService: RealDataService;
    private cache: Map<string, string>;

    constructor(realDataService: RealDataService) {
        this.realDataService = realDataService;
        this.cache = new Map();
    }

    async fetchData(id: string): Promise<string> {
        if (this.cache.has(id)) {
            console.log('Fetching data from cache...');
            return this.cache.get(id)!;
        }

        console.log('Fetching data from the database...');
        const data = await this.realDataService.fetchData(id);
        this.cache.set(id, data);
        return data;
    }
}

// 사용 예제
(async () => {
    const realDataService = new RealDataService();
    const cachedDataService = new CachedDataServiceProxy(realDataService);

    console.log(await cachedDataService.fetchData("123"));  // 데이터베이스에서 데이터 가져오기
    console.log(await cachedDataService.fetchData("123"));  // 캐시에서 데이터 가져오기
})();

```


데코레이터 패턴으로 적용한 캐시 서비스
-> 캐시 이외에 로깅 과 같은 기능들이 중첩해서 추가 되어야 하는 상황

```ts
interface DataService {
    fetchData(id: string): Promise<string>;
}

class RealDataService implements DataService {
    async fetchData(id: string): Promise<string> {
        await new Promise(resolve => setTimeout(resolve, 1000));
        return `Data for ID: ${id}`;
    }
}

class LoggingDecorator implements DataService {
    private dataService: DataService;

    constructor(dataService: DataService) {
        this.dataService = dataService;
    }

    async fetchData(id: string): Promise<string> {
        console.log(`Request made for ID: ${id}`);
        const result = await this.dataService.fetchData(id);
        console.log(`Data fetched for ID: ${id} - Result: ${result}`);
        return result;
    }
}

class CachingDecorator implements DataService {
    private dataService: DataService;
    private cache: Map<string, string>;

    constructor(dataService: DataService) {
        this.dataService = dataService;
        this.cache = new Map();
    }

    async fetchData(id: string): Promise<string> {
        if (this.cache.has(id)) {
            return this.cache.get(id)!;
        }

        const result = await this.dataService.fetchData(id);
        this.cache.set(id, result);
        return result;
    }
}

// 사용 예제
(async () => {
    const realDataService = new RealDataService();

    // 로깅과 캐싱 데코레이터를 중첩 사용
    const decoratedService = new LoggingDecorator(new CachingDecorator(realDataService));

    console.log(await decoratedService.fetchData("123"));  // 로그 출력 후 데이터베이스에서 데이터 가져오기
    console.log(await decoratedService.fetchData("123"));  // 로그 출력 후 캐시에서 데이터 가져오기
})();

```


대표적인 프록시 패턴 적용 예시

**객체의 생성 지연 (Lazy Initialization)**
```ts
interface ExpensiveObject {
    operation(): void;
}

class RealExpensiveObject implements ExpensiveObject {
    constructor() {
        console.log("RealExpensiveObject created!");
    }

    operation() {
        console.log("Expensive operation performed.");
    }
}

class LazyProxy implements ExpensiveObject {
    private realObject?: RealExpensiveObject;

    operation() {
        if (!this.realObject) {
            this.realObject = new RealExpensiveObject();
        }
        this.realObject.operation();
    }
}

const proxy = new LazyProxy();
proxy.operation();  // 처음 호출될 때 객체 생성

```

**원격 서버의 객체 접근 (Remote Proxy)**
``` ts
interface RemoteService {
    getData(): Promise<string>;
}

class RealRemoteService implements RemoteService {
    async getData(): Promise<string> {
        return "Data from remote server";
    }
}

class RemoteProxy implements RemoteService {
    async getData(): Promise<string> {
        // 예: 원격 서버에 API 요청을 보내는 코드
        const service = new RealRemoteService();
        return await service.getData();
    }
}

const proxy = new RemoteProxy();
proxy.getData().then(data => console.log(data));
```


**객체 접근 제한 (Protection Proxy)**
```ts
interface SecureResource {
    accessResource(): void;
}

class RealResource implements SecureResource {
    accessResource() {
        console.log("Resource accessed!");
    }
}

class ProtectionProxy implements SecureResource {
    private realResource: RealResource;
    private password: string;

    constructor(password: string) {
        this.password = password;
        this.realResource = new RealResource();
    }

    accessResource() {
        if (this.password === "correct-password") {
            this.realResource.accessResource();
        } else {
            console.log("Access denied!");
        }
    }
}

const proxy = new ProtectionProxy("wrong-password");
proxy.accessResource();  // Access denied!

const validProxy = new ProtectionProxy("correct-password");
validProxy.accessResource();  // Resource accessed!
```


정리:

프록시 패턴 : 
기존 객체를 대신해서 기존 객체의 동작을 제어하고 싶을 때

데코레이터 패턴 :
기존 객체에 새로운 기능을 동적으로 여러개 추가 하면서 사용하고 싶을 때

