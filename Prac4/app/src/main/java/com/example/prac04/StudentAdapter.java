package com.example.prac04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> studentList;
    private Context context;
    private OnItemClickListener listener;

    // Interface cho việc xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(Student student);
    }

    // Constructor
    public StudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    public void addStudent(Student student) {
        studentList.add(student);
        notifyItemInserted(studentList.size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);

        // Thiết lập thông tin cho các TextView
        holder.tvFullName.setText(student.getFullName());
        holder.tvId.setText(student.getId());
        holder.tvGPA.setText(String.valueOf(student.getGpa()));

        // Thiết lập hình đại diện dựa trên giới tính
        if (student.getGender().equalsIgnoreCase("Nam")) {
            holder.ivAvatar.setImageResource(R.drawable.avatar_nam); // Avatar cho nam
        } else {
            holder.ivAvatar.setImageResource(R.drawable.avatar_nu); // Avatar cho nữ
        }

        // Đặt sự kiện click
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(student);
            }
        });
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    // Setter cho OnItemClickListener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // ViewHolder cho adapter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFullName;
        TextView tvId;
        TextView tvGPA;
        ImageView ivAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvId = itemView.findViewById(R.id.tvId);
            tvGPA = itemView.findViewById(R.id.tvGPA);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }

    public void updateList(List<Student> newList) {
        studentList = newList;
        notifyDataSetChanged();
    }

}
