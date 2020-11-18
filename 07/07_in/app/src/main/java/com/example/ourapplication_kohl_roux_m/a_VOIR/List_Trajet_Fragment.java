package com.example.ourapplication_kohl_roux_m.a_VOIR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ourapplication_kohl_roux_m.R;

public class List_Trajet_Fragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_list_trajet, container, false);
    }
}
