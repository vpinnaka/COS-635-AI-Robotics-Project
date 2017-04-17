package android.app.com.emilyrobot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by vinay on 4/17/17.
 */

public class FragmentMaps extends Fragment {

    private RecyclerView recyclerView;



    public FragmentMaps()
    {

    }


    public static FragmentMaps newInstance() {
        FragmentMaps fragment = new FragmentMaps();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View v= inflater.inflate(R.layout.fragment_maps, container, false);
        return  v;


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.itemslist);

        FragmentAdapter contactsAdapter = new FragmentAdapter(getActivity(),generateData());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactsAdapter);

    }


    private ArrayList<Model> generateData() {

        ArrayList<Model> contactsModals = new ArrayList<>();

        for (int i = 0; i < Mydata.nameArray.length; i++) {

            contactsModals.add(new Model(Mydata.nameArray[i],Mydata.id_[i],Mydata.drawableArray[i]));

            String m=Mydata.nameArray[i];

            System.out.print(m);

        }

        return contactsModals;
    }




}
