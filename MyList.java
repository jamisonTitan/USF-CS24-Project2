import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;

public class MyList<E> extends ArrayList<E> {
    protected int DEFAULT_CAPACITY = 10;
    Object elements[];

    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return elements.length;
    }

    @Override
    public void add(int index, E element) {
        if (index == elements.length) {
            ensureCapacity();
        }
        elements[index] = element;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    @SuppressWarnings("unchecked")
    public MyList<E> removeIfDoesNotContain(String e) {
        MyList<E> out = new MyList<E>();
        for (Object elm : elements) {
            // System.out.println(Arrays.deepToString((String[]) elm));
        }
        int index = 0;
        for (int i = 0; i < elements.length; i++) {
            String[] entry = Arrays.deepToString((String[]) elements[i]).split(",");
            for (String val : entry) {
                // System.out.println(Arrays.toString(entry));
                if (val.contains(e)) {
                    out.add(index, (E) entry);
                    // System.out.println(Arrays.toString(entry));
                    index++;
                }
            }
        }
        return out;
    }

    // @Override
    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i >= elements.length || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (E) elements[i];
    }

}