public class UserA extends Observer {
    public UserA(String name) {
        this.setName(name);
    }

    @Override
    public void update() {
        System.out.println(this.getName() + "을 갱신합니다.");
    }
}