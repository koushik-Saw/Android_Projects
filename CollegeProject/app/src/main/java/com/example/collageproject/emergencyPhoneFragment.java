package com.example.collageproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class emergencyPhoneFragment extends Fragment {


    public emergencyPhoneFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_emergency_phone, container, false);

        WebView webView = view.findViewById(R.id.epn_wevID);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        ProgressBar progressBar = view.findViewById(R.id.epn_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        String data = "https://whitepagesbd.com/enumbers.aspx?fbclid=IwAR0F-_zie2DYzEVCr2H5lW0hMHAp3Cxt9heuqidA3QMeNvdPzCUBHyb-S0Y";
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