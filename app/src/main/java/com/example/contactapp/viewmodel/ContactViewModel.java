package com.example.contactapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.contactapp.database.Contact;
import com.example.contactapp.repository.ContactRepository;
import java.util.List;
public class ContactViewModel extends AndroidViewModel {
    private final ContactRepository repository;

    private final LiveData<List<Contact>> allContacts;

    public ContactViewModel(Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    public void insert(Contact contact) {
        repository.insert(contact);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public void update(Contact contact) {
        repository.update(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

}
