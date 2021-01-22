package pct.cmu.vn.myloves;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadDataSuccess  {

    private OnReadDataSuccessListener eventReadDataSuccess;
    List<CongViec> cvsWorks = new ArrayList<>();
    List<String> cvsJobs = new ArrayList<>();

    public ReadDataSuccess(){

    }

    public void setReadDataSuccess(OnReadDataSuccessListener onReadDataSuccess){
        this.eventReadDataSuccess = onReadDataSuccess;
    };

    public void readSnapshot(DataSnapshot dataSnapshot, String userId){
        cvsWorks.clear();
        cvsJobs.clear();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String strDate = formatter.format(date);

        for(DataSnapshot snapshot:dataSnapshot.child("works").getChildren()) {
            CongViec work = snapshot.getValue(CongViec.class);
            cvsWorks.add(work);
        }

        for(DataSnapshot snapshot:dataSnapshot.child("dojobs").child(userId).child(strDate).getChildren()){
            String job = snapshot.getValue(String.class);
            cvsJobs.add(job);
        }
        if(eventReadDataSuccess !=null){
            eventReadDataSuccess.onReadDataSuccess(cvsWorks,cvsJobs);
        } else
            System.out.println("No event");
    }



}
