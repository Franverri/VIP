package tdp.vip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public boolean estaEnPrincipal = true;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editorShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le quito la barra de notificaciones
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPref = getSharedPreferences(getString(R.string.saved_data), Context.MODE_PRIVATE);
        editorShared = sharedPref.edit();

        String nombre = sharedPref.getString("nombre", null);
        String apellido = sharedPref.getString("apellido", null);
        String nombreUsr = sharedPref.getString("nombreUsuario", null);



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        TextView navName = (TextView) headerView.findViewById(R.id.nav_header_nombre);
        navName.setText(nombre + " " + apellido);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_header_user);
        navUsername.setText(nombreUsr);

        ImageView navImage = (ImageView) headerView.findViewById(R.id.imageView);
        navImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goProfile();
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.d("PRUEBA", String.valueOf(estaEnPrincipal));
            if(estaEnPrincipal){
                super.onBackPressed();
                finish();
            } else {
                estaEnPrincipal = true;
                //setContentView(R.layout.activity_main);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment myFragment = null;
        boolean fragmentSelected = false;

        if (id == R.id.nav_gallery) {
            myFragment = new FragmentFutbol();
            fragmentSelected = true;
        } else if (id == R.id.nav_slideshow) {
            myFragment = new FragmentMusica();
            fragmentSelected = true;
        } else if (id == R.id.nav_manage) {
            myFragment = new FragmentTV();
            fragmentSelected = true;
        } else if (id == R.id.nav_share) {
            crearModal();
        } else if (id == R.id.nav_close) {
            editorShared.clear();
            editorShared.apply();
            goLogin();
        }

        if(fragmentSelected == true){
            estaEnPrincipal = false;
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void crearModal() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                Intent intent = new Intent(MainActivity.this, contact_form.class);
                startActivity(intent);
            }
        });

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void imgFutbolClick(View view) {
        estaEnPrincipal = false;
        Fragment myFragment = new FragmentFutbol();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();
        getSupportActionBar().setTitle("Futbol");
    }

    public void imgMusicClick(View view) {
        estaEnPrincipal = false;
        Fragment myFragment = new FragmentMusica();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();
        getSupportActionBar().setTitle("Musica");
    }

    public void imgTVClick(View view) {
        estaEnPrincipal = false;
        Fragment myFragment = new FragmentTV();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();
        getSupportActionBar().setTitle("TV/Cine");
    }

    public void goProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}
