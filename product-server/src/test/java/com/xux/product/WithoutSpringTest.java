package com.xux.product;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 14:53
 */
public class WithoutSpringTest {

    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next == 3)iterator.remove();
        }
        System.out.println(list);
    }
}
