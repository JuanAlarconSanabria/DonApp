package com.example.donapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.donapp.databinding.FragmentSignupBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SignupFragment extends Fragment {

private FragmentSignupBinding binding;
    boolean valido;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentSignupBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> list = new ArrayList<>();
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getContext(),
                 android.R.layout.simple_spinner_item, list);
        adapter.add("Seleccionar Organizacion");
        adapter.add("Crear Organizacion");
        adapter.addAll(((MainActivity)getActivity()).getOrganizations());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        valido = false;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item.equals("Crear Organizacion")) {
                    valido = false;
                    TextView textView = (TextView) getView().findViewById(R.id.textview_org_name);
                    textView.setVisibility(View.VISIBLE);
                    textView = (TextView) getView().findViewById(R.id.textview_org_des);
                    textView.setVisibility(View.VISIBLE);
                    EditText editText = (EditText) getView().findViewById(R.id.editTextNombreOrg);
                    editText.setVisibility(View.VISIBLE);
                    editText = (EditText) getView().findViewById(R.id.editTextDesOrg);
                    editText.setVisibility(View.VISIBLE);

                }
                else if (item.equals("Seleccionar Organizacion"))
                {
                    valido = false;
                    TextView textView = (TextView) getView().findViewById(R.id.textview_org_name);
                    textView.setVisibility(View.INVISIBLE);
                    textView = (TextView) getView().findViewById(R.id.textview_org_des);
                    textView.setVisibility(View.INVISIBLE);
                    EditText editText = (EditText) getView().findViewById(R.id.editTextNombreOrg);
                    editText.setVisibility(View.INVISIBLE);
                    editText = (EditText) getView().findViewById(R.id.editTextDesOrg);
                    editText.setVisibility(View.INVISIBLE);
                }
                else
                {
                    valido = true;
                    TextView textView = (TextView) getView().findViewById(R.id.textview_org_name);
                    textView.setVisibility(View.INVISIBLE);
                    textView = (TextView) getView().findViewById(R.id.textview_org_des);
                    textView.setVisibility(View.INVISIBLE);
                    EditText editText = (EditText) getView().findViewById(R.id.editTextNombreOrg);
                    editText.setVisibility(View.INVISIBLE);
                    editText = (EditText) getView().findViewById(R.id.editTextDesOrg);
                    editText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                valido = false;
            }
        });


         binding.buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = (EditText) getView().findViewById(R.id.editTextTextPersonName);
                EditText pass = (EditText) getView().findViewById(R.id.editTextTextPassword);
                if(!valido)
                {
                    EditText orgName = (EditText) getView().findViewById(R.id.editTextNombreOrg);
                    EditText orgDes = (EditText) getView().findViewById(R.id.editTextDesOrg);
                    if (orgName.getText().toString().trim().length() !=0  && orgDes.getText().toString().trim().length() != 0
                            && user.getText().toString().trim().length() != 0 && pass.getText().toString().trim().length() != 0)
                    {

                        if(!((MainActivity)getActivity()).existeUsuario(user.getText().toString()))
                        {
                            if ( !((MainActivity)getActivity()).existeOrganizacion(orgName.getText().toString().trim()))
                            {
                                ((MainActivity)getActivity()).crearUsuarioSinOrg(user.getText().toString().trim(), pass.getText().toString().trim(), orgName.getText().toString().trim(), orgDes.getText().toString().trim());


                                NavHostFragment.findNavController(SignupFragment.this)
                                        .navigate(R.id.action_Signup);
                                Snackbar.make(view, "Organización" + " " + orgName.getText().toString() + " creada", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                            else
                            {
                                Snackbar.make(view, "Organización" + " " + orgName.getText().toString() + " ya existe, por favor seleccionala", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }
                        else
                        {
                            Snackbar.make(view, "El usuario ya existe", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    }
                    else
                    {
                        Snackbar.make(view, "Los campos no pueden estar vacios", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }
                else
                {
                    if (user.getText().toString().trim().length() != 0 && pass.getText().toString().trim().length() != 0)
                    {


                        if(!((MainActivity)getActivity()).existeUsuario(user.getText().toString().trim()))
                        {
                            String orgName = spinner.getSelectedItem().toString().trim();
                            ((MainActivity)getActivity()).crearUsuarioConOrg(user.getText().toString().trim(), pass.getText().toString().trim(),orgName);
                            NavHostFragment.findNavController(SignupFragment.this)
                                    .navigate(R.id.action_Signup);

                            Snackbar.make(view, "Es correcto", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else
                        {
                            Snackbar.make(view, "El usuario ya existe", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                    else
                    {
                        Snackbar.make(view, "Los campos no pueden estar vacios", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
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