package com.escodro.saatila.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.escodro.saatila.R;
import com.escodro.saatila.SaatilaApplication;
import com.escodro.saatila.databinding.FragmentWeatherBinding;
import com.escodro.saatila.viewmodel.WeatherViewModel;

import javax.inject.Inject;

/**
 * Created by IgorEscodro on 17/12/16.
 */
public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding mBinding;

    @Inject
    WeatherViewModel mWeatherViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SaatilaApplication.get(getContext()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_weather,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setViewModel(mWeatherViewModel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWeatherViewModel.onDestroy();
    }
}
