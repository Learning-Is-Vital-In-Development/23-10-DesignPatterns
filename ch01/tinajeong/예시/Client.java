package ch01.tinajeong.예시;

public class Client {
    public static void main(String[] args) {
        BookRetrievingContext context = new BookRetrievingContext();
        context.performBookRetrieving(Vendor.KAKAO);
        context.performBookRetrieving(Vendor.ALADIN);
    }
}