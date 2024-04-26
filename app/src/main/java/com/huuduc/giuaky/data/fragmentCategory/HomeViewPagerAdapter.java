package com.huuduc.giuaky.data.fragmentCategory;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    public HomeViewPagerAdapter(@NonNull Fragment fragment,List<Fragment> list) {
        super(fragment);
        this.fragmentList=list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        switch (position){
//            case 0:
//                return new FoodFragment();
//            case 1:
//                return new DrinkFragment();
//            case 2:
//                return new SnackFragment();
//            case 3:
//                return new SauceFragment();
//            default:
//                return new FoodFragment();
//        }
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
