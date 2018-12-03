package com.example.adielpreciado.teachapfinal;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

import org.json.JSONObject;

public class pruebaActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private EditText doc, nom, prof,user,pass;
    private TextView txtnew;
    private Button btnagregar;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_main);
        doc = (EditText) findViewById(R.id.edtDoc);
        nom = (EditText) findViewById(R.id.edtnombre);
        prof = (EditText) findViewById(R.id.edtprof);
        user =(EditText)findViewById(R.id.Usernew);
        pass=(EditText)findViewById(R.id.Clavenew);
        btnagregar = (Button) findViewById(R.id.btnRegister);

        request = Volley.newRequestQueue(getApplicationContext());
        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });
    }
    public void onClick(View v) {
        Intent miIntent = null;
        switch (v.getId()) {
            case R.id.btnRegister:
                miIntent = new Intent(pruebaActivity.this, SesionFragment.class);
                break;
        }
        if (miIntent != null) {
            startActivity(miIntent);
        }
    }

    private void cargarWebService() {
        String url = "http://10.0.3.2:8080/Teach/registro.php?documento=" + doc.getText().toString() + "&nombre=" + nom.getText().toString() + "" +
                "&profecion=" + prof.getText().toString()+ "&user=" + user.getText().toString() + "&password=" + pass.getText().toString();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "no se ha registrado exitosamente" + error.toString(), Toast.LENGTH_LONG).show();
        progress.hide();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "se ha registrado exitosamente", Toast.LENGTH_LONG).show();
        progress.hide();
        doc.setText("");
        nom.setText("");
        prof.setText("");
        user.setText("");
        pass.setText("");


    }
   /* public void onClick(View v) {
        Intent Intent = null;
        switch (v.getId()) {
            case R.id.txtnew:
                Intent = new Intent(RegisterActivity.this, MainActivity.class);
                break;
        }
        if (Intent != null) {
            startActivity(Intent);
        }
    }*/

}
