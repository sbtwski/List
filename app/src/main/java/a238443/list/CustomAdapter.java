package a238443.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Person> al_database = new ArrayList<>();
    private LayoutInflater li_custom;

    CustomAdapter(Context ctxForAdapter) {
        li_custom = (LayoutInflater)ctxForAdapter.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void v_addItem(final Person pItem) {
        al_database.add(pItem);
        notifyDataSetChanged();
    }

    void v_addDatabase(ArrayList<Person> alBase) {
        al_database = alBase;
        notifyDataSetChanged();
    }

    ArrayList<Person> al_getDatabase() {
        return al_database;
    }

    private void v_removeItem(int iPosition) {
        al_database.remove(iPosition);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return al_database.size();
    }

    @Override
    public Person getItem(int position) {
        return al_database.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi_currentView = convertView;
        PersonHolder ph_holder = new PersonHolder();

        if(convertView == null) {
            vi_currentView = li_custom.inflate(R.layout.list_element,parent,false);

            ph_holder.tv_name = vi_currentView.findViewById(R.id.personal_text);
            ph_holder.tv_birth = vi_currentView.findViewById(R.id.age_text);
            ph_holder.iv_avatar = vi_currentView.findViewById(R.id.person_avatar);

            vi_currentView.setTag(ph_holder);
        }
        else
            ph_holder = (PersonHolder) vi_currentView.getTag();

        Person p_current = al_database.get(position);
        String s_tempData = p_current.s_getName() + " " + p_current.s_getSurname();

        ph_holder.tv_name.setText(s_tempData);

        s_tempData = "Wiek: " + p_current.i_getAge();
        ph_holder.tv_birth.setText(s_tempData);

        ph_holder.iv_avatar.setImageResource(R.drawable.ic_avatar);

        Button bt_delete = vi_currentView.findViewById(R.id.removal_button);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_removeItem(position);
            }
        });

        return vi_currentView;
    }

    private static class PersonHolder {
        TextView tv_name;
        TextView tv_birth;
        ImageView iv_avatar;
    }
}
