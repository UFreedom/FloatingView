package com.ufreedom.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author UFreedom
 * Date : 2016 十月 20
 */

public class SimpleFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    public static SimpleFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        View root = inflater.inflate(R.layout.fragment_simple,container,false);
        
        /*final Floating floatingView = new Floating(getActivity());

        final View normalView = root.findViewById(R.id.normalView);
        
        assert normalView != null;
        normalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(getActivity());
                textView.setBackgroundColor(Color.RED);
                textView.setText("UFreedom");
                FloatingElement builder = new FloatingBuilder().anchorView(view)
                        .floatingTransition(new TranslateFloatingTransition())
                        .target(textView).build();
                floatingView.startFloating(builder);
            }
        });


        View curveView = root.findViewById(R.id.curveView);

        assert curveView != null;
        curveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                linearLayout.addView(imageView);

                TextView textView = new TextView(getActivity());
                textView.setBackgroundColor(Color.RED);
                textView.setText("UFreedom");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                linearLayout.addView(textView,layoutParams);

                FloatingElement builder = new FloatingBuilder().anchorView(view)
                        .target(linearLayout).floatingTransition(new CurveFloatingPathTransition()).build();
                floatingView.startFloating(builder);

            }
        });

        View scaleView = root.findViewById(R.id.scaleView);


        assert scaleView != null;
        scaleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = new TextView(getActivity());
                textView.setBackgroundColor(Color.RED);
                textView.setText("UFreedom");
                FloatingElement builder = new FloatingBuilder().anchorView(view)
                        .target(textView).build();
                floatingView.startFloating(builder);
            }
        });*/
        return root;
    }
}
