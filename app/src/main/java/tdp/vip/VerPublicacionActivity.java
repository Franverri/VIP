package tdp.vip;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Activity que muestra una publicacion
 * Se pretende que se le pase un bundle con la ID de la publicacion para que llene los datos
 */
public class VerPublicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le quito la barra de notificaciones
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

    public void goPaymentActivity(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}
