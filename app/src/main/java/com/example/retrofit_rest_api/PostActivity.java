package com.example.retrofit_rest_api;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone,editTextPassword;
    private Button buttonSubmit;
    private static final String TAG = "PostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Initialize views
        editTextName = findViewById(R.id.nameEditText);
        editTextEmail = findViewById(R.id.emailEditText);
        editTextPhone = findViewById(R.id.phoneEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        buttonSubmit = findViewById(R.id.submitButton);

        ApiServices apiServices = RetrofitClient.getRetrofitInstance().create(ApiServices.class);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();


                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(PostActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(name, email, phone,password); // Create User object

                Call<User> call = apiServices.createUser(user); // Initialize Call object

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(PostActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostActivity.this, "Failed to create user.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });
    }
}
