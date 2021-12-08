package com.example.caugpa;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewSubjectsAdapter extends RecyclerView.Adapter<NewSubjectsAdapter.ViewHolder> {
    String[] gradeItems={"선택","A+","A","B+","B","C+","C","D+","D","F","P"};

    private ArrayList<AllSubjects> allSubjectsList;

    public interface onItemClickListener{
        void onItemClick(View v,int pos, String value);
    }
    private NewSubjectsAdapter.onItemClickListener mListener = null;
    public void setOnItemClickListener(NewSubjectsAdapter.onItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public NewSubjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_page_all_subjects_item, parent, false);
        return new NewSubjectsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewSubjectsAdapter.ViewHolder holder, int position) {
        holder.onBind(allSubjectsList.get(position));
    }

    @Override
    public int getItemCount() {
        return allSubjectsList.size();
    }

    public void setMySubjectsList(ArrayList<AllSubjects> list){
        this.allSubjectsList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectYear;
        TextView subjectName;
        TextView subjectCategory;
        EditText subjectGpa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectYear = (TextView) itemView.findViewById(R.id.class_year);
            subjectName = (TextView) itemView.findViewById(R.id.class_subject);
            subjectCategory= (TextView) itemView.findViewById(R.id.class_major);
            subjectGpa = (EditText) itemView.findViewById(R.id.class_gpa);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if(pos!= RecyclerView.NO_POSITION){
                    if(mListener !=null){
                        v.setBackgroundColor(Color.GRAY);
                        String value = subjectGpa.getText().toString();
                        Log.d("VAL",value);
                        subjectGpa.setText(value);
                        allSubjectsList.get(pos).setScore(value);
                        mListener.onItemClick(v,pos,value);
                    }
                }
            });
        }

        void onBind(AllSubjects item){
            String subCategory;
            String major = item.getMajor();
            if(major.equals("Y")){
                if(item.getMajorSpecific().equals("ADV")){
                    subCategory = "전공심화";
                }else if(item.getMajorSpecific().equals("BASIC")){
                    subCategory = "전공기초";
                }else{
                    subCategory = "전공필수";
                }
            }else{
                subCategory = "교양";
            }
            subjectYear.setText(""+item.getYear()+"학년");
            subjectName.setText(item.getSubject());
            subjectCategory.setText(subCategory);
        }
    }

}
