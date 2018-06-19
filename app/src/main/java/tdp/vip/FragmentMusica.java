package tdp.vip;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tdp.vip.dblocal.DBLocal;
import tdp.vip.dblocal.Famoso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMusica.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMusica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMusica extends Fragment {

    public FragmentMusica() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMusica.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMusica newInstance(String param1, String param2) {
        FragmentMusica fragment = new FragmentMusica();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musica, container, false);
        agregarFamosos();
        return view;
    }

    protected void agregarFamosos() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        boolean lado = true;

        for (Famoso famoso: DBLocal.getInstance().famososMusica) {
            FragmentFamoso fragment = FragmentFamoso.newInstance(famoso.id);
            if (lado) {
                fragmentTransaction.add(R.id.izq, fragment);
            }
            else {
                fragmentTransaction.add(R.id.der, fragment);
            }
            lado = !lado;
        }

        fragmentTransaction.commit();
    }
}
