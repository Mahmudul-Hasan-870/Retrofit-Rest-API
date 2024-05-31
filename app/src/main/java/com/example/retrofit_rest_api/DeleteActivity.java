package com.example.retrofit_rest_api;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    private EditText idEditText;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        idEditText = findViewById(R.id.idEditText);
        deleteButton = findViewById(R.id.deleteButton);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int userId = Integer.parseInt(idEditText.getText().toString());

                Call<Void> call = apiService.deleteUser(userId);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DeleteActivity.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DeleteActivity.this, "Failed to delete user", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });

    }
}