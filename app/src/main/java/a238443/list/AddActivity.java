package a238443.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    String sName;
    String sSurname;
    String sDate;
    int iAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar tl_resultToolbar = findViewById(R.id.add_toolbar);
        tl_resultToolbar.setTitle("");
        setSupportActionBar(tl_resultToolbar);
        v_readInput();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_accept) {
            if(b_validateInput()) {
                final Intent i_result = new Intent();
                i_result.putExtra("person",new Person(sName,sSurname,iAge));
                setResult(Activity.RESULT_OK,i_result);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void v_readInput() {
        EditText et_name = findViewById(R.id.name_input);
        EditText et_surname = findViewById(R.id.surname_input);
        EditText et_date = findViewById(R.id.date_input);

        sName = et_name.getText().toString();
        sSurname = et_surname.getText().toString();
        sDate = et_date.getText().toString();
    }

    private boolean b_validateInput() {
        v_readInput();

        if(sDate!=null && !sDate.isEmpty()) {
            int i_age = i_dateOperations();
            if(i_age>0 && i_age < 120) {
                iAge = i_age;
                return true;
            }
        }
        return false;
    }

    private int i_dateOperations() {
        int i_age = -1;
        try {
            SimpleDateFormat sdf_formatter = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);
            java.util.Date dt_temp = sdf_formatter.parse(sDate);

            Calendar cal_forInput = Calendar.getInstance();
            Calendar cal_forCurrent = Calendar.getInstance();
            cal_forInput.setTime(dt_temp);
            cal_forCurrent.getTime();

            int i_yearDifference = cal_forCurrent.get(Calendar.YEAR) - cal_forInput.get(Calendar.YEAR);
            int i_monthDifference = cal_forCurrent.get(Calendar.MONTH) - cal_forInput.get(Calendar.MONTH);
            int i_dayDifference = cal_forCurrent.get(Calendar.DAY_OF_MONTH) - cal_forInput.get(Calendar.DAY_OF_MONTH);
            i_age = i_yearDifference;

            if(i_monthDifference<0)
                i_age--;
            else {
                if(i_monthDifference == 0) {
                    if(i_dayDifference < 0)
                        i_age --;
                }
            }

        } catch(ParseException e) {
            e.printStackTrace();
        }

        return i_age;
    }
}
