package com.lotus.common;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.kit.MapKit;
import org.junit.Test;

public class CommonTest {
    @Test
    public void testObkectToMap() {
        System.out.println(MapKit.objToMap(AjaxResult.success("hell OK")));
    }
}
