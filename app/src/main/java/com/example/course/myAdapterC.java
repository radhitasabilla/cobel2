package com.example.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class myAdapterC extends ArrayAdapter<ProduitC> {

    //props
    private Context context;
    private int resource;
    private ArrayList<ProduitC> myArray = new ArrayList<ProduitC>();

    public myAdapterC(@NonNull Context _context, int _resource, @NonNull List<ProduitC> _objects) {
        super(_context, _resource, _objects);
        context = _context;
        resource = _resource;
        myArray = (ArrayList<ProduitC>) _objects;
    }

    @NonNull
    @Override
    public View getView(int _position, @Nullable View _convertView, @NonNull ViewGroup _parent) {

        _convertView = LayoutInflater.from(context).inflate(resource,_parent,false);

        //Views
        ImageView img = (ImageView) _convertView.findViewById(R.id.img_lst_ID);
        TextView txtNom = (TextView) _convertView.findViewById(R.id.txtNom_lst_ID);
        TextView txtPrix = (TextView) _convertView.findViewById(R.id.txtPrix_lst_ID);

        //get-set
        ProduitC produitC_i = myArray.get(_position);
        img.setImageResource(produitC_i.getImg());
        txtNom.setText(produitC_i.getNom());
        txtPrix.setText(String.valueOf(produitC_i.getPrix()));

        return _convertView;
    }
}
