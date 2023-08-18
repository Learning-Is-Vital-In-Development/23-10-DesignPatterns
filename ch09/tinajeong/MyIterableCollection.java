package ch09.tinajeong;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyIterableCollection implements Iterable<String> {
    private List<String> jjangguList = Arrays.asList("하나", "둘", "셋", "야!");

    @Override
    public Iterator<String> iterator() {
        System.out.println("🌟iterator가 호출됨⭐️");
        return jjangguList.iterator();
    }

    public static void main(String[] args) {
        MyIterableCollection collection = new MyIterableCollection();

        for (String string : collection) {
            System.out.println(string);
        }
    }
}