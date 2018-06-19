package tdp.vip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import tdp.vip.dblocal.DBLocal;
import tdp.vip.dblocal.Famoso;

/**
 * Fragmento que muestra en la UI una foto de un famoso con nombre, y se puede clickear
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentFamoso.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentFamoso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFamoso extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idfamoso";

    private int idFamoso;

    public FragmentFamoso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFamoso.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFamoso newInstance(int idFamoso) {
        FragmentFamoso fragment = new FragmentFamoso();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idFamoso);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idFamoso = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_famoso, container, false);

        // Cargamos la info
        Famoso famoso = DBLocal.getInstance().getFamoso(idFamoso);
        AppCompatTextView nombre = view.findViewById(R.id.famoso_nombre);
        nombre.setText(famoso.nombreYApellido);
        CircleImageView foto = view.findViewById(R.id.famoso_foto);
        foto.setImageURI(famoso.fotoURI);

        // Seteamos el click
        FrameLayout layout = view.findViewById(R.id.famoso_frame);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ItemsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(ARG_PARAM1, idFamoso);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        return view;
    }

}
