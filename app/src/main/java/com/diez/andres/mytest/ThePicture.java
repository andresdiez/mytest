package com.diez.andres.mytest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by HectorAndres on 12/14/2015.
 */
public class ThePicture extends Fragment {


    private int index;
    TheList articleFrag;
    CustomImageVIew img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.picture, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        articleFrag = (TheList)
                getFragmentManager().findFragmentById(R.id.thelistfragment);

        img=(CustomImageVIew)getView().findViewById(R.id.view2);
        img.setImageBitmap(articleFrag.rows.get(index).hometown);
        settitle();


        Button bn=(Button)getView().findViewById(R.id.button4);
        bn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                gonext();
            }
        });
        Button bp=(Button)getView().findViewById(R.id.button2);
        bp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                goprev();
            }
        });
        Button bdel=(Button)getView().findViewById(R.id.button3);
        bdel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                deletepict();
            }
        });

    }

    public void setindex(int index) {
        this.index=index;

    }
    public void settitle(){

        ((Screen2) getActivity()).setActionBarTitle((index+1)+" of "+articleFrag.rows.size());
    }

    public void gonext(){
        index++;
        if (index==articleFrag.rows.size()){
            index=0;
        }
        img.setImageBitmap(articleFrag.rows.get(index).hometown);
        settitle();
        articleFrag.getListView().setItemChecked(index,true);

    }

    public void goprev(){
        index--;
        if (index==-1){
            index=articleFrag.rows.size()-1;
        }
        img.setImageBitmap(articleFrag.rows.get(index).hometown);
        settitle();
        articleFrag.getListView().setItemChecked(index,true);

    }
    public void deletepict(){
        articleFrag.rows.remove(index);
        articleFrag.adapter.notifyDataSetChanged();
        img.setImageBitmap(articleFrag.rows.get(index).hometown);

    }
}
