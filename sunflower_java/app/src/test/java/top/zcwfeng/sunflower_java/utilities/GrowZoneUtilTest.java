package top.zcwfeng.sunflower_java.utilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrowZoneUtilTest {

    @Test
    public void getZoneForLatitude() {
        assertEquals(13, GrowZoneUtil.getZoneForLatitude(0.0));
        assertEquals(13, GrowZoneUtil.getZoneForLatitude(7.0));
        assertEquals(12, GrowZoneUtil.getZoneForLatitude(7.1));
        assertEquals(1, GrowZoneUtil.getZoneForLatitude(84.1));
        assertEquals(1, GrowZoneUtil.getZoneForLatitude(90.0));
    }
}