package com.example.contactapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.contactapp.database.AppDatabase;
import com.example.contactapp.database.Contact;
import com.example.contactapp.database.ContactDao;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class ContactRepository {
    private final ContactDao contactDao;
    private final ExecutorService executorService;

    public ContactRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        contactDao = database.contactDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Contact contact) {
        executorService.execute(() -> contactDao.insert(contact));
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public void update(Contact contact) {
        executorService.execute(() -> contactDao.update(contact));
    }

    public void delete(Contact contact) {
        executorService.execute(() -> contactDao.delete(contact));
    }

}
