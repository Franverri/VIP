package tdp.vip;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ExampleDialog extends AppCompatDialogFragment {

    private EditText etNombre;
    private EditText etDescripcion;

    public int idPublicacion;

    @SuppressLint("ValidFragment")
    public ExampleDialog(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Saludo personalizado")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        goPublicacion();
                    }
                });

        etNombre = (EditText) view.findViewById(R.id.dialog_nombre);
        etDescripcion = (EditText) view.findViewById(R.id.dialog_descripcion);


        return builder.create();
    }

    private void goPublicacion() {
        Intent intent = new Intent(getActivity(), VerPublicacionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("idpublicacion", idPublicacion);
        intent.putExtras(bundle); //Put your id to your next Intent
        startActivity(intent);
    }
}
