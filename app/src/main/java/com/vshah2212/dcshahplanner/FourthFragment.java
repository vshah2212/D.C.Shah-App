package com.vshah2212.dcshahplanner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FourthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FourthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourthFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText evName,evDate,evLocation,evGroup;
    Button createEv;
    String nameEv,date,loc,grp;


    private OnFragmentInteractionListener mListener;

    public FourthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("Create Event");
        }

        evName = (EditText) view.findViewById(R.id.EventName);
        evDate = (EditText) view.findViewById(R.id.EventDate);
        evLocation = (EditText) view.findViewById(R.id.EventLoc);
        createEv = (Button) view.findViewById(R.id.CreateEv);
        evGroup = (EditText) view.findViewById(R.id.EventGroup);


        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...
      /*  spinner_1 = (Spinner) view.findViewById(R.id.spinner);
        AdapterView xy = new AdapterView(getContext()) {
            @Override
            public Adapter getAdapter() {
                return null;
            }

            @Override
            public void setAdapter(Adapter adapter) {

            }

            @Override
            public View getSelectedView() {
                return null;
            }

            @Override
            public void setSelection(int position) {

            }
        };
        spinner_1.setOnItemSelectedListener(xy.getOnItemSelectedListener());
        List<String> list = new ArrayList<String>();
        list.add("All");
        list.add("Moryas");
        list.add("Tiyas");
        list.add("Tenyas");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter); */


        createEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEv = evName.getText().toString();
                date = evDate.getText().toString();
                loc = evLocation.getText().toString();
          //      grp = spinner_1.getSelectedItem().toString();
                grp = evGroup.getText().toString();
                String msg = nameEv+","+date+","+loc+","+grp;
               // Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                new NewEvent(getContext()).execute(msg);
            }
        });


        return view;
    }


   /* public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {


                grp = parent.getSelectedItem().toString();


    } */


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("Create Event");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String uri);
    }
}
