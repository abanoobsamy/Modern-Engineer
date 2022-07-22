package com.eng_sherif.modern_engineering_industries.Utils;

import android.content.Context;

import com.eng_sherif.modern_engineering_industries.Model.Machine;
import com.eng_sherif.modern_engineering_industries.Model.User;
import com.eng_sherif.modern_engineering_industries.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class FirebaseMethod {

    private static final String TAG = "FirebaseMethod";

    private Context context;

    private FirebaseAuth mAuth;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private String userId;

    public FirebaseMethod(Context context) {

        this.context = context;

        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        //to check if have user or no which mean if != null that's mean have user to getUid.
        if (mAuth.getCurrentUser() != null) {
            userId = mAuth.getCurrentUser().getUid();
        }
    }

    /**
     * @param username
     * @param phone
     */
    public void addNewUserInfo(String username, String phone) {

        //Users
        User user = new User(userId, username, phone);

        myRef.child(context.getString(R.string.databaseUsers))
                .child(mAuth.getCurrentUser().getUid())
                .setValue(user);
    }

    public void addItemsMachine(String filing, String closingCapping, String packing, String handlingProcess) {

        //Machine
        Machine machine = new Machine(userId, filing, closingCapping, packing, handlingProcess);

        myRef.child(context.getString(R.string.databaseMachine))
                .child(mAuth.getCurrentUser().getUid())
                .setValue(machine);
    }
}
