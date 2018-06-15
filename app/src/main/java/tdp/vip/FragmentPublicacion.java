package tdp.vip;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import tdp.vip.dblocal.DBLocal;
import tdp.vip.dblocal.Publicacion;


/**
 * Fragmento que muestra en la UI una foto de publicacion con titulo y precio, y se puede clickear
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPublicacion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPublicacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPublicacion extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idpublicacion";

    private int idPublicacion;

    public FragmentPublicacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param idPublicacion ID de la publicacion
     * @return A new instance of fragment FragmentPublicacion.
     */
    public static FragmentPublicacion newInstance(int idPublicacion) {
        FragmentPublicacion fragment = new FragmentPublicacion();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idPublicacion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPublicacion = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_publicacion, container, false);
        // Cargamos la info
        Publicacion publicacion = DBLocal.getInstance().getPublicacion(idPublicacion);
        TextView titulo = view.findViewById(R.id.public_titulo);
        titulo.setText(publicacion.titulo);
        TextView precio = view.findViewById(R.id.public_precio);
        String precioFormateado = Util.intAPrecio(publicacion.precio);
        precio.setText(precioFormateado);
        ImageView foto = view.findViewById(R.id.public_foto);
        foto.setImageURI(publicacion.fotoURI);
        // Seteamos el click
        FrameLayout layout = view.findViewById(R.id.public_frame);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VerPublicacionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(ARG_PARAM1, idPublicacion);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        return view;
    }

}
