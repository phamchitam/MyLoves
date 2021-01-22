package pct.cmu.vn.myloves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class SelectJobs extends AppCompatActivity implements CongViecAdapter.CongViecAdapterEvents {
    List<CongViec> lstCongViec = new ArrayList<>();
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    RecyclerView recyclerView;
    CongViecAdapter adapter;
    Button btnXacNhanCongViec;
    CheckBox chkCongViec;
    List<String> lstCV = new ArrayList<>();
    String userId;
    String email;

    @Override
    public void addList(String lst) {
        lstCV.add(lst);
//        Toast.makeText(this, " " + lstCV.size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, " " + lst, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeList(String lst) {
        lstCV.remove(lst);
//        Toast.makeText(this, " " + lstCV.size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, " " + lst, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerCongViec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);



        db.child("works").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    CongViec cv = dataSnapshot.getValue(CongViec.class);
                    lstCongViec.add(cv);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        adapter = new CongViecAdapter(lstCongViec, SelectJobs.this);
        adapter.setEventsListener(this);
        recyclerView.setAdapter(adapter);

        userId = getIntent().getStringExtra("userId");
        email = getIntent().getStringExtra("email");
        btnXacNhanCongViec = (Button) findViewById(R.id.btnXacNhanCongViec);
        btnXacNhanCongViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
                String strDate = formatter.format(date);
                Toast.makeText(v.getContext(),  " userId: " + userId, Toast.LENGTH_LONG).show();
                for(int i = 0; i < lstCV.size(); i++) {
                    db.child("dojobs").child(userId).child(strDate).child(lstCV.get(i)).setValue("No");
                }
                Intent intent = new Intent(v.getContext(), Dojob.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }
}