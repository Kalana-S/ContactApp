package com.example.contactapp.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactapp.R;
import com.example.contactapp.viewmodel.ContactViewModel;
import android.widget.SearchView;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.PorterDuff;

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
        contactViewModel.getAllContacts().observe(this, contacts -> contactAdapter.setContacts(contacts));

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

        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.getFilter().filter(newText);
                return false;
            }
        });

        int searchTextId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = searchView.findViewById(searchTextId);
        if (searchEditText != null) {
            searchEditText.setTextColor(Color.WHITE);
            searchEditText.setHintTextColor(Color.LTGRAY);
        }

        int searchIconId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_mag_icon", null, null);
        ImageView searchIcon = searchView.findViewById(searchIconId);
        if (searchIcon != null) {
            searchIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }

        int closeButtonId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButton = searchView.findViewById(closeButtonId);
        if (closeButton != null) {
            closeButton.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }
    }
}
