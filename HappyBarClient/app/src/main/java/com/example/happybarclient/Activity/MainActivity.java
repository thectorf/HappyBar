package com.example.happybarclient.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happybarclient.CircleTransform;
import com.example.happybarclient.Fragments.CodesQRFragment;
import com.example.happybarclient.Fragments.InicioFragment;
import com.example.happybarclient.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView nameUser;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        viewFinds();

        toolbar.setTitle("None");
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(navigationItemSelect);
        navigationView.setItemIconTintList(null);
        hamburgerConfigMenu();
        setFragment(0);

        View hView =  navigationView.getHeaderView(0);
        ImageView imageFotoUser = hView.findViewById(R.id.image_photo_default_user);
        TextView nameUser = hView.findViewById(R.id.name_user_navigation);
        nameUser.setText(user.getDisplayName());

        Picasso.get().load(R.drawable.default_photo_user)
                .transform(new CircleTransform())
                .into(imageFotoUser);

    }

    private void hamburgerConfigMenu(){
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_toolbar, R.string.close_toolbar){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));

        } else {
            actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        }
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelect = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            if(menuItem.isChecked()){
                menuItem.setChecked(false);
            }else{
                menuItem.setChecked(true);
            }
            drawerLayout.closeDrawers();

            switch (menuItem.getItemId()){

                case R.id.inicio:
                    setFragment(0);
                    break;
                case R.id.codes_qr:
                    setFragment(1);
                    break;
                case R.id.cerrar_sesion:
                    signOutUser().show();
                    break;
            }

            return false;
        }
    };

    public AlertDialog signOutUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Salir")
                .setMessage("Quieres cerrar la sesión?")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAuth.signOut();
                                Intent intent = new Intent(getApplication(), LoginRegisterActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

        return builder.create();
    }

    private void viewFinds(){
        nameUser = findViewById(R.id.name_user_toolbar);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_header_container);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void setFragment(int pos){

        FragmentManager fragmentManager = getSupportFragmentManager();              //Se llama al manager de Fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();       //Se llama al metodo FragmentTransaction y se asigna al fragmentManager antes creado con beginTransaction()

        switch (pos){
            case 0:
                toolbar.setTitle("Tragos");
                InicioFragment inicioFragment = new InicioFragment();
                transaction.replace(R.id.fragment_navigation_drawer, inicioFragment);
                transaction.commit();
                break;
            case 1:
                toolbar.setTitle("Mis Códigos");
                CodesQRFragment codesQRFragment = new CodesQRFragment();
                transaction.replace(R.id.fragment_navigation_drawer, codesQRFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
