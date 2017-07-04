package com.vshah2212.dcshahplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {
    Button next1,view1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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
        View view= inflater.inflate(R.layout.fragment_third, container, false);

        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("Upcoming Events");
        }

        int x=0;
        final String[] Eventarr = new String[50];
        next1 = (Button) view.findViewById(R.id.nextbtn);
        view1 = (Button) view.findViewById(R.id.viewAll);

        SharedPreferences prefs = getContext().getSharedPreferences("LoginPref", MODE_PRIVATE);
        String usn = prefs.getString("Username", "No name defined");


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        String result = null;
        InputStream is = null;

        try{
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost;

            httppost = new HttpPost("http://dcshahfamily.esy.es/getEvent.php?username="+usn);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            //   Log.e("log_tag", "connection success ");
            //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            //   Log.e("log_tag", "Error in http connection "+e.toString());
            //  Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

        }
        //convert response to string
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();
        }
        catch(Exception e)
        {
            //   Log.e("log_tag", "Error converting result "+e.toString());
            //   Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();
        }

        //parse json data
        try
        {

            Log.e("result","Result string in jSON "+result);

            JSONArray jArray = new JSONArray(result);


            // String re=jArray.getString(jArray.length()-1);


            TableLayout tv=(TableLayout) view.findViewById(R.id.table2);
            tv.removeAllViewsInLayout();

            int flag=1;

            for(int i=-1;i<jArray.length();i++)

            {




                TableRow tr=new TableRow(getContext());

                tr.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));




                if(flag==1)
                {

                    TextView b6=new TextView(getContext());
                    b6.setPadding(0,0,0,0);
                    b6.setText("Event Name");
                    b6.setTextColor(Color.BLUE);
                    b6.setTextSize(15);
                    tr.addView(b6);


                    TextView b19=new TextView(getContext());
                    b19.setPadding(40,0, 0, 0);
                    b19.setTextSize(15);
                    b19.setText("Date");
                    b19.setTextColor(Color.BLUE);
                    tr.addView(b19);


                    TextView b12=new TextView(getContext());
                    b12.setPadding(40,0, 0, 0);
                    b12.setTextSize(15);
                    b12.setText("Location");
                    b12.setTextColor(Color.BLUE);
                    tr.addView(b12);

                    TextView b69=new TextView(getContext());
                    b69.setPadding(40,0, 0, 0);
                    b69.setTextSize(15);
                    b69.setText("Group");
                    b69.setTextColor(Color.BLUE);
                    tr.addView(b69);


                    tv.addView(tr);

                    final View vline = new View(getContext());
                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                    vline.setBackgroundColor(Color.BLUE);



                    tv.addView(vline);
                    flag=0;


                }

                else
                {



                    JSONObject json_data = jArray.getJSONObject(i);
                    

                    TextView b1=new TextView(getContext());
                    b1.setPadding(10, 0, 0, 0);
                    b1.setTextSize(15);
                    String stime1=json_data.getString("Name");
                    Eventarr[x] = stime1;
                    x++;
                    b1.setText(stime1);
                    b1.setTextColor(Color.RED);
                    tr.addView(b1);

                    TextView b2=new TextView(getContext());
                    b2.setPadding(40, 0, 0, 0);
                    String stime2=json_data.getString("Date");
                    b2.setText(stime2);
                    b2.setTextColor(Color.RED);
                    b2.setTextSize(15);
                    tr.addView(b2);

                    TextView b3=new TextView(getContext());
                    b3.setPadding(40, 0, 0, 0);
                    String stime3=json_data.getString("Location");
                    b3.setText(stime3);
                    b3.setTextColor(Color.RED);
                    b3.setTextSize(15);
                    tr.addView(b3);

                    TextView b66=new TextView(getContext());
                    b66.setPadding(40, 0, 0, 0);
                    String stime66=json_data.getString("Groups");
                    b66.setText(stime66);
                    b66.setTextColor(Color.RED);
                    b66.setTextSize(15);
                    tr.addView(b66);

                    tv.addView(tr);


                    final View vline1 = new View(getContext());
                    vline1.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 1));
                    vline1.setBackgroundColor(Color.WHITE);
                    tv.addView(vline1);


                }

            }



        }
        catch(JSONException e)
        {
            Log.e("log_tag", "Error parsing data "+e.toString());
            Toast.makeText(getContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
        }


        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),AvailActivity.class);
                i.putExtra("Events",Eventarr);
                startActivity(i);
            }
        });


        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),AttendingActivity.class);
                startActivity(i);
            }
        });
        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("Upcoming Events");
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
        // NOTE : We changed the Uri to String.
        void onFragmentInteraction(String title);
    }


}
