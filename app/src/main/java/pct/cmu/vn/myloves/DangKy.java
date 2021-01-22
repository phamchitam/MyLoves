package pct.cmu.vn.myloves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangKy extends AppCompatActivity {
    Button btnDangKy;
    User user;
    FirebaseAuth mAuth;
    TextInputEditText edtHoTen, edtNamSinh, edtEmail, edtPassword;
    DatabaseReference dbf = FirebaseDatabase.getInstance().getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtHoTen = (TextInputEditText) findViewById(R.id.edtHoTen);
        edtNamSinh = (TextInputEditText) findViewById(R.id.edtNamSinh);
        edtEmail = (TextInputEditText) findViewById(R.id.edtEmail);
        edtPassword = (TextInputEditText) findViewById(R.id.edtPassword);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString();
                String namSinh = edtNamSinh.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                user = new User(hoTen, namSinh, email, password);
                dangKy(user, dbf);
            }
        });
    }
    public void dangKy(User user,  DatabaseReference db){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            String userId = mAuth.getCurrentUser().getUid().toString();
                            Crud cr = new Crud(dbf);
                            cr.createObject(userId,user,false);

                        } else {
                            Toast.makeText(DangKy.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}