package tdp.vip;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tdp.vip.dblocal.DBLocal;
import tdp.vip.dblocal.Publicacion;


/**
 * Fragmento para la activity que muestra el detalle de una publicación específica
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VerPublicacionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VerPublicacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerPublicacionFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idpublicacion";

    private int idPublicacion;

    public VerPublicacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param idPublicacion ID de la publicacion
     * @return A new instance of fragment FragmentPublicacion.
     */
    public static VerPublicacionFragment newInstance(int idPublicacion) {
        VerPublicacionFragment fragment = new VerPublicacionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idPublicacion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPublicacion = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_publicacion, container, false);

        Publicacion publicacion = DBLocal.getInstance().getPublicacion(idPublicacion);
        TextView titulo = view.findViewById(R.id.ver_public_titulo);
        titulo.setText(publicacion.titulo);
        TextView descrip = view.findViewById(R.id.ver_public_descrip);
        descrip.setText(publicacion.descripcion);
        TextView precio = view.findViewById(R.id.ver_public_precio);
        String precioFormateado = Util.intAPrecio(publicacion.precio);
        precio.setText(precioFormateado);
        ImageView foto = view.findViewById(R.id.ver_public_foto);
        foto.setImageURI(publicacion.fotoURI);
        return view;
    }

}
