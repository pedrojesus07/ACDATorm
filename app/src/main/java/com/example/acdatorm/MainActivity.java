package com.example.acdatorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.acdatorm.model.databaseExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private databaseExample db;
    private ProgressBar pbProgreso;
    private Hebra hebra;
    private Button btTexto;
    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initDatabase();
        initComponents();
        initEvents();
    }

    private void initEvents() {
        btTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saludar();
                if(!started){
                    hebra.execute();
                    started = true;
                }
                if(hebra.getStatus() == AsyncTask.Status.FINISHED){
                    hebra = new Hebra(new Random().nextInt(49));
                    hebra.execute();
                }
            }
        });
    }

    private void initComponents() {
        pbProgreso = findViewById(R.id.pbProgreso);
        btTexto = findViewById(R.id.btTexto);

        hebra = new Hebra(10);
    }

    private void initDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), databaseExample.class,"basedatosusuarios").build();
        Usuario usuario = new Usuario();
        usuario.setApellidos("Perez Lopez");
        usuario.setNombre("Baldomero");
        //db.usuarioDao().insert(usuario);
        new HebraInsertDb().execute(usuario);
    }

    private void saludar(){
        List<String> lista = new ArrayList<>();
    }

    class HebraInsertDb extends AsyncTask<Usuario, Void, Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer aInteger) {
            super.onPostExecute(aInteger);
            Log.v("xyz","resultado: "+ aInteger);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Integer aInteger) {
            super.onCancelled(aInteger);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Integer doInBackground(Usuario... usuarios) {
            int resultado = 0;
            if(usuarios != null && usuarios.length > 0 && usuarios[0] != null) {
                db.usuarioDao().insert(usuarios[0]);
                resultado = usuarios[0].getId();
            }
            return resultado;
        }
    }

    class Hebra extends AsyncTask<String, Integer, Boolean>{

        private int valorInicial;

        Hebra(int vi){
            //codigo que se ejecuta al crear la instacia
            valorInicial = vi;
        }

        Hebra(){
            this(0);
        }

        @Override
        protected void onPreExecute() {
            //codigo que se va a ejecutar justo antes de lanzar la hebra
            super.onPreExecute();
            pbProgreso.setProgress(valorInicial);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            for (int i = 0; i < 1000; i++) {
                Log.v("xyzyx",Thread.currentThread().getName() + " " + i);
                if(i%10 == 0){
                    publishProgress(i);
                }
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[values.length-1];
            value = value / 10;
            if(value > valorInicial) {
                pbProgreso.setProgress(value);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                pbProgreso.setProgress(100);
            }else{
                pbProgreso.setProgress(0);
            }
        }


    }
}
