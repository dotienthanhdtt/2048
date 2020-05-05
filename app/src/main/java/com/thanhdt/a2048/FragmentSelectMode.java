package com.thanhdt.a2048;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FragmentSelectMode extends DialogFragment {
    private Button button4, button6, button8, button10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_mode, container, false);
        button4 = view.findViewById(R.id.mode4);
        button6 = view.findViewById(R.id.mode6);
        button8 = view.findViewById(R.id.mode8);
        button10 = view.findViewById(R.id.mode10);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).selectMode.setText("4X4");
                getDialog().dismiss();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).selectMode.setText("6X6");
                getDialog().dismiss();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).selectMode.setText("8X8");
                getDialog().dismiss();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).selectMode.setText("10X10");
                getDialog().dismiss();
            }
        });
        return view;
    }
}
