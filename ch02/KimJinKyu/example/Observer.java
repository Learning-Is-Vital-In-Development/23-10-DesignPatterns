package ch02.KimJinKyu.example;

public interface Observer {
    // 원하는 항목만 가져올 수 있도록 pull 형태로 구현
    public void update();
}
