package top.zcwfeng.sunflower_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import top.zcwfeng.sunflower_java.adapters.GardenPlantingAdapter;
import top.zcwfeng.sunflower_java.databinding.FragmentGardenBinding;
import top.zcwfeng.sunflower_java.utilities.InjectorUtils;
import top.zcwfeng.sunflower_java.viewmodels.GardenPlantingListViewModel;
import top.zcwfeng.sunflower_java.viewmodels.GardenPlantingListViewModelFactory;


public class GardenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater,container,false);
        GardenPlantingAdapter adapter = new GardenPlantingAdapter();
        binding.gardenList.setAdapter(adapter);
        subScribeUi(adapter, binding);
        return binding.getRoot();
    }

    private void subScribeUi(@NonNull GardenPlantingAdapter adapter, @NonNull FragmentGardenBinding binding) {
        GardenPlantingListViewModelFactory factory =
                InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext());
//        GardenPlantingListViewModel viewModel =
//                ViewModelProviders.of(this, factory).get(GardenPlantingListViewModel.class);
        GardenPlantingListViewModel viewModel =
                new ViewModelProvider(this, factory).get(GardenPlantingListViewModel.class);

        viewModel.gardenPlantings.observe(getViewLifecycleOwner(), gardenPlantings ->
                binding.setHasPlantings(gardenPlantings != null && !gardenPlantings.isEmpty()));

        viewModel.plantAndGardenPlantings.observe(getViewLifecycleOwner(), plantAndGardenPlantings -> {
            if (plantAndGardenPlantings != null && !plantAndGardenPlantings.isEmpty()) {
                adapter.submitList(plantAndGardenPlantings);
            }
        });
    }
}