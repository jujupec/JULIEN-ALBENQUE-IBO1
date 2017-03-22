package fr.esilv.s8.exam.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.esilv.s8.exam.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private EditText mrecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mButton = (Button) findViewById(R.id.iButton);
        mrecherche = (EditText) findViewById(R.id.editText);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String Requete = mrecherche.getText().toString();


        VideoActivity.start(this, Requete);


    }
}
