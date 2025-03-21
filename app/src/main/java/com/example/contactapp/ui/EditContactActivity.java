package com.example.contactapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.contactapp.R;
import com.example.contactapp.database.Contact;
import com.example.contactapp.viewmodel.ContactViewModel;

public class EditContactActivity extends AppCompatActivity {
    private EditText editTextName, editTextPhone;
    private ContactViewModel contactViewModel;
    private int contactId;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        Button btnUpdate = findViewById(R.id.buttonUpdate);
        Button btnDelete = findViewById(R.id.buttonDelete);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra("contact_id") && intent.hasExtra("contact_name") && intent.hasExtra("contact_phone")) {
            contactId = intent.getIntExtra("contact_id", -1);
            String name = intent.getStringExtra("contact_name");
            String phone = intent.getStringExtra("contact_phone");

            editTextName.setText(name);
            editTextPhone.setText(phone);

            currentContact = new Contact(name, phone);
            currentContact.setId(contactId);
        }

        btnUpdate.setOnClickListener(view -> updateContact());

        btnDelete.setOnClickListener(view -> deleteContact());
    }

    private void updateContact() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        currentContact.setName(name);
        currentContact.setPhoneNumber(phone);
        contactViewModel.update(currentContact);
        Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteContact() {
        contactViewModel.delete(currentContact);
        Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
