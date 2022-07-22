package com.eng_sherif.modern_engineering_industries.Ui.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpToolbarChat();
    }

    private void setUpToolbarChat() {

        toolbar = findViewById(R.id.toolbarChat);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.chatUs));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

}