package com.example.cm.teacher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_blank, container, false);
        MobileAds.initialize(getContext(), "ca-app-pub-9566226877101037/7656799605");
        NativeExpressAdView adView = (NativeExpressAdView)view.findViewById(R.id.adView);

        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
        return view;
    }

}
