package tdp.vip;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import tdp.vip.dblocal.DBLocal;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editorShared;

    String nombre;
    String apellido;
    String nombreUsr;
    String mail;
    String fechaNacimiento;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le quito la barra de notificaciones
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPref = getSharedPreferences(getString(R.string.saved_data), Context.MODE_PRIVATE);
        editorShared = sharedPref.edit();

        nombre = sharedPref.getString("nombre", null);
        apellido = sharedPref.getString("apellido", null);
        nombreUsr = sharedPref.getString("nombreUsuario", null);
        mail = sharedPref.getString("mail", null);
        fechaNacimiento = sharedPref.getString("fechaNacimiento", null);

        setContentView(R.layout.activity_profile);

        TextView tvNombreUsr = (TextView) findViewById(R.id.profile_nombreUsr);
        EditText etNombre = (EditText) findViewById(R.id.profile_nombre);
        EditText etApellido = (EditText) findViewById(R.id.profile_apellido);
        EditText etMail = (EditText) findViewById(R.id.profile_mail);
        EditText etFechaNacimiento = (EditText) findViewById(R.id.profile_fechaNacimiento);

        tvNombreUsr.setText(nombreUsr);
        etNombre.setText(nombre);
        etApellido.setText(apellido);
        etMail.setText(mail);
        etFechaNacimiento.setText(fechaNacimiento);

        Button button= (Button) findViewById(R.id.profile_btnGuardar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges(v);
            }
        });
    }

    private void saveChanges(View v) {

        EditText etNombre = (EditText) findViewById(R.id.profile_nombre);
        EditText etApellido = (EditText) findViewById(R.id.profile_apellido);
        EditText etMail = (EditText) findViewById(R.id.profile_mail);
        EditText etFechaNacimiento = (EditText) findViewById(R.id.profile_fechaNacimiento);

        nombre = etNombre.getText().toString();
        apellido = etApellido.getText().toString();
        mail = etMail.getText().toString();
        fechaNacimiento = etFechaNacimiento.getText().toString();

        editorShared.putString("nombre", nombre);
        editorShared.putString("apellido", apellido);
        editorShared.putString("mail", mail);
        editorShared.putString("fechaNacimiento", fechaNacimiento);
        editorShared.apply();

        modificarDatosEnBDD(nombreUsr, nombre, apellido, mail, fechaNacimiento);

        goMain();

    }

    private void modificarDatosEnBDD(String nombreUsr, String nombre, String apellido, String mail, String fechaNacimiento) {
        DBLocal.getInstance().updateUsuario(nombreUsr,nombre,apellido,mail,fechaNacimiento);
    }

    private void goMain() {
        final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setTitle("Perfil");
        progressDialog.setMessage("Guardando cambios...");
        progressDialog.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressDialog.cancel();
                Toast.makeText(ProfileActivity.this, "Cambios guardados!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 4000);
    }
}
