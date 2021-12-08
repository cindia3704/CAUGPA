package com.example.caugpa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MySubjectsAdapter extends RecyclerView.Adapter<MySubjectsAdapter.ViewHolder> {
    private ArrayList<MySubjects> mySubjectsList;

    @NonNull
    @Override
    public MySubjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_subjects_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySubjectsAdapter.ViewHolder holder, int position) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectYear = (TextView) itemView.findViewById(R.id.item_year);
            subjectName = (TextView) itemView.findViewById(R.id.item_subject);
            subjectGrade = (TextView) itemView.findViewById(R.id.item_gpa);
            subjectCategory= (TextView) itemView.findViewById(R.id.item_major);
        }

        void onBind(MySubjects item){
            String subCategory;
            String major = item.getMajor();
            if(major == "N"){
                if(item.getMajorSpecific()=="ADV"){
                    subCategory = "전공심화";
                }else if(item.getMajorSpecific()=="BASIC"){
                    subCategory = "전공기초";
                }else{
                    subCategory = "전공필수";
                }
            }else{
                subCategory = "교양";
            }
            subjectYear.setText(""+item.getYear());
            subjectName.setText(item.getSubject());
            subjectGrade.setText(""+item.getScore());
            subjectCategory.setText(subCategory);
        }
    }
}
