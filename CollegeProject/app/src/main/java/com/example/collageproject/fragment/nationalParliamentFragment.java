package com.example.collageproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.collageproject.R;


public class nationalParliamentFragment extends Fragment {



    public nationalParliamentFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_national_parliament, container, false);

        WebView webView = view.findViewById(R.id.national_parliament_wevID);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        ProgressBar progressBar = view.findViewById(R.id.national_parliament_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        String data = "http://www.parliament.gov.bd/?fbclid=IwAR3TO1XHRjq8IRq7LDdu2T9KTGR_pEgVYFlJgqS8-LVECBriwmZQbb6ANjE";
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setBuiltInZoomControls(true);



       /* webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(data);
                return true;
            }

        });
*/

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //getSupportActionBar().setTitle("Loading....");

                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                    // getSupportActionBar().setTitle(R.string.app_name);

                }
            }
        });
        webView.loadUrl(data);


        return view;
    }
}