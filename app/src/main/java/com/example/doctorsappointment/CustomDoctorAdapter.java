package com.example.doctorsappointment;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class CustomDoctorAdapter extends ArrayAdapter<Doctor> {
    private final Context context;
    private final ArrayList<Doctor> records;

    public CustomDoctorAdapter(@NonNull Context context, @NonNull ArrayList<Doctor> records) {
        super(context, -1, records);
        this.context = context;
        this.records = records;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_doctor, parent, false);

        TextView tvSN = rowView.findViewById(R.id.tvSN);
        TextView tvID = rowView.findViewById(R.id.tvID);
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvSpeciality = rowView.findViewById(R.id.tvSpeciality);
        TextView tvContact = rowView.findViewById(R.id.tvContact);
        TextView tvTime = rowView.findViewById(R.id.tvTime);

        tvSN.setText(String.valueOf(position+1));
        tvID.setText(records.get(position).id);
        tvName.setText(records.get(position).name);
        tvSpeciality.setText(records.get(position).speciality);
        tvContact.setText(records.get(position).contact);
        tvTime.setText(records.get(position).shr);

        //System.out.println(records.get(position).status);


        return rowView;
    }
}
