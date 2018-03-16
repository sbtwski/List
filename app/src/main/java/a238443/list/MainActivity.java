package a238443.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    CustomAdapter ca_myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ca_myAdapter = new CustomAdapter(getApplicationContext());

        ca_myAdapter.addItem(new Person("Adam","Nowak",31));
        ca_myAdapter.addItem(new Person("Anna","Kowalska",15));
        ca_myAdapter.addItem(new Person("Krzysztof","Krawczyk",133));
        ca_myAdapter.addItem(new Person("Sylwia","Judek",27));

        ca_myAdapter.addItem(new Person("Adam","Nowak",31));
        ca_myAdapter.addItem(new Person("Anna","Kowalska",15));
        ca_myAdapter.addItem(new Person("Krzysztof","Krawczyk",133));
        ca_myAdapter.addItem(new Person("Sylwia","Judek",27));

        Toolbar tl_mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(tl_mainToolbar);

        ListView lv_mainView = findViewById(R.id.main_list_view);
        lv_mainView.setAdapter(ca_myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(this, AddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
