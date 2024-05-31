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

public class UpdateActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextEmail, editTextPhone, editTextPassword;
    private Button buttonSubmit;
    private static final String TAG = "UpdateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Initialize views
        editTextId = findViewById(R.id.idEditText);
        editTextName = findViewById(R.id.nameEditText);
        editTextEmail = findViewById(R.id.emailEditText);
        editTextPhone = findViewById(R.id.phoneEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        buttonSubmit = findViewById(R.id.submitButton);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = Integer.parseInt(editTextId.getText().toString());
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();


                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                User update_user = new User();
                update_user.setName(name);
                update_user.setEmail(email);
                update_user.setPhone(phone);
                update_user.setPassword(password);

                Call<User> call = apiService.updateUser(userId,update_user); // Initialize Call object

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d(TAG, "Response code: " + response.code());
                        Log.d(TAG, "Response message: " + response.message());
                        if (response.isSuccessful()) {
                            Toast.makeText(UpdateActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateActivity.this, "Failed to updated user.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onFailure: " + response.message());

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
