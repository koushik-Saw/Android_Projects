package com.example.safety.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.safety.R;


public class privacyFragment extends Fragment {

    private WebView webView;

    public privacyFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_privacy, container, false);

        webView = view.findViewById(R.id.privacyWebID);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/privacy_policy.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        return view;
    }
}