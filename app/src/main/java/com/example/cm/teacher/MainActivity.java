package com.example.cm.teacher;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView listView;
    ArrayList<data> arrayList;
    ArrayList<data> arrayList_search;
    Adapter arrayAdapter;

    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv_names);
        dataBase = new DataBase(this);
        arrayList = new ArrayList<>();
        arrayList_search = new ArrayList<>();
        ArrayList a = dataBase.read_name_and_id();

        for (int i = 0; i <a.size() ; i++) {
            String s = (String) a.get(i);
            String arr[]=s.split("/");
            arrayList.add(new data(Integer.valueOf(arr[0]),arr[1]));
        }

        arrayAdapter = new Adapter(this,R.layout.raws,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int n = (int) adapterView.getItemIdAtPosition(i);
               int num = arrayList.get(i).getId();
                String nam = String.valueOf(arrayList.get(i).getName());
                Toast.makeText(MainActivity.this,"تم الانتقال الى صفحة المعلومات عن الطالب ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Info.class);
                intent.putExtra("num",num);
                intent.putExtra("name",nam);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.men,menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.app_bar_search));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.add )
        {
            alert_add();


        }else if(id == R.id.delete )
        {
            alert_remove();


        }

        return super.onOptionsItemSelected(item);
    }

    public  void alert_add()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.addstudent);
        //builder.setTitle("اضافة طالب جديد");
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText name = (EditText) alertDialog.findViewById(R.id.name);
        Button button = (Button)  alertDialog.findViewById(R.id.ok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = name.getText().toString();
                Boolean b = dataBase.insert_student_name(s);
                alertDialog.dismiss();
                /*Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);*/
                recreate();
                if (b==true)
                {
                    Toast.makeText(MainActivity.this, "تم اضافة الطالب" , Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "حدث خطاء ما" , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public  void alert_remove()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.deletestudent);
        //builder.setTitle("اضافة طالب جديد");
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText number = (EditText) alertDialog.findViewById(R.id.number);
        Button button = (Button)  alertDialog.findViewById(R.id.remove);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = number.getText().toString();
                Boolean b = dataBase.delete_student(id);
                alertDialog.dismiss();
                /*Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);*/
                recreate();
                if (b==true)
                {
                    Toast.makeText(MainActivity.this, "تم حذف الطالب" , Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "حدث خطاء ما" , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

    public void search(String text)
    {
        arrayList_search.clear();
        String key;

        ArrayList a = dataBase.read_name_and_id();

        for (int i = 0; i <a.size() ; i++) {
            String s = (String) a.get(i);
            String arr[]=s.split("/");
            if(arr[1].contains(text))
            {
                arrayList_search.add(new data(Integer.valueOf(arr[0]),arr[1]));
            }
        }

        arrayAdapter = new Adapter(this,R.layout.raws,arrayList_search);
        listView.setAdapter(arrayAdapter);
    }
}
