package com.example.prac5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        studentAdapter = new StudentAdapter(studentList, this,
                student -> {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("student_id", student.getMssv());
                    intent.putExtra("student_name", student.getHoten());
                    intent.putExtra("student_class", student.getLop());
                    intent.putExtra("student_gpa", student.getDiem());
                    startActivity(intent);
                },
                student -> {
                    deleteStudent(student);
                }
        );


        recyclerView.setAdapter(studentAdapter);
        getStudentsFromFirebase();

        FloatingActionButton btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void getStudentsFromFirebase() {
        db.collection("sinhvien").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(MainActivity.this, "Lỗi khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value != null) {
                    studentList.clear();
                    for (DocumentSnapshot studentSnapshot : value.getDocuments()) {
                        Student student = studentSnapshot.toObject(Student.class);
                        if (student != null) {
                            student.setMssv(studentSnapshot.getId());
                            studentList.add(student);
                        }
                    }
                    studentAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void deleteStudent(Student student) {
        db.collection("sinhvien").document(student.getMssv())
                .delete()
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Xóa sinh viên thành công", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Lỗi khi xóa sinh viên", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            getStudentsFromFirebase();
        }
    }
}
