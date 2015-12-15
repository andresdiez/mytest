package com.diez.andres.mytest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by HectorAndres on 12/14/2015.
 */
public class TheList extends ListFragment implements AdapterView.OnItemClickListener {

    ProgressDialog progress;
    AdapterList adapter;
    ArrayList<Row> rows;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);
        progress = ProgressDialog.show(getActivity(), "Searching",
                "Please wait while we load data", true);
        return view;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Screen2 activity = (Screen2)getActivity();
        String sear = activity.getData();
        ((Screen2) getActivity()).setActionBarTitle(sear);

        rows = new ArrayList<Row>();
        adapter=new AdapterList(getActivity(),rows);
        setListAdapter(adapter);

        BackTask1 bt=new BackTask1(adapter,progress);
        bt.execute(sear);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        view.setSelected(true);


        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        ThePicture fragment2 = new ThePicture();
        fragment2.setindex(position);
        fragmentTransaction2.addToBackStack("pic");
        fragmentTransaction2.hide(TheList.this);
        fragmentTransaction2.add(android.R.id.content, fragment2);
        fragmentTransaction2.commit();



    }




}
