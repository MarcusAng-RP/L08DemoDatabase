package sg.edu.rp.c346.id22002222.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;

    EditText etTask, etDate;
    ListView lv;

    ArrayAdapter<String> aa;
    ArrayList<Task> al;

    boolean asc = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);
        lv = findViewById(R.id.lv);




        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
//                db.insertTask("Submit RJ", "25 Apr 2021");
                String Task = etTask.getText().toString();
                String Date = etDate.getText().toString();
                db.insertTask(Task,Date);
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();

//                ArrayList<Task> al = db.getTasks();

                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

//              DBHelper db2 = new DBHelper(MainActivity.this);
                al = db.getTasks(asc);
//              db2.close();
                asc = !asc;
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);

//                ArrayAdapter adapter2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,al);
//                lv.setAdapter(adapter2);
            }
        });


    }
}