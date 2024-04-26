package com.huuduc.giuaky.activity;


/*
* SnackSnap App
*
* @author: NguyenHuuDuc
* */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.huuduc.giuaky.R;
import com.huuduc.giuaky.data.User.User;
import com.huuduc.giuaky.data.fragmentMain.AccountFragment;
import com.huuduc.giuaky.data.fragmentMain.FavoriteFragment;
import com.huuduc.giuaky.data.fragmentMain.HomeFragment;
import com.huuduc.giuaky.data.fragmentMain.ViewPagerAdapter;
import com.huuduc.giuaky.retrofit.CartApi;
import com.huuduc.giuaky.retrofit.RetrofitService;
import com.huuduc.giuaky.retrofit.UserApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;

    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ view
        setControl();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,fragmentArrayList);
        viewPager.setAdapter(viewPagerAdapter);
        //Giải quyết thao tác
        setEvent();
        loadUser();
    }

    private void loadUser() {
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        userApi.getByCartId(1)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            user=response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Error user",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void setEvent() {

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_favorite);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_home);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_account);
                        break;
                    default:
                        bottomNavigationView.setSelectedItemId(R.id.menu_home);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_favorite) {
                    viewPager.setCurrentItem(0);
                } else if (itemId == R.id.menu_home) {
                    viewPager.setCurrentItem(1);
                } else if (itemId == R.id.menu_account) {
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });

    }

    public void setControl(){

        viewPager = findViewById(R.id.view_page);
        bottomNavigationView=findViewById(R.id.bottom);
        fragmentArrayList.add(new FavoriteFragment());
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new AccountFragment());

    }
}