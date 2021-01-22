package pct.cmu.vn.myloves;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dojob extends AppCompatActivity   {
    List<CongViec> lstWorks = new ArrayList<>();
    List<String> lstJobs = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ReadDataSuccess readWorks = new ReadDataSuccess();
        ReadDataSuccess readJobs = new ReadDataSuccess();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dojob);
        int diemTotal = 0;
        String userId = getIntent().getStringExtra("userId");

        DatabaseReference dbworks = FirebaseDatabase.getInstance().getReference();
        dbworks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                readWorks.setReadDataSuccess(new OnReadDataSuccessListener<CongViec,String>() {
                    @Override
                    public void onReadDataSuccess(List<CongViec> lstWorks, List<String> lstJobs) {
                        Toast.makeText(Dojob.this, "Size of list Cong Viec:  " + lstWorks.size() + " . Size of list Jobs: " + lstJobs.size(), Toast.LENGTH_SHORT).show();
                        lstWorks = lstWorks;
                        lstJobs = lstJobs;
                        System.out.println("Size Works: " + lstWorks.size() + ". Size of Jobs: " + lstJobs.size());


                    }
                });
                readWorks.readSnapshot(snapshot,userId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    };
}