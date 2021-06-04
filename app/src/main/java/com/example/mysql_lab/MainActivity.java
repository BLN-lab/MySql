package com.example.mysql_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String Root="http://192.168.1.2/Android_Lab12/";
    String add_url=Root+"Add.php";
    String edit_url=Root+"Edit.php";
    Button check,edit,delete,view,add;
    EditText id,name,age;
    TextView id_t,name_t,age_t;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check=(Button) findViewById(R.id.Con);
        add=(Button) findViewById(R.id.add);
        delete=(Button) findViewById(R.id.delete);
        edit=(Button) findViewById(R.id.edit);
        view=(Button) findViewById(R.id.view);

        id=(EditText) findViewById(R.id.id_E);
        name=(EditText) findViewById(R.id.name_E);
        age=(EditText) findViewById(R.id.age_E);

        id_t=(TextView) findViewById(R.id.id_T);
        name_t=(TextView) findViewById(R.id.name_T);
        age_t=(TextView) findViewById(R.id.age_T);

        progressDialog=new ProgressDialog(this);

        check.setOnClickListener(this);

        add.setOnClickListener(this);

        edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==check) {
            Conn();
        }
        if (v==add) {
            Add();
        }
        if(v==edit){
            Edit();
        }
    }

    protected void Edit() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, edit_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.hide();
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "Error:" + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id_t.getText().toString());
                params.put("name", name.getText().toString());
                params.put("age", age.getText().toString());
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }

    protected void Conn(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Root+"Connection.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error:" + error, Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }

    protected void Add() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, add_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.hide();
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "Error:" + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name.getText().toString());
                params.put("age", age.getText().toString());
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }
}