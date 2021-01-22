package pct.cmu.vn.myloves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends AppCompatActivity {
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    Button btnCongViec;
    CongViec cv;
    TextInputEditText edtCongViec, edtAdd, edtMinus, edtRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        edtCongViec = (TextInputEditText) findViewById(R.id.edtCongViec);
        edtAdd = (TextInputEditText) findViewById(R.id.edtAdd);
        edtMinus = (TextInputEditText) findViewById(R.id.edtMinus);
        edtRate = (TextInputEditText) findViewById(R.id.edtRate);
        btnCongViec = (Button) findViewById(R.id.btnCongViec);
        btnCongViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String congViec = edtCongViec.getText().toString();
                String add = edtAdd.getText().toString();
                String minus = edtMinus.getText().toString();
                String rate = edtRate.getText().toString();
                cv = new CongViec(congViec, add, minus, rate);
                String key = db.child("works").push().getKey();
                cv.maCV = key;
                db.child("works").child(key).setValue(cv);

            }
        });

    }
}