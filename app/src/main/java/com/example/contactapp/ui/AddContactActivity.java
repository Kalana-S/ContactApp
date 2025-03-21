package com.example.contactapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.contactapp.R;
import com.example.contactapp.database.Contact;
import com.example.contactapp.viewmodel.ContactViewModel;

public class AddContactActivity extends AppCompatActivity {
    private EditText editTextName, editTextPhone;
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        Button btnSave = findViewById(R.id.buttonSave);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        btnSave.setOnClickListener(view -> saveContact());
    }

    private void saveContact() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact contact = new Contact(name, phone);
        contactViewModel.insert(contact);
        Toast.makeText(this, "Contact Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
