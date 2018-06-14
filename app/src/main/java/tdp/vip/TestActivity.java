package tdp.vip;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tdp.vip.dblocal.Famoso;
import tdp.vip.dblocal.DBLocal;

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

        agregarFamosos();
    }

    protected void agregarFamosos() {
        for (Famoso famoso : DBLocal.getInstance().famosos) {
            agregarFamoso(famoso);
        }
    }

    protected void agregarFamoso(Famoso famoso) {

        ImageButton boton = new ImageButton(this);
        boton.setImageResource(getResources().getIdentifier(famoso.fotoResPath, "drawable", getPackageName()));
        //boton.setBackgroundColor(Color.TRANSPARENT);
        boton.setMaxHeight(1);
        boton.setMaxWidth(1);
        boton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.addView(boton, lp);
    }

}
