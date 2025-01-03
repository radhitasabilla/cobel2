package com.example.course;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Variable Global
    private EditText txtNom;
    private EditText txtPrix;
    private RadioButton radFruits;
    private RadioButton radViande;
    private RadioButton radLait;
    private RadioButton radPatisserie;
    private Button btnAdd;
    private ListView lstEpicerie;
    private TextView lblPrixTotal;
    private LinearLayout oneLigne_lst;


    //pour ne pas la redeclarer a chaque Click // et pour les trouver par tout
    ArrayList<ProduitC> myArray = new ArrayList<ProduitC>();
    double PrixTotal=0;
    int counter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views
        txtNom = (EditText) findViewById(R.id.txtNom_ID);
        txtPrix = (EditText) findViewById(R.id.txtPrix_ID);
        radFruits = (RadioButton) findViewById(R.id.radFruits_ID);
        radViande = (RadioButton) findViewById(R.id.radViande_ID);
        radLait = (RadioButton) findViewById(R.id.radLait_ID);
        radPatisserie = (RadioButton) findViewById(R.id.radPatisserie_ID);
        btnAdd = (Button) findViewById(R.id.btnAdd_ID);
        lstEpicerie = (ListView) findViewById(R.id.lstEpicerie_ID);

        oneLigne_lst = (LinearLayout) findViewById(R.id.oneLigne_lst_ID);

        lblPrixTotal = (TextView) findViewById(R.id.lblPrixTotal_ID);
    }



    public void AddToListView(View view) {

        String nom="";
        double prix=0;
        //get
        try{
            nom = txtNom.getText().toString();
            prix = Double.parseDouble(txtPrix.getText().toString());
        }catch (Exception ex){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(ex.getMessage());
            alert.setTitle("Erreur d'input");
            alert.create();
            alert.show();
        }

        if(radFruits.isChecked()){
            myArray.add(new ProduitC(nom,prix,R.drawable.fruit));
            PrixTotal+=prix;
            lblPrixTotal.setText(String.valueOf(PrixTotal));
        }else if (radViande.isChecked()){
            myArray.add(new ProduitC(nom,prix,R.drawable.viande));
            PrixTotal+=prix;
            lblPrixTotal.setText(String.valueOf(PrixTotal));
        }else if (radLait.isChecked()){
            myArray.add(new ProduitC(nom,prix,R.drawable.lait));
            PrixTotal+=prix;
            lblPrixTotal.setText(String.valueOf(PrixTotal));
        }else if (radPatisserie.isChecked()){
            myArray.add(new ProduitC(nom,prix,R.drawable.pattiserie));
            PrixTotal+=prix;
            lblPrixTotal.setText(String.valueOf(PrixTotal));
        }

        myAdapterC adapter = new myAdapterC
                (this,R.layout.layout_costum_listitem,myArray);
        lstEpicerie.setAdapter(adapter);


        Context ctx = this;
        //remove after a long click listner
        lstEpicerie.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
                alert.setTitle("Warning closing");
                alert.setMessage("Vous etes sur de suprimer cet aliment ?");

                alert.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PrixTotal-=myArray.get(position).getPrix();
                        lblPrixTotal.setText(String.valueOf(PrixTotal));
                        myArray.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });

                alert.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LinearLayout oneLnr = (LinearLayout) view.findViewById(R.id.oneLigne_lst_ID);
                        oneLnr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        counter++;
                    }
                });

                alert.create();
                alert.show();


                return false;
            }
        });


        //one click green/black Toggle
        lstEpicerie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                counter++;
                LinearLayout oneLnr = (LinearLayout) view.findViewById(R.id.oneLigne_lst_ID);
                if(counter%2==0){
                    oneLnr.setBackgroundColor(Color.parseColor("#3984F600"));
                }else{
                    oneLnr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }
        });


    }
}