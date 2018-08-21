package com.sun.common.gc;
import com.sun.base.BaseJunitTest;
import org.junit.Test;

/**
 *
 */
public class GcTest extends BaseJunitTest {

    @Test
    public  void test(){
        System.out.print("1");
        Runtime.getRuntime().gc();
    }
}
