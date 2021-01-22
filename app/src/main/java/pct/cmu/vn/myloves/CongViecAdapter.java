package pct.cmu.vn.myloves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.ViewHolder> {
    Context context;
    List<CongViec> lstCongViec;
    private CongViecAdapterEvents events;

    public CongViecAdapter(List<CongViec> lstCongViec, Context context) {
        this.context = context;
        this.lstCongViec = lstCongViec;
    }

    public void setEventsListener(CongViecAdapterEvents e){
        events = e;
    }

    public interface CongViecAdapterEvents {
        void addList(String lst);
        void removeList(String lst);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewCongViec = layoutInflater.inflate(R.layout.item_congviec,parent,false);
        ViewHolder viewHolder = new ViewHolder(viewCongViec);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CongViec cv = lstCongViec.get(position);
        holder.txtCongViec.setText(cv.congViec);
        holder.txtDiemCong.setText(cv.diemCong);
        holder.txtDiemTru.setText(cv.diemTru);
        holder.chkCongViec.setText(cv.maCV);
        holder.txtMaCV.setText(cv.maCV);
    }

    @Override
    public int getItemCount() {
        return lstCongViec.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView txtMaCV;
        public TextView txtCongViec;
        public TextView txtDiemCong;
        public TextView txtDiemTru;
        public CheckBox chkCongViec;
        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            txtMaCV = itemView.findViewById(R.id.txtMaCV);
            txtCongViec = itemView.findViewById(R.id.txtCongViec);
            txtDiemCong = itemView.findViewById(R.id.txtDiemCong);
            txtDiemTru = itemView.findViewById(R.id.txtDiemTru);
            chkCongViec = itemView.findViewById(R.id.chkCongViec);
            chkCongViec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (chkCongViec.isChecked()){
                        events.addList(chkCongViec.getText().toString());
                    } else {
                        events.removeList(chkCongViec.getText().toString());
                    }
                }
            });
        }
    }
}
