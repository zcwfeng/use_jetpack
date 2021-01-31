package top.zcwfeng.sunflower_java.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlantTest {

    private Plant plant;

    @Before
    public void setUp() {
        this.plant = new Plant("1", "https://upload.wikimedia.org/wikipedia/commons/5/55/Apple_orchard_in_Tasmania.jpg", "Tomato", "A red vegatable", 1, 2);
    }

    @Test
    public void testDefaultValues() {
        Plant defaultPlant = new Plant("2", "https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg", "Apple", "Description", 1, -1);
        assert 7 == defaultPlant.getWateringInterval();
        assert "" == defaultPlant.getImageUrl();
    }

    @Test
    public void testShouldBeWatered() {
        Calendar now = Calendar.getInstance();

        // Test for lastWateringDate is today.
        assertFalse(plant.shouldBeWatered(now, getNewCalendar(now, Calendar.DAY_OF_YEAR, -0)));

        // Test for lastWateringDate is yesterday.
        assertFalse(plant.shouldBeWatered(now, getNewCalendar(now, Calendar.DAY_OF_YEAR, -1)));

        // Test for lastWateringDate is the day before yesterday.
        assertFalse(plant.shouldBeWatered(now, getNewCalendar(now, Calendar.DAY_OF_YEAR, -2)));

        // Test for lastWateringDate is some days ago, three days ago, four days ago etc.
        assertTrue(plant.shouldBeWatered(now, getNewCalendar(now, Calendar.DAY_OF_YEAR, -3)));
    }

    private static Calendar getNewCalendar(Calendar cal, int field, int amount) {
        Calendar newCal = Calendar.getInstance();
        newCal.setTime(cal.getTime());
        newCal.add(field, amount);
        return newCal;
    }
}