package com.example.happybarclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.happybarclient.Fragments.LoginFragment;
import com.example.happybarclient.R;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        LoginFragment loginFragment =new LoginFragment();
        fragmentTransaction.replace(R.id.frament_layout_login_register, loginFragment);
        fragmentTransaction.commit();

    }
}
