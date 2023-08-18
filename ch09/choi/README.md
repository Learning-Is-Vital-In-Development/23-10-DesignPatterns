---
marp: true
---

# 반복자 패턴과 컴포지트 패턴

## 최 혁

---

# 상황

    메뉴를 저장하는 프로그램에서 아이템을 List에 저장하는 PancakeHouseMenu와 Array에
    저장하는 DinnerMenu를 동일한 인터페이스로 조회해야 한다.

    => 인터페이스가 아닌 구상 클래스에 맞추었기에 여러 구현이 중복됐다.

    => 반복 작업을 Iterator로 추상화하자!

<br>

```java
for (int i = 0; i < breakfastItems.size(); i++) {
    MenuItem menuItem = breakfastItems.get(i);
}
for (int i = 0; i < lunchItems.length; i++) {
    MenuItem menuItem = lunchItems.[i];
}
```

---

# 반복자 패턴

    컬렉션의 요소들의 기본 표현​(리스트, 스택, 트리 등)​을 노출하지 않고 그들을 하나씩 순회할 수
    있도록 하는 행동 디자인 패턴

**자바에서 Iterable, Iterator 인터페이스로 구현할 수 있다.**

- 장점
  - 집합체를 대상으로 하는 반복 작업을 별도의 객체(Iterator?)로 캡슐화할 수 있다.
  - 컬렉션에 있는 모든 데이터를 대상으로 반복 작업을 하는 역할을 컬렉션에서 분리할 수 있다.
  - 반복 작업에 똑같은 인터페이스를 적용할 수 있으므로 집합체에 있는 객체를 활용하는 코드를 만들 때 다형성을 활용할 수 있다.

---

# 반복자 패턴

```java
public class Waitress {
    Menu pancakeHouseMenu;
	Menu dinerMenu;
    public void printMenu() {
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();

		printMenu(pancakeIterator);
		printMenu(dinerIterator);
	}
	private void printMenu(Iterator<MenuItem> iterator) {
		while (iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}
```

---

# 단일 책임 원칙(단일 역할 원칙)

    단일 책임 원칙: 클래스는 단 한 개의 책임을 가져야 한다.
    단일 역할 원칙: 어떤 클래스가 바뀌는 이유는 하나뿐이어야 한다.

**만약 Collection이 Iterable을 구현하지 않고 내부 컬렉션 관련 기능(ArrayList 구현체 메서드)과 반복자용 메서드 관련 기능(hasNext, get 등)을 전부 구현한다면?**

1. Collection 기능이 변경되면 클래스가 바뀌어야 한다.
2. 반복자 관련 기능이 바뀌었을 때도 클래스가 바뀌어야 한다.

---

# 상황2

    전체 메뉴 안에 세부 메뉴들이 List에 들어가있다. 다만, 세부 메뉴 안에 또 다른 서브 메뉴를 넣을 수
    없다. 이 경우 어떻게 해야 할까?

- 메뉴, 세부 메뉴, 서브 메뉴를 모두 넣을 수 있는 트리 형태의 구조가 필요하다.
- 각 메뉴에 있는 모든 항목을 대상으로 특정 작업을 할 수 있는 방법을 편하게 제공해야 한다.
- 더 유연한 방법으로 아이템을 대상으로 반복 작업을 수행할 수 있어야 한다.

---

# 컴포지트 패턴

    객체들을 트리 구조들로 구성한 후 이러한 구조들과 개별 객체들처럼 작업할 수 있도록 하는 구조 패턴

- 부분-전체 계층 구조를 생성할 수 있다.
- 클라이언트가 개별 객체와 복합 객체를 똑같은 방법으로 다룰 수 있다.
- 모든 객체의 인터페이스가 동일해야 하기에 객체에 따라 아무 의미가 없는 메서드가 생길 수 있다
  - null이나 false, 예외를 던져 해결할 수 있다.
- 복합 구조가 너무 복잡하다면 캐시를 도입하여 속도를 향상시킬 수 있다.

---

# 컴포지트 패턴 구성 요소

```java
public abstract class MenuComponent {

	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
  	public String getName() {
		throw new UnsupportedOperationException();
	}
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
        public void print() {
		throw new UnsupportedOperationException();
	}
}

```

---

# 컴포지트 패턴 구성 요소

```java
public class Menu extends MenuComponent {
	ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
	String name;
	String description;

	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}

	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}

	public MenuComponent getChild(int i) {
		return (MenuComponent)menuComponents.get(i);
	}
}
```

---

# 컴포지트 패턴 구성 요소

```java
public class MenuItem extends MenuComponent {
	String name;
	String description;
	boolean vegetarian;
	double price;

	public MenuItem(String name,
	                String description,
	                boolean vegetarian,
	                double price)
	{
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
}
```

---

# 컴포지트 패턴 구성 요소

```java
    MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
    MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
    MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
    allMenus.add(pancakeHouseMenu);
    allMenus.add(dinerMenu);
    pancakeHouseMenu.add(new MenuItem(...));
    dinerMenu.add(new MenuItem(...));

    allMenus.print();
```

---

# 정리

**반복자 패턴: 컬렉션의 구현을 드러내지 않으면서도 컬렉션에 있는 모든 객체를 대상으로 반복 작업을 할 수 있다.**

**컴포지트 패턴: 클라이언트에서 객체 컬렉션과 개별 객체를 똑같은 식으로 처리할 수 있다.**
