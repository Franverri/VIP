package tdp.vip;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import tdp.vip.dblocal.DBLocal;
import tdp.vip.dblocal.Usuario;

public class RegisterActivity extends AppCompatActivity {

    private Button btnFecha;
    private EditText fechaNacimiento;
    private int dia, mes, año;
    private DatePickerDialog.OnDateSetListener mDataSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le quito la barra de notificaciones
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_register);

        btnFecha = (Button) findViewById(R.id.btnPFecha);
        fechaNacimiento = (EditText) findViewById(R.id.regP_fechaNacimiento);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                año = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListener, año, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                String fecha = dia+"/"+(mes+1)+"/"+año;
                fechaNacimiento.setText(fecha);
            }
        };
    }

    public void registerClick(View view) {

        //Obtenemos los datos
        TextView viewNombreUsuario = findViewById(R.id.regP_usuario);
        final String nombreUsuario = ((EditText)viewNombreUsuario).getText().toString();
        final String nombre = ((EditText)findViewById(R.id.regP_nombre)).getText().toString();
        final String apellido = ((EditText)findViewById(R.id.regP_apellido)).getText().toString();
        final String password = ((EditText)findViewById(R.id.regP_contrasenia)).getText().toString();
        final String email = ((EditText)findViewById(R.id.regP_mail)).getText().toString();
        final String fechaNac = fechaNacimiento.getText().toString();

        if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                password.isEmpty() || email.isEmpty() || fechaNac.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Se requieren todos los campos.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        //Guardar nombre de usuario, contraseña y datos en SharedPref


        //Vovemos al inicio de sesion
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("VIP");
        progressDialog.setMessage("Registrando nuevo usuario...");
        progressDialog.show();

        if (DBLocal.getInstance().nombreUsuarioExiste(nombreUsuario)) {
            progressDialog.cancel();
            viewNombreUsuario.setError("Este nombre de usuario ya existe");
            viewNombreUsuario.requestFocus();
            return;
        }

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressDialog.cancel();
                Usuario usr = new Usuario(nombreUsuario, nombre, apellido, email, fechaNac, password);
                DBLocal.getInstance().usuarios.add(usr);
                Toast.makeText(RegisterActivity.this, "¡Nuevo usuario creado!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 4000);

    }
}
