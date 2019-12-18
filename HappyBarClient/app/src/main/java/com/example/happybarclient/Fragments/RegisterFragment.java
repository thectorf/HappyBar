
package com.example.happybarclient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happybarclient.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Button btnRegister;
    private TextInputEditText correoRegister, passwordRegister, nameRegister;

    public RegisterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewFinds(view);

        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(btnRegisterFunction);
    }

    private View.OnClickListener btnRegisterFunction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String correo = String.valueOf(correoRegister.getText());
            String password = String.valueOf(passwordRegister.getText());
            final String name = String.valueOf(nameRegister.getText());
            if(correo.length() > 0 && password.length() > 0 && name.length() > 0){
                mAuth.createUserWithEmailAndPassword(correo, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //si se registró el usuario con exito.
                                    final FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();

                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getActivity(), String.valueOf(user.getDisplayName()), Toast.LENGTH_SHORT).show();
                                                        getActivity().onBackPressed();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                Toast.makeText(getContext(), "No se permiten campos vacios", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void viewFinds(View view){
        btnRegister = view.findViewById(R.id.btn_register);
        correoRegister = view.findViewById(R.id.correo_register);
        passwordRegister = view.findViewById(R.id.password_register);
        nameRegister = view.findViewById(R.id.name_register);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}