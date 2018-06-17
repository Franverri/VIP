package tdp.vip;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ItemsActivity extends AppCompatActivity {

    private Button btnFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le quito la barra de notificaciones
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_items);

        //Obtengo la referencia al boton de "Seguir"
        btnFollow = (Button) findViewById(R.id.btnFollow);
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
