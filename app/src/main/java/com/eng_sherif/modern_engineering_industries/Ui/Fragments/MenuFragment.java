package com.eng_sherif.modern_engineering_industries.Ui.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import io.paperdb.Paper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.eng_sherif.modern_engineering_industries.Adapters.SettingAdapter;
import com.eng_sherif.modern_engineering_industries.Model.Menu;
import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.Ui.Settings.ChatActivity;
import com.eng_sherif.modern_engineering_industries.Ui.Settings.AboutActivity;
import com.eng_sherif.modern_engineering_industries.Ui.Settings.ChangeLanguageActivity;
import com.eng_sherif.modern_engineering_industries.Ui.Settings.ContactActivity;
import com.eng_sherif.modern_engineering_industries.Ui.SignUser.SendOTPActivity;
import com.eng_sherif.modern_engineering_industries.Utils.LocaleHelper;
import com.eng_sherif.modern_engineering_industries.databinding.FragmentMenuBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private static final String TAG = "MenuFragment";

    private FragmentMenuBinding binding;

    private FirebaseAuth mAuth;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(getLayoutInflater(), container, false);

        setUpFirebaseAuthentication();

        addListItem();

        return binding.getRoot();
    }

    private void addListItem() {

        List<Menu> menus = new ArrayList<>();

        menus.add(new Menu(R.drawable.ic_location, getString(R.string.location)));
        menus.add(new Menu(R.drawable.ic_contact_phone, getString(R.string.contact)));
        menus.add(new Menu(R.drawable.ic_info, getString(R.string.about)));
        menus.add(new Menu(R.drawable.ic_chat, getString(R.string.chatUs)));
        menus.add(new Menu(R.drawable.ic_language, getString(R.string.language)));
        menus.add(new Menu(R.drawable.ic_exit, getString(R.string.signout)));

        SettingAdapter adapter = new SettingAdapter(requireContext(), menus);

        binding.listViewMenu.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Menu menu = menus.get(i);

                Toast.makeText(requireContext(), menu.getTitle() + " #" + i, Toast.LENGTH_SHORT).show();

                switch (i) {

                    case 0:

                        goToOurLocation();
                        break;

                    case 1:

                        Intent intent1 = new Intent(requireContext(), ContactActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:

                        Intent intent2 = new Intent(requireContext(), AboutActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:

//                        Intent intent3 = new Intent(requireContext(), ChatActivity.class);
//                        startActivity(intent3);

                        setUpSendUsNowChat();
                        break;

                    case 4:

                        Intent intent4 = new Intent(requireContext(), ChangeLanguageActivity.class);
                        startActivity(intent4);
                        break;

                    case 5:

                        setUpDialogLogout();
                        break;
                }
            }
        });
    }

    private void setUpDialogLogout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getString(R.string.titleLogout));
        builder.setMessage(getString(R.string.messageLogout));

        // Add the buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button

                mAuth.signOut();

                setUpActivityLogout();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog

                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void goToOurLocation() {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo: <29.9627>,<30.9269>?q=<29.9627>,<30.9269>(Label+Name)"));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setUpActivityLogout() {

        Intent intent = new Intent(getActivity(), SendOTPActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private void checkCurrentUser(FirebaseUser user) {

        if (user == null) {

            setUpActivityLogout();
        }
    }

    private void setUpFirebaseAuthentication() {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser fUser = mAuth.getCurrentUser();

                if (fUser != null) {

                    Log.d(TAG, "onAuthStateChanged: SignIn" + fUser.getUid());
                } else {

                    Log.d(TAG, "onAuthStateChanged: SignOut");

                    //check if the user logged in
                    checkCurrentUser(fUser);
                }
            }
        };
    }

    private void setUpSendUsNowChat() {

        PackageManager pm = getActivity().getPackageManager();

        Intent waIntent = new Intent(Intent.ACTION_VIEW);

        String text = getString(R.string.hello) + "\n";

        waIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+201276326918&&text=" + text));

        if (waIntent.resolveActivity(pm) != null) {

            Toast.makeText(
                    getActivity(),
                    getString(R.string.pleaseInstallWhatsapp),
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // Starting Whatsapp
        startActivity(waIntent);

    }

}