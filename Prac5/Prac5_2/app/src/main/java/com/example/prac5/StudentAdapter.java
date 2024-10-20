package com.example.prac5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> studentList;
    private Context context;
    private OnStudentClickListener onStudentClickListener;
    private OnStudentDeleteClickListener onStudentDeleteClickListener;

    public interface OnStudentClickListener {
        void onEditClick(Student student);
    }

    public interface OnStudentDeleteClickListener {
        void onDeleteClick(Student student);
    }

    public StudentAdapter(List<Student> studentList, Context context,
                          OnStudentClickListener onStudentClickListener,
                          OnStudentDeleteClickListener onStudentDeleteClickListener) {
        this.studentList = studentList;
        this.context = context;
        this.onStudentClickListener = onStudentClickListener;
        this.onStudentDeleteClickListener = onStudentDeleteClickListener;
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
        holder.tvStudentName.setText(student.getHoten());
        holder.tvStudentId.setText("MSSV: " + student.getMssv());
        holder.tvStudentClass.setText("Lớp: " + student.getLop());
        holder.tvStudentScore.setText("Điểm: " + student.getDiem());

        holder.btnEdit.setOnClickListener(v -> onStudentClickListener.onEditClick(student));

        holder.btnDelete.setOnClickListener(v -> {
            if (onStudentDeleteClickListener != null) {
                onStudentDeleteClickListener.onDeleteClick(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName, tvStudentId, tvStudentClass, tvStudentScore;
        ImageButton btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvStudentId = itemView.findViewById(R.id.tvStudentId);
            tvStudentClass = itemView.findViewById(R.id.tvStudentClass);
            tvStudentScore = itemView.findViewById(R.id.tvStudentScore);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
