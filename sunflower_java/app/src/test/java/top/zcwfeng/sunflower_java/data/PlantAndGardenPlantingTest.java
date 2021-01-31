package top.zcwfeng.sunflower_java.data;

import org.junit.Test;

public class PlantAndGardenPlantingTest {

    @Test
    public void testDefaultValues() {
        PlantAndGardenPlantings p = new PlantAndGardenPlantings();
        assert p.getGardenPlantings().isEmpty();
    }
}