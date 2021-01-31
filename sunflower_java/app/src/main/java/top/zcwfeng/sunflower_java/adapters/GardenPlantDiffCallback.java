package top.zcwfeng.sunflower_java.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import top.zcwfeng.sunflower_java.data.PlantAndGardenPlantings;

public class GardenPlantDiffCallback extends DiffUtil.ItemCallback<PlantAndGardenPlantings> {

    @Override
    public boolean areItemsTheSame(@NonNull PlantAndGardenPlantings oldItem,
                                   @NonNull PlantAndGardenPlantings newItem) {
        return oldItem.getPlant().getPlantId().equals(newItem.getPlant().getPlantId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull PlantAndGardenPlantings oldItem,
                                      @NonNull PlantAndGardenPlantings newItem) {
        return oldItem == newItem;
    }
}
