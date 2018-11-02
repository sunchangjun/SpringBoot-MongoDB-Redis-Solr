package com.sun.common.excel;

import com.alibaba.fastjson.JSONObject;
import com.sun.common.Excel.PoiExcelUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class poitest {
    @Test
    public void test() throws IOException {
        PoiExcelUtils poiUtils = new PoiExcelUtils();
        ArrayList<ArrayList<String>> list = poiUtils.readExcel("F:/2018-10-31——金庸MV上传结果汇总.xlsx", 0, 1, 2,3,4,5);
        for (ArrayList<String> arry:list) {
            System.out.println(JSONObject.toJSONString(arry));
        }

    }


}

