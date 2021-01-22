package pct.cmu.vn.myloves;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;
import java.util.Map;

public class Crud {
    DatabaseReference db;

    public Crud(DatabaseReference db) {
        this.db = db;
    }

    public DatabaseReference getDb() {
        return db;
    }

    public void setDb(DatabaseReference db) {
        this.db = db;
    }

    public void createSingleValue(String child, String value, boolean push){
        if (push == false) {
            db.child(child).setValue(value);
        } else{
            db.child(child).push().setValue(value);
        }
    }

    public void createObject(String child, Object object, boolean push){
        if (push == false) {
            db.child(child).setValue(object);
        } else{
            db.child(child).push().setValue(object);
        }
    }

    public void createMap(String child, Map map, boolean push){
        if (push = false) {
            db.child(child).setValue(map);
        } else{
            db.child(child).push().setValue(map);
        }
    }

    public void deleteNode(String node){
        db.child(node).removeValue();
    }

    public void deleteItem(String node, String atrbName, String id){
        Query itemQuery = db.child(node).orderByChild(atrbName).equalTo(id);
        itemQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot itemSnapshot : snapshot.getChildren()){
                    itemSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}
