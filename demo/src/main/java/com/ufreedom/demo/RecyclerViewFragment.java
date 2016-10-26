package com.ufreedom.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufreedom.floatingview.Floating;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;

import java.util.ArrayList;
import java.util.List;

/**
 * Author UFreedom
 * Date : 2016 十月 20
 */

public class RecyclerViewFragment extends Fragment {

    public static RecyclerViewFragment newInstance() {

        Bundle args = new Bundle();
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rv,container,false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new SimpleAdapter(generateTestData()));
        return rootView;
        
    }
    
    class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder>{

        private List<SimpleData> simpleDatas;

        public SimpleAdapter(List<SimpleData> simpleDatas) {
            this.simpleDatas = simpleDatas;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item,parent,false);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.bindData(simpleDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return simpleDatas.size();
        }
    }
    
    class SimpleViewHolder extends RecyclerView.ViewHolder{

        private TextView indexTextView;
        private Floating floating;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            floating = new Floating(getActivity());

            indexTextView = (TextView) itemView.findViewById(R.id.indexTextView);
            indexTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = new TextView(getActivity());
                    textView.setBackgroundColor(Color.RED);
                    textView.setText("UFreedom");
                    FloatingElement builder = new FloatingBuilder()
                            .anchorView(v)
                            .target(textView)
                            .floatingTransition(new TranslateFloatingTransition())
                            .build();
                    floating.startFloating(builder);
                }
            });
        }
        
        public void bindData(SimpleData simpleData){
            indexTextView.setText("Index : "+simpleData.index);
        }
        
    }

    public   List<SimpleData> generateTestData(){
        List<SimpleData> simpleDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            simpleDatas.add(new SimpleData(i));
        }
        return simpleDatas;
    }
    
    class SimpleData{
        private int index;

        public SimpleData(int index) {
            this.index = index;
        }
    }
}
