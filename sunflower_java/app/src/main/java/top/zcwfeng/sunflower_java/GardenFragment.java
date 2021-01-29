package top.zcwfeng.sunflower_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import top.zcwfeng.sunflower_java.databinding.FragmentGardenBinding;


public class GardenFragment extends Fragment {


    public GardenFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_garden, container, false);
        return binding.getRoot();
    }
}