package top.zcwfeng.sunflower_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ListAdapter;

import top.zcwfeng.sunflower_java.adapters.PlantAdapter;
import top.zcwfeng.sunflower_java.databinding.FragmentPlantListBinding;
import top.zcwfeng.sunflower_java.utilities.InjectorUtils;
import top.zcwfeng.sunflower_java.viewmodels.PlantListViewModel;
import top.zcwfeng.sunflower_java.viewmodels.PlantListViewModelFactory;

public class PlantListFragment extends Fragment {

    private PlantListViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater, container, false);
        PlantListViewModelFactory factory = InjectorUtils.providePlantListViewModelFactory(getContext());
        ListAdapter adapter = new PlantAdapter();
        mViewModel = new ViewModelProvider(this, factory).get(PlantListViewModel.class);
        //        mViewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);
        binding.plantList.setAdapter(adapter);
        subscribeUi(adapter);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_zone:
                updateData();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void subscribeUi(ListAdapter adapter) {
        mViewModel.plants.observe(getViewLifecycleOwner(), plants -> {
            if (plants != null) {
                adapter.submitList(plants);
            }
        });
    }

    private void updateData() {
        if (mViewModel.isFiltered()) {
            mViewModel.cleanGrowZoneNumber();
        } else {
            mViewModel.setGrowZoneNumber(9);
        }
    }
}