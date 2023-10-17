package com.dani.practica_listitem;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record implements Comparable<Record>{
        public int intents;
        public String nom;

        public int profile;
        public Record(int _intents, String _nom, int _foto ) {
            intents = _intents;
            nom = _nom;
            profile = _foto;
        }
        @Override
        public int compareTo(Record otro) {
            if (this.intents < otro.intents) {
                return -1;
            } else if (this.intents == otro.intents){
                return 0;
            } else {
                return 1;
            }
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> nombres = new ArrayList<>();
        nombres.add("Po");
        nombres.add("María");
        nombres.add("Aguacate");
        nombres.add("Tinquiwinqui");
        nombres.add("Carlos");
        nombres.add("Dani");
        nombres.add("Akane");
        nombres.add("Juan");
        nombres.add("María");
        nombres.add("Pedro Picapiedra");
        nombres.add("Luisa");
        nombres.add("Bosh");
        nombres.add("Laura");
        nombres.add("Andrés");
        nombres.add("Ana");
        nombres.add("Gabriel");
        nombres.add("Messi");
        nombres.add("Angelina");
        nombres.add("Isabella");
        nombres.add("Javier");
        nombres.add("Carmen");
        nombres.add("Shawn");
        nombres.add("Valentina");
        nombres.add("Emilio");
        nombres.add("Valeria");
        nombres.add("Fernando");
        nombres.add("Camila");
        nombres.add("Pablo");
        nombres.add("Antonia");
        nombres.add("Diego");
        nombres.add("Natalia");
        nombres.add("Daniel");
        nombres.add("Lucía");
        nombres.add("Gonzalo");
        nombres.add("Ayumi");
        nombres.add("Rodrigo");
        nombres.add("Alejandra");
        nombres.add("Robertotototo");
        nombres.add("Verónica");
        nombres.add("Cocodrilo Sacamuelas");
        nombres.add("Carolina");
        nombres.add("Arturo");
        nombres.add("Goku");
        nombres.add("Lorenzo");
        nombres.add("Pedrito");
        nombres.add("Héctor");
        nombres.add("Victoria");
        nombres.add("Joel");

        ArrayList<Integer> icons = new ArrayList<Integer>();
        icons.add(R.drawable.img1);
        icons.add(R.drawable.img2);
        icons.add(R.drawable.img3);
        icons.add(R.drawable.img4);
        icons.add(R.drawable.img5);
        icons.add(R.drawable.img6);
        icons.add(R.drawable.img7);
        icons.add(R.drawable.img8);
        icons.add(R.drawable.img9);

        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Manolo", R.drawable.img1) );
        records.add( new Record(12,"Pepe", R.drawable.img2) );
        records.add( new Record(42,"Laura", R.drawable.img3) );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                int imagenId = R.drawable.img1;
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));

                ImageView img = convertView.findViewById(R.id.profile);
                int imageResource = getItem(pos).profile;
                img.setImageResource(imageResource);

                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);

        Collections.sort(records);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<10;i++) {
                    int intents = r.nextInt(100)+1;
                    int nom = r.nextInt(nombres.size());
                    int p = r.nextInt(9);
                    records.add(new Record(intents, nombres.get(nom), icons.get(p)));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });

        Button b2 = (Button) findViewById(R.id.ordenar);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(records);
                adapter.notifyDataSetChanged();
            }
        });

    }
}