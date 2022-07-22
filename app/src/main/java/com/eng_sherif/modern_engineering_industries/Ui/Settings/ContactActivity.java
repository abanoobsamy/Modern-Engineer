package com.eng_sherif.modern_engineering_industries.Ui.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.eng_sherif.modern_engineering_industries.Adapters.SettingAdapter;
import com.eng_sherif.modern_engineering_industries.Model.Menu;
import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.databinding.ActivityContactBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    Toolbar toolbar;

    ActivityContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpToolbarContact();

        addListItem();
    }

    private void setUpToolbarContact() {

        toolbar = findViewById(R.id.toolbarContact);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.contact));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    private void addListItem() {

        List<Menu> menus = new ArrayList<>();

        menus.add(new Menu(R.drawable.ic_call, "01276326918"));
        menus.add(new Menu(R.drawable.ic_email,"modernengineeringindustries@gmail.com"));

        SettingAdapter adapter = new SettingAdapter(this, menus);

        binding.listViewContact.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Menu menu = menus.get(i);

                Toast.makeText(getApplicationContext(), menu.getTitle() + " #" + i, Toast.LENGTH_SHORT).show();

                switch (i) {

                    case 0:

                        goToCallContact(menu.getTitle());
                        break;

                    case 1:

                        goToEmail();
                        break;

                }
            }
        });
    }

    private void goToCallContact(String title) {

        Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel: " + title));
        startActivity(intent);
    }

    private void goToEmail() {

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto: modernengineeringindustries@gmail.com")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.hello_blank_fragment));
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"modernengineeringindustries@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.hello));
//        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, getString(R.string.chooseAnEmail)));
    }

}