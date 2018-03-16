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
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    EditText et_name, et_surname, et_date;
    Person p_fromInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_name = findViewById(R.id.name_input);
        et_surname = findViewById(R.id.surname_input);
        et_date = findViewById(R.id.date_input);

        Toolbar tl_resultToolbar = findViewById(R.id.add_toolbar);
        tl_resultToolbar.setTitle("");
        setSupportActionBar(tl_resultToolbar);
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
            if(b_validInput()) {
                final Intent i_result = new Intent();
                i_result.putExtra("person",p_fromInput);
                setResult(Activity.RESULT_OK,i_result);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean b_validInput() {
        String s_name = et_name.getText().toString();
        String s_surname = et_surname.getText().toString();
        String s_date = et_date.getText().toString();
        boolean b_valid = true;

        if(s_name.isEmpty()) {
            et_name.setError(getString(R.string.input_empty));
            b_valid = false;
        }

        if(s_surname.isEmpty()) {
            et_surname.setError(getString(R.string.input_empty));
            b_valid = false;
        }

        if(s_date.isEmpty()) {
            et_date.setError(getString(R.string.input_empty));
            b_valid = false;
        }
        else {
            int i_age = i_dateOperations(s_date);
            if(i_age>=0 && i_age <= 120) {
                if(b_valid) {
                    p_fromInput = new Person(s_name,s_surname,i_age);
                }
            }
            else {
                et_date.setError(getString(R.string.invalid_date));
                et_date.setText("");
                b_valid = false;
            }
        }

        return b_valid;
    }

    private int i_dateOperations(String sDate) {
        int i_age = -1;
        SimpleDateFormat sdf_formatter = new SimpleDateFormat(getString(R.string.date_format), Locale.ENGLISH);
        Date dt_temp = new Date();
        boolean b_correctDate = true;
        sdf_formatter.setLenient(false);

        try {
            dt_temp = sdf_formatter.parse(sDate);
        } catch(ParseException e) {
            b_correctDate = false;
        }

        if(b_correctDate) {
            Calendar cal_forInput = Calendar.getInstance();
            Calendar cal_forCurrent = Calendar.getInstance();
            cal_forInput.setTime(dt_temp);
            cal_forCurrent.getTime();

            int i_yearDifference = cal_forCurrent.get(Calendar.YEAR) - cal_forInput.get(Calendar.YEAR);
            int i_monthDifference = cal_forCurrent.get(Calendar.MONTH) - cal_forInput.get(Calendar.MONTH);
            int i_dayDifference = cal_forCurrent.get(Calendar.DAY_OF_MONTH) - cal_forInput.get(Calendar.DAY_OF_MONTH);
            i_age = i_yearDifference;

            if (i_monthDifference < 0)
                i_age--;
            else {
                if (i_monthDifference == 0) {
                    if (i_dayDifference < 0)
                        i_age--;
                }
            }
        }

        return i_age;
    }
}
