package com.example.plisya;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Listita extends AppCompatActivity {

    private TextView textView;
    private ListView listView1;
    private List<MyCuenta> list1;
    private ListView listView2;
    private List<MyCuenta> list2;
    private ListView listView3;
    private List<MyCuenta> list3;
    private int []imagenUser = { R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
    private int []imagen = { R.drawable.editar,R.drawable.borrar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listita);

        int numArchivo = getIntent().getExtras().getInt("numArchivo");

        textView = (TextView) findViewById(R.id.textViewL1);

        JSON json = new JSON();

        try {
            BufferedReader fileU = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + ".txt")));
            String lineaTextoU = fileU.readLine();
            String completoTextoU = "";
            while(lineaTextoU != null){
                completoTextoU = completoTextoU + lineaTextoU;
                lineaTextoU = fileU.readLine();
            }
            MyInfo datosU = json.leerJson(completoTextoU);
            fileU.close();

            textView.setText("Cuentas de " + datosU.getUsuario());

            listView1 = (ListView) findViewById(R.id.listViewId1);
            list1 = new ArrayList<MyCuenta>();

            listView2 = (ListView) findViewById(R.id.listViewId2);
            list2 = new ArrayList<MyCuenta>();

            listView3 = (ListView) findViewById(R.id.listViewId3);
            list3 = new ArrayList<MyCuenta>();

            boolean BucleArchivo = true;
            int x = 1;
            while (BucleArchivo) {
                File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + numArchivo + "." + x + ".txt");
                if(Cfile.exists()) {
                    BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + "." + x + ".txt")));
                    String lineaTexto = file.readLine();
                    String completoTexto = "";
                    while(lineaTexto != null){
                        completoTexto = completoTexto + lineaTexto;
                        lineaTexto = file.readLine();
                    }
                    file.close();

                    MyCuenta datos = json.leerJsonCuenta(completoTexto);

                    MyCuenta cuenta1 = new MyCuenta();
                    MyCuenta cuenta2 = new MyCuenta();
                    MyCuenta cuenta3 = new MyCuenta();
                    cuenta1.setPassCuenta(datos.getPassCuenta());
                    cuenta1.setNameCuenta(datos.getNameCuenta());
                    cuenta1.setImage(datos.getImage());
                    cuenta2.setImage(imagen[0]);
                    cuenta3.setImage(imagen[1]);

                    list1.add(cuenta1);
                    list2.add(cuenta2);
                    list3.add(cuenta3);
                    x = x + 1;
                }else{
                    BucleArchivo = false;
                }
            }

            MyAdapter myAdapter1 = new MyAdapter(list1, getBaseContext());
            listView1.setAdapter(myAdapter1);
            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    toast1( i );
                }
            });

            MyAdapterEditar myAdapter2 = new MyAdapterEditar(list2, getBaseContext());
            listView2.setAdapter(myAdapter2);
            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toast2(i);
                }
            });

            MyAdapterBorrar myAdapter3 = new MyAdapterBorrar(list3, getBaseContext());
            listView3.setAdapter(myAdapter3);
            listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toast3(i);
                }
            });
        }catch(Exception e){
            Toast.makeText(getBaseContext(), "Error al Cargar la Lista", Toast.LENGTH_SHORT).show();
        }
    }

    private void toast1( int i )
    {
        Toast.makeText(getBaseContext(), list1.get(i).getPassCuenta(), Toast.LENGTH_SHORT).show();
    }

    private void toast2( int i )
    {
        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        Intent intent1 = new Intent (Listita.this, EditarListita.class);
        intent1.putExtra("numArchivo", numArchivo);
        intent1.putExtra("numContext", 2);
        intent1.putExtra("numArchivoCuenta", (i + 1));
        startActivity( intent1 );
    }

    private void toast3( int i )
    {
        try {
            int numArchivo = getIntent().getExtras().getInt("numArchivo");
            boolean BucleArchivo = true;
            int x = (i + 1);
            while (BucleArchivo) {
                File Cfile1 = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + numArchivo + "." + x + ".txt");
                File Cfile2 = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + numArchivo + "." + (x + 1) + ".txt");
                if (Cfile1.exists() & Cfile2.exists()) {
                    int numArchivoCuenta = getIntent().getExtras().getInt("numArchivoCuenta");
                    BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + "." + (x + 1) + ".txt")));
                    String lineaTexto = file.readLine();
                    String completoTexto = "";
                    while(lineaTexto != null){
                        completoTexto = completoTexto + lineaTexto;
                        lineaTexto = file.readLine();
                    }
                    file.close();

                    BufferedWriter fileC = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + numArchivo + "." + x + ".txt", Context.MODE_PRIVATE)));
                    fileC.write(completoTexto);
                    fileC.close();

                    x = x + 1;
                }
                if (Cfile1.exists() & !Cfile2.exists()) {
                    File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + numArchivo + "." + x + ".txt");
                    Cfile.delete();
                    Intent intent = new Intent (Listita.this, Listita.class);
                    intent.putExtra("numArchivo", numArchivo);
                    startActivity( intent );
                    BucleArchivo = false;
                }
            }
        }catch(Exception e){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean flag = false;
        MenuInflater menuInflater = null;
        flag = super.onCreateOptionsMenu(menu);
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return flag;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String seleccion = null;
        switch(item.getItemId()){

            case R.id.MenuNuevoId:
                int numArchivo = getIntent().getExtras().getInt("numArchivo");
                Intent intent2 = new Intent (Listita.this, EditarListita.class);
                intent2.putExtra("numArchivo", numArchivo);
                intent2.putExtra("numContext", 1);
                startActivity( intent2 );
                break;
            default:
                seleccion = "sin opcion %s";
                Toast.makeText(getBaseContext(), seleccion, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}