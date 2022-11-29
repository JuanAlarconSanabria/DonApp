package com.example.donapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.donapp.databinding.FragmentResBinding;
import com.google.android.material.snackbar.Snackbar;

public class ResFragment extends Fragment {

private FragmentResBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
      binding = FragmentResBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] res =  ((MainActivity)getActivity()).getLastEventInfo();
        binding.textviewName.setText(res[0]);
        binding.textviewDes.setText(res[1]);
        if (res[4] == null)
        {
            binding.textviewLoc.setText(res[2] + ", " + res[3] + ", " + res[5] + ", ");
        }
        else
        {
            binding.textviewLoc.setText(res[2] + ", " + res[3]+ ", " + res[4] + ", " + res[5] + ", ");
        }
        binding.textviewDate.setText(res[6]);
        binding.textviewOrg.setText(res[7]);

        binding.buttonRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(ResFragment.this)
                        .navigate(R.id.action_volver);

            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}