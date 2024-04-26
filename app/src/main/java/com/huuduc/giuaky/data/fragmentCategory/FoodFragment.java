package com.huuduc.giuaky.data.fragmentCategory;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.huuduc.giuaky.R;
import com.huuduc.giuaky.activity.ShowDetailActivity;
import com.huuduc.giuaky.data.product.Product;
import com.huuduc.giuaky.adapter.ProductAdapter;
import com.huuduc.giuaky.interfaces.ItemClickListener;
import com.huuduc.giuaky.repo.DSProduct;
import com.huuduc.giuaky.retrofit.ProductApi;
import com.huuduc.giuaky.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private View mView;

//    private DSProduct products;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodFragment newInstance(String param1, String param2) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_food, container, false);

        //Ánh xạ
        recyclerView = mView.findViewById(R.id.rcv_food);
//        products= new DSProduct();

//        productAdapter = new ProductAdapter(getContext(), products, new ItemClickListener() {
//            @Override
//            public void onClickItem(Product product) {
//                onClickToDetail(product);
//            }
//        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//        productAdapter.setData(products.getListByType(1,products.getProducts()));

//        recyclerView.setAdapter(productAdapter);

        loadProductFood();
        return mView;
    }

    private void loadProductFood() {
        RetrofitService retrofitService = new RetrofitService();
        ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);
        productApi.getAllByType(1)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Save error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Product> body) {
        productAdapter = new ProductAdapter(getContext(), body, new ItemClickListener() {
            @Override
            public void onClickItem(Product product) {
                onClickToDetail(product);
            }
        });

        recyclerView.setAdapter(productAdapter);
    }

    public void onClickToDetail(Product product) {
        Intent intent = new Intent(getContext(), ShowDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_product", product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //    public List<Product> getListData(){
//
//        List<Product> list = new ArrayList<>();
//
//        list.add(new Product("Salad cá ngừ",30,"Food",R.drawable.food,true));
//        list.add(new Product("Salad cá ngừ",30,"Food",R.drawable.food,true));
//        list.add(new Product("Salad cá ngừ",30,"Food",R.drawable.food,true));
//        list.add(new Product("Salad cá ngừ",30,"Food",R.drawable.food,true));
//        list.add(new Product("Salad cá ngừ",30,"Food",R.drawable.food,true));
//        return list;
//    }
}