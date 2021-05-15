package com.minhthanh.bookshop.home;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minhthanh.bookshop.R;
import com.minhthanh.bookshop.adapter.BookAdapter;
import com.minhthanh.bookshop.adapter.CategoryAdapter;
import com.minhthanh.bookshop.api.GetBookApi;
import com.minhthanh.bookshop.api.GetCategoryApi;
import com.minhthanh.bookshop.databinding.FragmentHomeBinding;
import com.minhthanh.bookshop.home.img_slider.Slide;
import com.minhthanh.bookshop.home.img_slider.SlideAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.Book;
import model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  HomeFragment extends Fragment implements IHome {

    String linkSlide = "http://demo8468432.mockable.io/GetSlider";
    Slide slide;
    List<Slide> slideList;

    private Timer timer;

    ArrayList<Category> categoryList;
    ArrayList<Book> bookList;
    private CategoryAdapter categoryAdapter;
    private BookAdapter bookAdapter;

    RecyclerView recyclerView;
    private Context context;

    FragmentHomeBinding binding;
    private SlideAdapter slideAdapter;
    HomePresenter homePresenter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);

        new GetSlide().execute();


        //recycleVIew
        getCategory();

        return binding.getRoot();
    }




    @Override
    public void onShowSlide(List<Slide> slideList) {
        //TRUYỀN VÀO viewpager
        slideAdapter = new SlideAdapter(getContext(), slideList);
        binding.viewpager.setAdapter(slideAdapter);

        //truyền dl cỉclecandicator
        binding.circleindicator.setViewPager(binding.viewpager);
        slideAdapter.registerDataSetObserver(binding.circleindicator.getDataSetObserver());

        autoSlideImage();
    }


    //dùng retrofit




    private void getCategory(){

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo8468432.mockable.io/")
                // on below line we are calling add
                // Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        GetCategoryApi getCategoryApi = retrofit.create(GetCategoryApi.class);

        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<Category>> call = getCategoryApi.getCategory();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<ArrayList<Category>>() {

            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {
                    // on successful we are hiding our progressbar.
                    binding.loading.setVisibility(View.GONE);

                    // below line is to add our data from api to our array list.
                    categoryList = response.body();

                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < categoryList.size(); i++) {
                        categoryAdapter = new CategoryAdapter(context, categoryList);

                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        // setting layout manager for our recycler view.
                        binding.crvCategory.setLayoutManager(linearLayoutManager);

                        // below line is to set adapter to our recycler view.
                        binding.crvCategory.setAdapter(categoryAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show();

            }
        });
    }



    //lấy ds slide dùng AsynTask
    class GetSlide extends AsyncTask<Void,Void,String>{

        List<Slide> slideList2;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result ="";

            try {
                URL url = new URL(linkSlide);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();

                int byteCharacter;
                while ((byteCharacter = is.read()) != -1){
                    result += (char) byteCharacter;
                }


            } catch (MalformedURLException e) { //Đổi MalformedURLException thành Exception
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            slideList2 = getJSONSlideArray(s);

            onShowSlide(slideList2);

//            //TRUYỀN VÀO viewpager
//            slideAdapter = new SlideAdapter(getContext(), slideList2);
//            binding.viewpager.setAdapter(slideAdapter);
//
//            //truyền dl cỉclecandicator
//            binding.circleindicator.setViewPager(binding.viewpager);
//            slideAdapter.registerDataSetObserver(binding.circleindicator.getDataSetObserver());
//
//            autoSlideImage();
        }
    }

    public List<Slide> getJSONSlideArray(String s){

        slideList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i =0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int resourceID = jsonObject.getInt("resourceID");
                String thumb = jsonObject.getString("thumb");
                String toLink = jsonObject.getString("toLink");

                slide = new Slide(resourceID,thumb,toLink);
                slideList.add(slide);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return slideList;

    }

    private  void  autoSlideImage(){
        if( slideList == null || slideList.isEmpty() || binding.viewpager == null)
            return;

        //init timer
        if(timer == null){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = binding.viewpager.getCurrentItem();
                        int totalItem = slideList.size() -1;
                        if( currentItem< totalItem){
                            currentItem ++;
                            binding.viewpager.setCurrentItem(currentItem);
                        }else {
                            binding.viewpager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500,2000);//set time
    }


    //neu activity k tồn tại thì cancel
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null)
            timer.cancel();
        timer = null;
    }
}

