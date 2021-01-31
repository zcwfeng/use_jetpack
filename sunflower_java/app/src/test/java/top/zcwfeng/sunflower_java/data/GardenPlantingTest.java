package top.zcwfeng.sunflower_java.data;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GardenPlantingTest {

    @Test
    public void testDefaultValues() {
        GardenPlanting gardenPlanting = new GardenPlanting("1", null, null);
        Calendar cal = Calendar.getInstance();
        assertYMD(cal, gardenPlanting.getPlantDate());
        assertYMD(cal, gardenPlanting.getLastWateringDate());
        assertEquals(0L, gardenPlanting.getGardenPlantingId());
    }

    // Only Year/Month/Day precision is needed for comparing GardenPlanting Calendar entries
    private static void assertYMD(Calendar expectedCal, Calendar actualCal) {
        assertThat(actualCal.get(Calendar.YEAR), CoreMatchers.equalTo(expectedCal.get(Calendar.YEAR)));
        assertThat(actualCal.get(Calendar.MONTH), CoreMatchers.equalTo(expectedCal.get(Calendar.MONTH)));
        assertThat(actualCal.get(Calendar.DAY_OF_MONTH), CoreMatchers.equalTo(expectedCal.get(Calendar.DAY_OF_MONTH)));
    }
}