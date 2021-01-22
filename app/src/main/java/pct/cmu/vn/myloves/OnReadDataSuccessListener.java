package pct.cmu.vn.myloves;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface OnReadDataSuccessListener<T,V> {
    public void onReadDataSuccess(List<T> lstWorks, List<V> lstJobs);
}
