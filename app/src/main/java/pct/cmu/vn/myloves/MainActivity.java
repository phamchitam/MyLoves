package pct.cmu.vn.myloves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextInputEditText txtInputUsername, txtInputPassword;
    Button btnDangnhap, btnDangkyThongTin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInputUsername = (TextInputEditText) findViewById(R.id.txtInputUsername);
        txtInputPassword = (TextInputEditText) findViewById(R.id.txtInputPassword);
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
        btnDangkyThongTin = (Button) findViewById(R.id.btnDangkyThongTin);

        if (BuildConfig.DEBUG) {
            txtInputUsername.setText("khadi@gmail.com");
            txtInputPassword.setText("123456");
        }

        btnDangkyThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DangKy.class);
                startActivity(intent);
            }
        });

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtInputUsername.getText().toString();
                String password = txtInputPassword.getText().toString();
                dangNhap(userName,password);
            }
        });
    }

    public void dangNhap(String userName, String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                         //   Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                       //     Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }


                    }
                });
    }
    public void updateUI(FirebaseUser account){

        if(account != null){
            if (account.getEmail().equals("chitamcmu@gmail.com")){
                Intent intent = new Intent(this, Admin.class);
                intent.putExtra("userId",account.getUid());
                Toast.makeText(this,"You signed in as Admin",Toast.LENGTH_LONG).show();
                startActivity(intent);

            }else {
                Intent intent = new Intent(this, SelectJobs.class);
                intent.putExtra("userId",account.getUid());
//                intent.putExtra("email",account.getEmail());
                Toast.makeText(this, "You signed in successfully " + account.getUid(), Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }else {
            Toast.makeText(this,"You didn't signed in",Toast.LENGTH_LONG).show();
        }

    }

}