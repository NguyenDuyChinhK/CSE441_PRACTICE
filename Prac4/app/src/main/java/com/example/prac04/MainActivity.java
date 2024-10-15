package com.example.prac04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView; // Import cho SearchView
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu; // Import cho Menu
import android.view.MenuInflater; // Import cho MenuInflater
import android.view.MenuItem; // Import cho MenuItem
import android.widget.Toast;
import android.widget.Button;
import java.util.Comparator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    public static List<Student> studentList;
    private FloatingActionButton fab;

    public static final int ADD_STUDENT_REQUEST = 1;
    public static final int EDIT_STUDENT_REQUEST = 2; // Mã cho yêu cầu chỉnh sửa

    // Biến để lưu trữ sinh viên được chọn để chỉnh sửa
    private Student selectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Tham chiếu đúng đến R.layout.activity_main

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

//        Button btnSearch = findViewById(R.id.btnSearch);
//        SearchView searchView = findViewById(R.id.searchView);

        studentList = loadStudentsFromJSON();
        adapter = new StudentAdapter(studentList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student student) {
                // Khi nhấn vào một sinh viên, thiết lập sinh viên được chọn
                selectedStudent = student;
                // Mở DetailActivity hoặc thực hiện hành động khác nếu cần
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("STUDENT", student);
                startActivityForResult(intent, EDIT_STUDENT_REQUEST); // Gọi startActivityForResult để nhận kết quả
            }
        });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, ADD_STUDENT_REQUEST);
        });
