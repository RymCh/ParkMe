package eniso.ia2.parkingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;
import java.util.Iterator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference dref;
    ListView listview;
    Button p1, p2, p3, p4, p5, p6, br;
    // Button b;
    TextView t;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.t);
        p1 = (Button) findViewById(R.id.p1);
        p2 = (Button) findViewById(R.id.p2);
        br = (Button) findViewById(R.id.br);
        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            /* accountsDb = mDatabase.child("enisoparking");
            accountsDb.child("some key").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
            try{
            String account = snapshot.getChildren().iterator().next()
            .getValue(String.class);

            } catch (Throwable e) {
            MyLogger.error(this, "onCreate eror", e);
            }
            }
            @Override public void onCancelled(DatabaseError error) { }
            });
            */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List myList = new ArrayList<>(); int s=0; int g=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey().toString();
                    String value = snapshot.getValue().toString();

                    if (key.equals("Parking1")) {
                        key = "P1";
                        p1.setText(key);

                        if ( value.toLowerCase().contains("false")) {
                            value = "false";
                            p1.setBackgroundColor(getResources().getColor(R.color.green));
                             s=0;
                        }
                        if ( value.toLowerCase().contains("true")) {
                            value = "true";
                            p1.setBackgroundColor(getResources().getColor(R.color.red));
                            s=1;
                        }


                    }
                    if (key.equals("Parking2")) {
                        key = "P2";
                        p2.setText(key);


                        if (value.contains("False")) {
                            value = "False";
                            g=0;
                            p2.setBackgroundColor(getResources().getColor(R.color.green));
                        }
                        if (value.contains("True")) {
                            value = "True";
                            g=1;
                            p2.setBackgroundColor(getResources().getColor(R.color.red));
                        }


                    }

                    String string = key + " : " + value + "\n";
                    myList.add(string);
//t.setText(string);
                }
                int k = g+s;
                String ch = "";
                for (int i = 0; i < myList.size(); i++) {
                    ch = ch + "" + myList.get(i) + "somme="+ (10-k)+"";
                    String sh="There are "+(10-k)+" available places! ";
                    t.setText(sh);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(getApplicationContext(),Barr.class);
                startActivity(open);
            }
        });
    }

}
