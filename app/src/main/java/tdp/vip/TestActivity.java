package tdp.vip;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tdp.vip.dblocal.EstadoPublicacion;
import tdp.vip.dblocal.Famoso;
import tdp.vip.dblocal.DBLocal;
import tdp.vip.dblocal.Publicacion;

/**
 * Una activity para testear giladas
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        agregarPublicaciones();
    }

    protected void agregarFamosos() {
        for (Famoso famoso : DBLocal.getInstance().famosos) {
            agregarFamoso(famoso);
        }
    }

    protected void agregarFamoso(Famoso famoso) {

        ImageButton boton = new ImageButton(this);
        boton.setImageURI(famoso.fotoURI);
        boton.setMaxHeight(1);
        boton.setMaxWidth(1);
        boton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.addView(boton, lp);
    }

    protected void agregarPublicaciones() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Publicacion publicacion: DBLocal.getInstance().publicaciones) {
            if (publicacion.estado == EstadoPublicacion.A_LA_VENTA) {
                FragmentPublicacion fragment = FragmentPublicacion.newInstance(publicacion.id);
                fragmentTransaction.add(R.id.layout, fragment);
            }
        }

        fragmentTransaction.commit();
    }

    protected void agregarPublicacionesTest() {
        for (Publicacion publicacion: DBLocal.getInstance().publicaciones) {
            agregarPublicacion(publicacion);
        }


    }

    protected void agregarPublicacion(Publicacion publicacion) {

        ImageButton boton = new ImageButton(this);
        boton.setImageURI(publicacion.fotoURI);
        boton.setMaxHeight(10);
        boton.setMaxWidth(10);
        boton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.addView(boton, lp);
    }

}
