package a238443.list;

import android.app.Activity;
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
    private static final int REQUEST_CODE = 0x9988;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ca_myAdapter = new CustomAdapter(getApplicationContext());

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
            startActivityForResult(new Intent(this, AddActivity.class),REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                Person p_input = (Person) data.getSerializableExtra("person");
                ca_myAdapter.v_addItem(p_input);
            }
        }
    }
}
