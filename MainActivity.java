package edu.monash.fit3027ffit4039.week03prac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import edu.monash.fit3027ffit4039.week03prac.models.Person;

public class MainActivity extends AppCompatActivity {

    // unique identifier for receiving activity result
    public static final int ADD_PERSON_REQUEST = 1;

    private ListView mListView;
    private PersonAdapter mAdapter;
    private ArrayList<Person> mPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize the Person List
        mPersonList = new ArrayList<>();
        initializeDefaultList();
        mListView = (ListView) findViewById(R.id.peopleListView);

        // create Adapter and associate it with our PersonList
        mAdapter = new PersonAdapter(this, mPersonList);
        mListView.setAdapter(mAdapter);
        updateListCount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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

    // overridden method to get results from any activity launched from this activity previously
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PERSON_REQUEST) {
            if (resultCode == RESULT_OK) {
                // get the person object from the intent and add it to our list
                Person newPerson = data.getParcelableExtra("result");
                mPersonList.add(newPerson);
                mAdapter.notifyDataSetChanged();
                updateListCount();
            }
        }
    }

    // function to initialize default valudes for the person list
    private void initializeDefaultList() {
        mPersonList.add(new Person("Neptune", "Australia"));
        mPersonList.add(new Person("NepGear", "Asia"));
        mPersonList.add(new Person("Noire", "North America"));
        mPersonList.add(new Person("Uni", "South America"));
        mPersonList.add(new Person("Vert", "Europe"));
        mPersonList.add(new Person("Blanc", "Australia"));
        mPersonList.add(new Person("Rom", "Africa"));
        mPersonList.add(new Person("Ram", "Asia"));
    }

    private void updateListCount() {
        // get total size of person list & set title
        int numPeople = mPersonList.size();
        setTitle("All People: " + numPeople);
    }
}
