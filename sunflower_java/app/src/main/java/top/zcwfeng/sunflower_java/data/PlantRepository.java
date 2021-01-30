package top.zcwfeng.sunflower_java.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlantRepository {
    private static PlantRepository instance;
    private PlantDao plantDao;

    private PlantRepository(PlantDao gardenPlantingDao) {
        this.plantDao = gardenPlantingDao;
    }

    public static PlantRepository getInstance(PlantDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (PlantRepository.class) {
                if (instance == null) {
                    instance = new PlantRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }

    public LiveData<List<Plant>> getPlants() {
        return this.plantDao.getPlants();
    }

    public LiveData<Plant> getPlant(String plantId) {
        return this.plantDao.getPlant(plantId);
    }

    public LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber) {
        return this.plantDao.getPlantsWithGrowZoneNumber(growZoneNumber);
    }
}
