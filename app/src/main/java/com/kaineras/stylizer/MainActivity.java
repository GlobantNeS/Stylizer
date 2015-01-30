package com.kaineras.stylizer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CheckBox cbNormal=(CheckBox) findViewById(R.id.BoxNormal);
        final CheckBox cbBold=(CheckBox) findViewById(R.id.BoxBold);
        final CheckBox cbItalic=(CheckBox) findViewById(R.id.BoxItalic);
        final Button button = (Button) findViewById(R.id.button);
        final EditText textColor= (EditText) findViewById(R.id.editTextColor);
        final TextView textChange= (TextView) findViewById(R.id.textTitle);
        final RadioButton rbNormalFont= (RadioButton) findViewById(R.id.radioNormal);
        final RadioButton rbCustomFont= (RadioButton) findViewById(R.id.radioCustom);
        final Spinner spinner = (Spinner) findViewById(R.id.listFonts);
        final Typeface otf=textChange.getTypeface();

        cbNormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    if(cbNormal.isChecked()) {
                                                        cbBold.setChecked(false);
                                                        cbItalic.setChecked(false);
                                                    }
                                                    else
                                                        if(!cbBold.isChecked() && !cbItalic.isChecked())
                                                            cbNormal.setChecked(true);

                                                }
                                            }
        );

        cbItalic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    if(cbItalic.isChecked())
                                                        cbNormal.setChecked(false);
                                                    else
                                                        if(!cbBold.isChecked())
                                                            cbNormal.setChecked(true);

                                                }
                                            }
        );

        cbBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    if(cbBold.isChecked())
                                                        cbNormal.setChecked(false);
                                                    else
                                                        if(!cbItalic.isChecked())
                                                            cbNormal.setChecked(true);

                                                }
                                            }
        );

        rbNormalFont.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                        if(rbNormalFont.isChecked())
                                                            spinner.setVisibility(View.INVISIBLE);
                                                        else
                                                        if(rbCustomFont.isChecked())
                                                            spinner.setVisibility(View.VISIBLE);

                                                    }
                                                }
        );

        rbCustomFont.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                        if(rbCustomFont.isChecked())
                                                            spinner.setVisibility(View.VISIBLE);
                                                        else
                                                        if(rbNormalFont.isChecked())
                                                            spinner.setVisibility(View.INVISIBLE);

                                                    }
                                                }
        );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.listFonts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    String color=textColor.getText().toString();
                    int selectColor=Color.parseColor(color);
                    int pf=0;
                    if(cbBold.isChecked()&&cbItalic.isChecked())
                        pf=3;
                    else
                    if (cbBold.isChecked())
                        pf=1;
                    else
                    if(cbItalic.isChecked())
                        pf=2;
                    textChange.setTextColor(selectColor);
                    Typeface tf=null;
                    if(rbNormalFont.isChecked())
                        tf = Typeface.create(otf,pf);
                    else {
                        String typeFont=spinner.getSelectedItem().toString();
                        switch (typeFont)
                        {
                            case "Monospace":
                                tf = Typeface.create(Typeface.MONOSPACE, pf);
                            break;
                            case "Sans Serif":
                                tf = Typeface.create(Typeface.SANS_SERIF, pf);
                            break;
                            case "Serif":
                                tf = Typeface.create(Typeface.SERIF, pf);
                            break;
                        }
                    }
                    textChange.setTypeface(tf,pf);

                }catch(IllegalArgumentException e)
                {
                    Toast toast1 =Toast.makeText(getApplicationContext(),R.string.errorColor,Toast.LENGTH_LONG);
                    toast1.show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
