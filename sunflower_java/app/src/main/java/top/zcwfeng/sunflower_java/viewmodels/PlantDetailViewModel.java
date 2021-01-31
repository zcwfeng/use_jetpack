package top.zcwfeng.sunflower_java.viewmodels;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import top.zcwfeng.sunflower_java.data.GardenPlanting;
import top.zcwfeng.sunflower_java.data.GardenPlantingRepository;
import top.zcwfeng.sunflower_java.data.Plant;
import top.zcwfeng.sunflower_java.data.PlantRepository;
import top.zcwfeng.sunflower_java.utilities.AppExecutors;

public class PlantDetailViewModel extends ViewModel {
    private GardenPlantingRepository gardenPlantingRepository;
    private PlantRepository plantRepository;

    private String plantId;

    private LiveData<Boolean> isPlanted;
    public LiveData<Plant> plant;

    PlantDetailViewModel(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        super();
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;

        /* The getGardenPlantingForPlant method returns a LiveData from querying the database. The
         * method can return null in two cases: when the database query is running and if no records
         * are found. In these cases isPlanted is false. If a record is found then isPlanted is
         * true. */
        LiveData<GardenPlanting> gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isPlanted = Transformations.map(gardenPlantingForPlant, Objects::nonNull);
        }else{
            isPlanted = Transformations.map(gardenPlantingForPlant, it -> it != null);
        }
        plant = plantRepository.getPlant(plantId);
    }

    public void addPlantToGarden() {
        AppExecutors.getInstance().diskIO().execute(() -> gardenPlantingRepository.createGardenPlanting(plantId));
    }

    public LiveData<Boolean> getIsPlanted() {
        return isPlanted;
    }
}