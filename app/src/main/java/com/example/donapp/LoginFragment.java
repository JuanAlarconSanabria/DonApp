package com.example.donapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.donapp.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment {

private FragmentLoginBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
      binding = FragmentLoginBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editUser = (EditText) getView().findViewById(R.id.editTextTextPersonName);
                String user = editUser.getText().toString();
                EditText editPass = (EditText) getView().findViewById(R.id.editTextTextPassword);
                String pass = editPass.getText().toString();
                boolean bool = ((MainActivity)getActivity()).login(user, pass);

                if (bool)
                {
                    Snackbar.make(view, "Es correcto", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    NavHostFragment.findNavController(LoginFragment.this)
                           .navigate(R.id.action_Login);
                }
                else
                {
                    Snackbar.make(view, "El usuario o la contraseña son incorrectos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }



            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}