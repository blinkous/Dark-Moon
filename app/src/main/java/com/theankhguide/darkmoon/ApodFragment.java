package com.theankhguide.darkmoon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ApodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ApodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApodFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ApodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApodFragment newInstance(String param1, String param2) {
        ApodFragment fragment = new ApodFragment();
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
        getApod();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apod, container, false);
    }

    private void getApod(){
        /**Getting the NASA APOD data and display it*/
        //Create a handler for the RetrofitInstance interface//
        GetNasaApodData service = RetrofitNasaClient.getRetrofitInstance().create(GetNasaApodData.class);

        Call<RetroNasa> call = service.getAllNasa();

        //Execute the request asynchronously//
        call.enqueue(new Callback<RetroNasa>() {
            //Handle a successful response//
            @Override
            public void onResponse(Call<RetroNasa> call, Response<RetroNasa> response) {
                if(response.body() != null) {
                    loadDataList(response.body());
                }
            }

            //Handle execution failures//
            @Override
            public void onFailure(Call<RetroNasa> call, Throwable throwable) {
                //If the request fails, then display the following toast//
                Toast.makeText(getActivity(), "Unable to load APOD data", Toast.LENGTH_SHORT).show();
                Log.d("nasa", "onFailure:" + throwable);
            }
        });
    }


    private void loadDataList (RetroNasa nasaList) {
        if(!nasaList.get_title().isEmpty() && !nasaList.get_explanation().isEmpty() && !nasaList.get_url().isEmpty()){
            // Get references to the objects in the layout by using getView() to access the root view since this is a Fragment
            TextView textTitle = getView().findViewById(R.id.textTitle);
            TextView desc = getView().findViewById(R.id.textDesc);
            ImageView imageView = getView().findViewById(R.id.imageView);

            // Setting the layout objects
            textTitle.setText(nasaList.get_title());
            desc.setText(nasaList.get_explanation());
            Picasso.get().load(nasaList.get_url()).into(imageView);
//            Log.d("home", "loadDataList: **********" + nasaList.get_url() + "*************");
            Log.d("home", "loadDataList: ********** GETTING DATA *************");
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
