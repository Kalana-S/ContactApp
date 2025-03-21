package com.example.contactapp.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactapp.R;
import com.example.contactapp.database.Contact;
import com.example.contactapp.viewmodel.ContactViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ContactViewModel contactViewModel;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactAdapter = new ContactAdapter();
        recyclerView.setAdapter(contactAdapter);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactAdapter.setContacts(contacts);
            }
        });

        findViewById(R.id.buttonAddContact).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddContactActivity.class));
        });

        contactAdapter.setOnItemClickListener(contact -> {
            Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
            intent.putExtra("contact_id", contact.getId());
            intent.putExtra("contact_name", contact.getName());
            intent.putExtra("contact_phone", contact.getPhoneNumber());
            startActivity(intent);
        });
    }
}
