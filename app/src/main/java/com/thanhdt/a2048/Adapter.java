package com.thanhdt.a2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Integer> {
    private Context context;
    private int mode;

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    private ArrayList<Integer> list;

    public Adapter(@NonNull Context context, int resource, List<Integer> objects, int mode) {
        super(context, resource, objects);
        this.context = context;
        this.list = new ArrayList<>(objects);
        this.mode = mode;
    }

    @Override
    public void notifyDataSetChanged() {
        list = GamePlay.getGamePlay().getListNumber();
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null);
        }

        if (list.size() > 0) {
            Square square = convertView.findViewById(R.id.txtSquare);
            square.setValue(list.get(position), mode);

        }
        return convertView;
    }
}
