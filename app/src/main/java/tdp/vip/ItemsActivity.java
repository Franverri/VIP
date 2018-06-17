package tdp.vip;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemsActivity extends AppCompatActivity {

    private Button btnFollow;
    private TextView tvNombreFamoso;
    private String idFamoso;
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
        idFamoso = bundle.getString("idFamoso");

        switch (idFamoso){
            case "1":
                nombreFamoso = "Leonel Messi";
                imgFamoso.setImageDrawable(getResources().getDrawable(R.drawable.messi));
                break;
            case "2":
                nombreFamoso = "Cristiano Ronaldo";
                imgFamoso.setImageDrawable(getResources().getDrawable(R.drawable.ronaldo));
                break;
            case "3":
                nombreFamoso = "Justin Bieber";
                imgFamoso.setImageDrawable(getResources().getDrawable(R.drawable.justin));
                break;
            case "4":
                nombreFamoso = "Lali Esposito";
                imgFamoso.setImageDrawable(getResources().getDrawable(R.drawable.lali));
                break;
            default:
                nombreFamoso = "Nombre default";
        }

        //Seteo los parametros en la vista
        tvNombreFamoso = (TextView) findViewById(R.id.tvNombreFamoso);
        tvNombreFamoso.setText(nombreFamoso);

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

}
