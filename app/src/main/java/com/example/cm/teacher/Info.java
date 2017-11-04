package com.example.cm.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Info extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.Presence:
                    hdoor_and_ghtap();

                case R.id.pay:
                    pay();
                    return true;
                case R.id.mark:
                    marks();
                    return true;
            }
            return false;
        }

    };

    int keypay;
    int key2;
    int key;
    int x;
    String asm;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        x=intent.getIntExtra("num",0);
        asm = intent.getStringExtra("name");
        dataBase = new DataBase(this);


    }

    public void marks()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Info.this);
        builder.setView(R.layout.marks);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ImageView imageView = (ImageView) alertDialog.findViewById(R.id.img_mark);
        Animation animation = AnimationUtils.loadAnimation(Info.this,R.anim.ani);
        imageView.startAnimation(animation);
        final EditText gread1 = (EditText) alertDialog.findViewById(R.id.grade1);
        final EditText gread2 = (EditText) alertDialog.findViewById(R.id.grade2);
        Button finish = (Button) alertDialog.findViewById(R.id.mark_finish);
        Button login = (Button) alertDialog.findViewById(R.id.login_mark);
        TextView listView = (TextView) alertDialog.findViewById(R.id.lv_marks);
        TextView num = (TextView) alertDialog.findViewById(R.id.num3);
        TextView name = (TextView) alertDialog.findViewById(R.id.name3);
        num.setText(String.valueOf(x));
        name.setText(asm.toString());

        /****************************************************************************************/

        ArrayList a = dataBase.read_num_of_marks();
        String ss="";
        for (int i = 0; i <a.size() ; i++) {

            String s =  a.get(i).toString();
            String arr[]=s.split("]");
            int dd = Integer.valueOf(arr[0]);
           if(dd == x)
           {
               ss += arr[1]+ "\n";
           }

        }
        listView.setText( ss );
        /****************************************************************************************/
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( gread1.length() ==0 || gread2.length() == 0)
                {
                    Toast.makeText(Info.this, " من فضلك ادخل الدرجات", Toast.LENGTH_SHORT).show();
                }else
                {
                    String s = gread1.getText().toString() + " / " + gread2.getText().toString();
                   boolean b = dataBase.write_marks(x,s);

                    if(b==true)
                    {
                        Toast.makeText(Info.this, " تم اضافة الدرجات", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(Info.this, " حذت خطاء ما", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }

    public void pay()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Info.this);
        builder.setView(R.layout.pay);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ImageView imageView = (ImageView) alertDialog.findViewById(R.id.img_pay);
        Animation animation = AnimationUtils.loadAnimation(Info.this,R.anim.ani);
        imageView.startAnimation(animation);
        Button finish = (Button) alertDialog.findViewById(R.id.pay_finish);
        Button login = (Button) alertDialog.findViewById(R.id.login_pay);
        TextView num = (TextView) alertDialog.findViewById(R.id.num2);
        TextView name = (TextView) alertDialog.findViewById(R.id.name2);
        num.setText(String.valueOf(x));
        name.setText(asm.toString());
        final CheckBox checkBox_pay = (CheckBox) alertDialog.findViewById(R.id.chechbox_pay);
        TextView num_pay = (TextView) alertDialog.findViewById(R.id.num_pay);
        /****************************************************************************************/
        ArrayList a = dataBase.read_num_of_pay();

        for (int i = 0; i <a.size() ; i++) {
            String s = (String) a.get(i);
            String arr[]=s.split("/");
            int dd = Integer.valueOf(arr[0]);
            if(dd == x)
            {
                if(arr[1].equals("null")|| arr[1]=="null")
                {
                    arr[1] = "0";
                    keypay = Integer.valueOf(arr[1]);
                    num_pay.setText(arr[1]);
                }else
                {
                    keypay = Integer.valueOf(arr[1]);
                    num_pay.setText(arr[1]);
                }
            }
        }
        /****************************************************************************************/
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkBox_pay.isChecked())
                {
                    //write fun to increase the num of Presence
                    Boolean b = dataBase.write_pay(String.valueOf(x),keypay);
                    if(b==true)
                    {
                        Toast.makeText(Info.this, "تم الدفع", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });


    }

    public void hdoor_and_ghtap()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Info.this);
        builder.setView(R.layout.hdoorrrr);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ImageView imageView = (ImageView) alertDialog.findViewById(R.id.img_hdoor);
        Animation animation = AnimationUtils.loadAnimation(Info.this,R.anim.ani);
        Button finish = (Button) alertDialog.findViewById(R.id.login_finish);
        Button login = (Button) alertDialog.findViewById(R.id.login_hdoor);
        TextView num = (TextView) alertDialog.findViewById(R.id.num);
        TextView name = (TextView) alertDialog.findViewById(R.id.name);
        num.setText(String.valueOf(x));
        name.setText(asm.toString());

        final CheckBox checkBox_hdoor = (CheckBox) alertDialog.findViewById(R.id.chechbox_hdor);
        final CheckBox checkBox_ghyap = (CheckBox) alertDialog.findViewById(R.id.chechbox_ghyab);

        TextView num_hdoor = (TextView) alertDialog.findViewById(R.id.num_hdoor);
        TextView num_ghyap = (TextView) alertDialog.findViewById(R.id.num_ghyab);

/****************************************************************************************/
        ArrayList a = dataBase.read_num_of_Presence();

        for (int i = 0; i <a.size() ; i++) {
            String s = (String) a.get(i);
            String arr[]=s.split("/");
            int dd = Integer.valueOf(arr[0]);
            if(dd == x)
            {
                if(arr[1].equals("null")|| arr[1]=="null")
                {
                    arr[1] = "0";
                    key = Integer.valueOf(arr[1]);
                    num_hdoor.setText(arr[1]);
                }else
                {
                    key = Integer.valueOf(arr[1]);
                    num_hdoor.setText(arr[1]);
                }
            }
        }
/****************************************************************************************/

        ArrayList a2 = dataBase.read_num_of_Absence();

        for (int i = 0; i <a2.size() ; i++) {
            String s = (String) a2.get(i);
            String arr[]=s.split("/");
            int dd = Integer.valueOf(arr[0]);
            if(dd == x)
            {
                if(arr[1].equals("null")|| arr[1]=="null")
                {
                    arr[1] = "0";
                    key2 = Integer.valueOf(arr[1]);
                    num_ghyap.setText(arr[1]);
                }else
                {
                    key2 = Integer.valueOf(arr[1]);
                    num_ghyap.setText(arr[1]);
                }
            }
        }
/****************************************************************************************/
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkBox_hdoor.isChecked())
                {
                    //write fun to increase the num of Presence
                    Boolean b = dataBase.write_Presence(String.valueOf(x),key);
                    if(b==true)
                    {
                        Toast.makeText(Info.this, "تم تسجيل الحضور", Toast.LENGTH_SHORT).show();
                    }
                }

                if(checkBox_ghyap.isChecked())
                {
                    Boolean b = dataBase.write_Absence(String.valueOf(x),key2);
                    if(b==true)
                    {
                        Toast.makeText(Info.this, "تم تسجيل الغياب", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        imageView.startAnimation(animation);
    }

}
