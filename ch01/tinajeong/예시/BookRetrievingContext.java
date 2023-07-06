package ch01.tinajeong.예시;

import java.util.HashMap;

public class BookRetrievingContext {

    private static HashMap<String,BookApiOperations> bookOperationMap;

    BookRetrievingContext() {
        bookOperationMap = new HashMap<>();
        bookOperationMap.put(Vendor.KAKAO.getDescription(), new KakaoBookApiOperations());
        bookOperationMap.put(Vendor.ALADIN.getDescription(), new AladinBookApiOperations());
    }
    
    public void performBookRetrieving(Vendor vendor) {
        BookApiOperations bookApiOperations = bookOperationMap.get(vendor.getDescription());
        bookApiOperations.getBookInformation();
    }
        
}
