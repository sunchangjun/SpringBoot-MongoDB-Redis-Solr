package com.sun.common;

import com.sun.enums.GenreEnum;
import org.junit.Test;

public class EnumTest {

    @Test
    public void  test(){
        test1(GenreEnum.GENRE_1);
    }

    public void test1(GenreEnum genreEnum){
    System.out.println(genreEnum.getName());
    }
}
