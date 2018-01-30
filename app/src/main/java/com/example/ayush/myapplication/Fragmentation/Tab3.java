package com.example.ayush.myapplication.Fragmentation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ayush.myapplication.AboutUs.AboutHelpingHandActivity;
import com.example.ayush.myapplication.AboutUs.AppVersions;
import com.example.ayush.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tab3, container, false);
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        final CardView mission = view.findViewById(R.id.missioncardview);
        CardView vision = view.findViewById(R.id.visioncardview);
//        CardView goal = view.findViewById(R.id.goalcardview);
        CardView info = view.findViewById(R.id.infocardview);
        CardView app = view.findViewById(R.id.aboutappcardview);

        mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String longtext = "Mission is to focus on primary health cares with people’s participation and promoting health education among the people far from the reach of the health sources & diagnosis and providing awareness of various epidemic, endemic and pandemic diseases.";

                Intent intent = new Intent(getActivity(), AboutHelpingHandActivity.class);
                intent.putExtra("ExtraValue", longtext);
                startActivity(intent);
            }
        });

        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String longtext2 = "HAND-NEPAL (Health And Awareness Next to your Door,Nepal) स्वास्थ्य तथा सचेतनाका लागी सहयोगी , नेपाल HAND-Nepal is non-profit organisation registered under both District Administration Office and Social Welfare Council. HAND-Nepal focuses in improving rural and urban healthcare in Nepal. It aims to provide affordable, accessible & advance health care facilities at the door steps of the underprivileged communities.";
                Intent intent = new Intent(getActivity(), AboutHelpingHandActivity.class);
                intent.putExtra("ExtraValue",longtext2);
                startActivity(intent);
            }
        });

//        goal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String longtext2 = "Our Goal is to ";
//                Intent intent = new Intent(getActivity(), AboutHelpingHandActivity.class);
//                intent.putExtra("ExtraValue",longtext2);
//                startActivity(intent);
//            }
//        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AppVersions.class);
                startActivity(intent);
            }
        });

        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String longtext2 = "HamroSewa app helps to provide a better health facility service to people. It consists milestones of providing various information regarding to hospitals to users. It is useful for various patients who are willing to know the information of hospital beds like ICU, ICU-CIV, Pathology, CCU and further more departments of hospital whether they are available or not in concerned hospitals and similarly, they can also obtain the information of various testes regarding to rate. It helps to analyse the patients, according to hospitals that which of them rate is better and choose hospitals for their check-up. With the help of our application system users will be well known of the current rate of the hospital service charge and check out also other tests information. Our application provides great service on society so that users can get information for their health services and also be able to compare between various hospitals about their services, their rates and also in case of emergency they can be able to admit in hospital nearest to their area. In case of emergency they can dial call to the hospitals and get more information with their interests. We have also provided helpful way to people to find location of hospitals who are unknown about location of hospitals.";
                Intent intent = new Intent(getActivity(), AboutHelpingHandActivity.class);
                intent.putExtra("ExtraValue",longtext2);
                startActivity(intent);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }
}
