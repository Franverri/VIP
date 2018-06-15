package tdp.vip;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity que muestra una publicacion
 * Se pretende que se le pase un bundle con la ID de la publicacion para que llene los datos
 */
public class VerPublicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_publicacion);

        // Sacamos la id de publicacion del bundle que nos pasaron
        Bundle bundle = getIntent().getExtras();
        int idPublicacion = 1;
        if(bundle != null) idPublicacion = bundle.getInt("idpublicacion");

        // Agregamos el fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        VerPublicacionFragment fragment = VerPublicacionFragment.newInstance(idPublicacion);
        fragmentTransaction.add(R.id.layout, fragment);
        fragmentTransaction.commit();

    }
}
