package demo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HumanComparetor implements Comparator<Human> {
    @Override
    public int compare(Human h1, Human h2) {
        if (h1.getAge() > h2.getAge()) {
            return 1;
        } else if (h1.getAge() == h2.getAge()) {
            return 0;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        List<Human> humans = Human.getAInitHumanList();
        Collections.sort(humans, new HumanComparetor());
        System.out.println(humans);
    }
}



