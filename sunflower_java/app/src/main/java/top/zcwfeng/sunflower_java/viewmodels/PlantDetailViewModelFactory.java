package top.zcwfeng.sunflower_java.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import top.zcwfeng.sunflower_java.data.GardenPlantingRepository;
import top.zcwfeng.sunflower_java.data.PlantRepository;

public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private String plantId;
    private PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;

    public PlantDetailViewModelFactory(PlantRepository plantRepository,
                                       GardenPlantingRepository gardenPlantingRepository,
                                       String plantId) {
        this.plantId = plantId;
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId);
    }
}