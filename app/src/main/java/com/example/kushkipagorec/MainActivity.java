package com.example.kushkipagorec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kushkipagorec.WebServices.Asynchtask;
import com.example.kushkipagorec.WebServices.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity
        extends AppCompatActivity
        implements Asynchtask {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickLogin(View view){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        EditText txtUser = findViewById(R.id.txtUser);
        EditText txtPass = findViewById(R.id.txtPass);
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr="
                        + txtUser.getText().toString() + "&pass=" + txtPass.getText().toString(),
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        Intent intent = new Intent(this, MainActivity2.class);
        TextView txtRespuesta = findViewById(R.id.txtRespuesta);
        if (result.equals("Login Correcto!")) {
            startActivity(intent);
        }
        else{
            txtRespuesta.setText(result);
        }
    }
}