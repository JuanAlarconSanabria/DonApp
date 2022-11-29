package com.example.donapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.donapp.databinding.FragmentShowEventsBinding;

import java.util.ArrayList;

public class ShowEventsFragment extends Fragment {

private FragmentShowEventsBinding binding;
String event;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
      binding = FragmentShowEventsBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        ArrayList<String> events = ((MainActivity)getActivity()).getCurrentEventsStrings();
        for(int i = 0; i < events.size(); i++)
        {
            event = events.get(i);
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            TextView rowTextView = new TextView(getContext());
            rowTextView.setText(events.get(i));
            rowTextView.setTextColor(Color.parseColor("#000000"));
            Button button = new Button(getContext());
            button.setText("Más");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).setCurrentEvent(event.split("-")[0].trim());

                    NavHostFragment.findNavController(ShowEventsFragment.this)
                            .navigate(R.id.info);

                }
            });
            layout.addView(rowTextView);
            layout.setGravity(Gravity.CENTER);
            layout.addView(button);
            binding.layout.addView(layout);


        }




       binding.buttonRes.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(ShowEventsFragment.this)
                        .navigate(R.id.volver);

            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}