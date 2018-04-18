package edu.monash.fit3027ffit4039.week03prac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.monash.fit3027ffit4039.week03prac.models.Person;

public class AddPersonActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNameInputText;
    private Spinner mContinentSpinner;
    private Button mSubmitButton;
    private ArrayList<String> mContinents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        mNameInputText = (EditText) findViewById(R.id.nameEditText);
        mContinentSpinner = (Spinner) findViewById(R.id.continentSpinner);
        mSubmitButton = (Button) findViewById(R.id.addButton);
        mSubmitButton.setOnClickListener(this);
        InitializeContinentArray();

        // set up ArrayAdapter for Spinner options
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mContinents);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mContinentSpinner.setAdapter(adapter);
    }

        private void InitializeContinentArray() {
            mContinents = new ArrayList<>();
            mContinents.add("Australia");
            mContinents.add("Africa");
            mContinents.add("Asia");
            mContinents.add("Europe");
            mContinents.add("South America");
            mContinents.add("North America");
        }

        public void onClick(View view) {
            String name = mNameInputText.getText().toString();

            if (name.length() > 0) {
                String continent = (String) mContinentSpinner.getSelectedItem();
                Person newPerson = new Person(name, continent);

                // create new intent, pass info & finish
                Intent intent = new Intent();
                intent.putExtra("result", newPerson);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

