package com.scy.jvmdemo.memorymgr.outofmemoryerror;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject{

    }
    public static void main(String[] args) {
        List list = new ArrayList();
        while (true)
        list.add(new OOMObject());
    }
}
