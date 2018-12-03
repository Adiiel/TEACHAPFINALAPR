package com.example.adielpreciado.teachapfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    private EditText edtusu, edtclv;
    private Button btningr;
    private TextView register;

    RequestQueue rq;
    JsonRequest jrq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion, container, false);
        edtusu = (EditText) vista.findViewById(R.id.Usuario);
        edtclv = (EditText) vista.findViewById(R.id.Clave);
        btningr = (Button) vista.findViewById(R.id.btningr);
        register = (TextView) vista.findViewById(R.id.txtRH);
        rq = Volley.newRequestQueue(getContext());
        btningr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        return vista;
    }

    public void onClick(View v) {
        Intent miIntent = null;
        switch (v.getId()) {
            case R.id.txtRH:
                miIntent = new Intent(SesionFragment.this.getActivity(), pruebaActivity.class);
                break;
            case R.id.btningr:
                miIntent = new Intent(SesionFragment.this.getActivity(),MainActivity2.class);
        }
        if (miIntent != null) {
            startActivity(miIntent);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "no se ha encontrado exitosamente" + error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), " se ha encontrado exitosamente" + edtusu.getText(), Toast.LENGTH_LONG).show();
        User usuario = new User();
        JSONArray jsonArray = response.optJSONArray("usuario");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));
            usuario.setPassword(jsonObject.optString("password"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void iniciarSesion() {
        String url = "http://10.0.3.2:8080/Teach/consulta.php?user=" + edtusu.getText().toString() + "&password" + edtclv.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }
}


