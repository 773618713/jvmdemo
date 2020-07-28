package com.scy.jvmdemo.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception {
        //sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
        //1.最简单的使用方式是基于反射获取Unsafe实例
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe U = (Unsafe) f.get(null);

        O[] tab = new O[32];
        O a = new O();
        a.num = 3;

        boolean r = U.compareAndSwapObject(tab, (18 << 2) + 16, null, a);
        System.out.println(r);
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == null){
                System.out.println(tab[i]+"="+i);
            }else {
                System.out.println(tab[i].num);
            }
        }

        /*ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(3, "zhangsan");*/
    }
}
