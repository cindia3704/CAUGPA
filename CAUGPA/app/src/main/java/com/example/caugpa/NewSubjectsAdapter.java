package com.example.caugpa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewSubjectsAdapter extends RecyclerView.Adapter<NewSubjectsAdapter.ViewHolder> {
    private ArrayList<AllSubjects> allSubjectsList;
    public interface onItemClickListener{
        void onItemClick(View v, int pos);
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
                if(pos!= RecyclerView.NO_POSITION){
                    if(mListener !=null){
                        mListener.onItemClick(v,pos);
                    }
                }
                allSubjectsList.remove(pos);
                notifyDataSetChanged();
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
