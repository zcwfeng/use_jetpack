package top.zcwfeng.sunflower_java.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import top.zcwfeng.sunflower_java.GardenFragmentDirections;
import top.zcwfeng.sunflower_java.R;
import top.zcwfeng.sunflower_java.data.PlantAndGardenPlantings;
import top.zcwfeng.sunflower_java.databinding.ListItemGardenPlantingBinding;
import top.zcwfeng.sunflower_java.viewmodels.PlantAndGardenPlantingsViewModel;

public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {

    public GardenPlantingAdapter() {
        super(new GardenPlantDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_garden_planting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlantAndGardenPlantings plantings = getItem(position);
        holder.itemView.setTag(plantings);
        holder.bind(createOnClickListener(plantings.getPlant().getPlantId()), plantings);
    }

    private View.OnClickListener createOnClickListener(String plantId) {
        Log.e("zcwfeng", "fix GardenPlantingAdapter click");
        GardenFragmentDirections.ActionGardenFragmentToPlantDetailFragment direction = GardenFragmentDirections.actionGardenFragmentToPlantDetailFragment();
        direction.setPlantId(plantId);
        return v -> Navigation.findNavController(v).navigate(direction);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemGardenPlantingBinding binding;

        public ViewHolder(@NonNull ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, PlantAndGardenPlantings plantings) {
            this.binding.setClickListener(listener);

            this.binding.setViewModel(new PlantAndGardenPlantingsViewModel(plantings));
            this.binding.executePendingBindings();
        }
    }

}