package com.example.caugpa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddSubjectsMySubjectAdapter extends RecyclerView.Adapter<AddSubjectsMySubjectAdapter.ViewHolder> {
    private ArrayList<MySubjects> mySubjectsList;
    public interface onItemClickListener{
        void onItemClick(View v, int pos);
    }
    private onItemClickListener mListener = null;
    public void setOnItemClickListener(onItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AddSubjectsMySubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_page_my_subjects_item, parent, false);
        return new AddSubjectsMySubjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddSubjectsMySubjectAdapter.ViewHolder holder, int position) {
        holder.onBind(mySubjectsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mySubjectsList.size();
    }

    public void setMySubjectsList(ArrayList<MySubjects> list){
        this.mySubjectsList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectYear;
        TextView subjectName;
        TextView subjectGrade;
        TextView subjectCategory;
        ImageView minus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectYear = (TextView) itemView.findViewById(R.id.subject_year);
            subjectName = (TextView) itemView.findViewById(R.id.subject_subject);
            subjectGrade = (TextView) itemView.findViewById(R.id.subject_gpa);
            subjectCategory= (TextView) itemView.findViewById(R.id.subject_major);
            minus = (ImageView) itemView.findViewById(R.id.subject_minus);

            minus.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if(pos!=RecyclerView.NO_POSITION){
                    if(mListener !=null){
                        mListener.onItemClick(v,pos);
                    }
                }
                mySubjectsList.remove(pos);
                notifyDataSetChanged();
            });
        }

        void onBind(MySubjects item){
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
            subjectGrade.setText(""+item.getScore());
            subjectCategory.setText(subCategory);
//            minus.setOnClickListener(view -> {
////               mySubjectsList.remove(item);
//            });
        }
    }

}
