package com.example.android_development_assessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_development_assessment.model.SymbolsModel;
import com.example.android_development_assessment.repository.SymbolsRepo;

import java.util.List;

public class SymbolViewModel extends AndroidViewModel {

    private SymbolsRepo symbolsRepo;

    public SymbolViewModel(@NonNull Application application) {
        super(application);
        symbolsRepo = new SymbolsRepo(application);
    }

    public LiveData<SymbolsModel> getsymbols(){
        return symbolsRepo.getsymbols();
    }
}
