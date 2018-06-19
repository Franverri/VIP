package tdp.vip;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import tdp.vip.dblocal.DBLocal;
import tdp.vip.dblocal.EstadoPublicacion;
import tdp.vip.dblocal.Famoso;
import tdp.vip.dblocal.Publicacion;

public class ItemsActivity extends AppCompatActivity {

    private Button btnFollow;
    private TextView tvNombreFamoso;
    private Famoso famoso;
    private String nombreFamoso;
    private CircleImageView imgFamoso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le quito la barra de notificaciones
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_items);

        //Obtengo la referencia al boton de "Seguir"
        btnFollow = (Button) findViewById(R.id.btnFollow);

        //Obtengo la referencia a la imagen del famoso
        imgFamoso = (CircleImageView) findViewById(R.id.imgFamosoItems);

        //Obtengo los parametros pasados desde la activity anterior
        Bundle bundle = getIntent().getExtras();
        int idFamoso = bundle.getInt("idfamoso");

        famoso = DBLocal.getInstance().getFamoso(idFamoso);
        if (famoso != null) {
            nombreFamoso = famoso.nombreYApellido;
            imgFamoso.setImageURI(famoso.fotoURI);
        }
        else {
            nombreFamoso = "Nombre default";
        }

        //Seteo los parametros en la vista
        tvNombreFamoso = (TextView) findViewById(R.id.tvNombreFamoso);
        tvNombreFamoso.setText(nombreFamoso);

        agregarPublicaciones(idFamoso);
    }

    public void followFamous(View view) {
        if(btnFollow.getText().equals("Seguir")){
            btnFollow.setText("Seguido");
            btnFollow.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbuttongreen));
        } else {
            btnFollow.setText("Seguir");
            btnFollow.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
        }
    }

    /**
     * Agrega a la activity las publicaciones relacionadas al famoso
     * @param idFamoso              Id del famoso
     */
    protected void agregarPublicaciones(int idFamoso) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Publicacion publicacion: DBLocal.getInstance().getPublicacionesDeFamosoALaVenta(idFamoso) ) {
            FragmentPublicacion fragment = FragmentPublicacion.newInstance(publicacion.id);
            fragmentTransaction.add(R.id.items_cards, fragment);
        }
        fragmentTransaction.commit();
    }

}