//        btnSearch.setOnClickListener(v -> {
//            String query = searchView.getQuery().toString();
//            searchStudent(query); // Gọi phương thức tìm kiếm đã được định nghĩa
//        });
    }

    private void sortStudentsAZ() {
        studentList.sort((s1, s2) -> s1.getFullName().compareToIgnoreCase(s2.getFullName()));
        adapter.notifyDataSetChanged(); // Cập nhật giao diện
    }

    private void sortStudentsZA() {
        studentList.sort((s1, s2) -> s2.getFullName().compareToIgnoreCase(s1.getFullName()));
        adapter.notifyDataSetChanged(); // Cập nhật giao diện
    }

    private List<Student> loadStudentsFromJSON() {
        List<Student> students = new ArrayList<>();
        String json = loadJSONFromRawResource(R.raw.student);
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String id = obj.getString("id");
                    String email = obj.getString("email");
                    String fullName = obj.getString("fullName");
                    String gender = obj.getString("gender");
                    String major = obj.getString("major");
                    double gpa = obj.getDouble("gpa");
                    String birthDay = obj.getString("birthDay");
                    String address = obj.getString("address");
                    int year = obj.getInt("year");

                    students.add(new Student(id, email, fullName, gender, major, gpa, birthDay, address, year));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    private String loadJSONFromRawResource(int resourceId) {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(resourceId);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            // Nhận thông tin sinh viên mới được thêm
            String id = data.getStringExtra("id");
            String email = data.getStringExtra("email");
            String fullName = data.getStringExtra("fullName");
            String gender = data.getStringExtra("gender");
            String major = data.getStringExtra("major");
            double gpa = data.getDoubleExtra("gpa", 0.0);
            String birthDay = data.getStringExtra("birthDay");
            String address = data.getStringExtra("address");
            int year = data.getIntExtra("year", 0);

            Student newStudent = new Student(id, email, fullName, gender, major, gpa, birthDay, address, year);
            adapter.addStudent(newStudent);
            Toast.makeText(this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_STUDENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Nhận thông tin sinh viên đã chỉnh sửa
                String id = data.getStringExtra("id");
                String email = data.getStringExtra("email");
                String fullName = data.getStringExtra("fullName");
                String gender = data.getStringExtra("gender");
                String major = data.getStringExtra("major");
                double gpa = data.getDoubleExtra("gpa", 0.0);
                String birthDay = data.getStringExtra("birthDay");
                String address = data.getStringExtra("address");
                int year = data.getIntExtra("year", 0);

                // Tìm sinh viên trong danh sách và cập nhật thông tin
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getId().equals(id)) {
                        studentList.set(i, new Student(id, email, fullName, gender, major, gpa, birthDay, address, year));
                        adapter.notifyItemChanged(i); // Cập nhật giao diện
                        Toast.makeText(this, "Cập nhật sinh viên thành công!", Toast.LENGTH_SHORT).show();

                        // Cập nhật selectedStudent nếu cần
                        if (selectedStudent != null && selectedStudent.getId().equals(id)) {
                            selectedStudent = studentList.get(i);
                        }

                        break;
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                // Nhận thông tin sinh viên đã bị xóa
                String id = data.getStringExtra("id");
                // Tìm và xóa sinh viên trong danh sách
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getId().equals(id)) {
                        studentList.remove(i);
                        adapter.notifyItemRemoved(i); // Cập nhật giao diện
                        Toast.makeText(this, "Xóa sinh viên thành công!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        }
    }

    // Tạo Menu trong App Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Thiết lập SearchView (nếu có)
        MenuItem searchItem = menu.findItem(R.id.action_search_student);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setQueryHint("Tìm kiếm sinh viên...");

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query)
                {
                    searchStudent(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText)
                {
                    searchStudent(newText);
                    return false;
                }
            });
        }

        return true;
    }

    // Xử lý các lựa chọn trong Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit_student) {
            if (selectedStudent != null) {
                Intent editIntent = new Intent(MainActivity.this, EditStudentActivity.class);
                editIntent.putExtra("STUDENT", selectedStudent);
                startActivityForResult(editIntent, EDIT_STUDENT_REQUEST);
            } else {
                Toast.makeText(this, "Vui lòng chọn sinh viên để sửa.", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.action_sort_az) {
            sortStudentsAZ();
            return true;
        } else if (id == R.id.action_sort_za) {
            sortStudentsZA();
            return true;
        } else if (id == R.id.action_sort_gpa_asc) {
            sortStudentsByGpaAsc();
            return true;
        } else if (id == R.id.action_sort_gpa_desc) {
            sortStudentsByGpaDesc();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void sortStudentsByFullNameAsc() {
        studentList.sort((s1, s2) -> {
            int nameComparison = s1.getFullName().compareToIgnoreCase(s2.getFullName());
            if (nameComparison != 0) {
                return nameComparison;
            } else {
                // Nếu tên giống nhau, sắp xếp theo họ
                return s1.getLastName().compareToIgnoreCase(s2.getLastName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortStudentsByFullNameDesc() {
        studentList.sort((s1, s2) -> {
            int nameComparison = s2.getFullName().compareToIgnoreCase(s1.getFullName());
            if (nameComparison != 0) {
                return nameComparison;
            } else {
                // Nếu tên giống nhau, sắp xếp theo họ
                return s2.getLastName().compareToIgnoreCase(s1.getLastName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortStudentsByGpaAsc() {
        studentList.sort(Comparator.comparingDouble(Student::getGpa));
        adapter.notifyDataSetChanged();
    }

    private void sortStudentsByGpaDesc() {
        studentList.sort(Comparator.comparingDouble(Student::getGpa).reversed());
        adapter.notifyDataSetChanged();
    }


    // Phương thức tìm kiếm sinh viên theo tên gần đúng
    private void searchStudent(String name) {
        if (name.isEmpty()) {
            adapter.updateList(studentList);
            return;
        }

        List<Student> filteredList = new ArrayList<>();
        for (Student student : studentList) {
            // Kiểm tra xem tên sinh viên có chứa chuỗi tìm kiếm không
            if (student.getFullName().toLowerCase().contains(name.toLowerCase())) {
                filteredList.add(student);
            }
        }
        adapter.updateList(filteredList);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Thực hiện thêm logic nếu cần thiết
    }

}
