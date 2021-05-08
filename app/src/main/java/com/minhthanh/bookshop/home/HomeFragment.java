package com.minhthanh.bookshop.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.minhthanh.bookshop.R;
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

public class HomeFragment extends Fragment {

    String linkSlide = "http://demo8468432.mockable.io/GetSlider";
    Slide slide;
    List<Slide> slideList;

    FragmentHomeBinding binding;
    private SlideAdapter slideAdapter;

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

//        //TRUYỀN VÀO viewpager
//        imageAdapter = new ImageAdapter(getContext(), getListImage());
//        binding.viewpager.setAdapter(imageAdapter);
//
//        //truyền dl cỉclecandicator
//        binding.circleindicator.setViewPager(binding.viewpager);
//        imageAdapter.registerDataSetObserver(binding.circleindicator.getDataSetObserver());

        new GetSlide().execute();
        return binding.getRoot();
    }

    private List<Slide> getListImage(){
        List<Slide> slideList = new ArrayList<>();
        slideList.add(new Slide(-1,"https://cdn0.fahasa.com/media/magentothem/banner7/Resize_920x420.jpg",null));
        slideList.add(new Slide(-1,"https://cdn0.fahasa.com/media/magentothem/banner7/920_x_420.jpg",null));
        slideList.add(new Slide(-1,"https://cdn0.fahasa.com/media/magentothem/banner7/zalopay0521.png",null));
        slideList.add(new Slide(-1,"https://cdn0.fahasa.com/media/magentothem/banner7/VNPAY1_920x420.png",null));
        slideList.add(new Slide(-1,"https://cdn0.fahasa.com/media/magentothem/banner7/airpay_920_x_420_2.jpg",null));

        return slideList;
    }

    //lấy ds slide
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

            slideList2 = getJSONSlideArray(linkSlide);

            //TRUYỀN VÀO viewpager
            slideAdapter = new SlideAdapter(getContext(), slideList2);
            binding.viewpager.setAdapter(slideAdapter);

            //truyền dl cỉclecandicator
            binding.circleindicator.setViewPager(binding.viewpager);
            slideAdapter.registerDataSetObserver(binding.circleindicator.getDataSetObserver());
        }
    }

    public List<Slide> getJSONSlideArray(String linkSlide){

        String result = "";
        slideList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(linkSlide);
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
}
