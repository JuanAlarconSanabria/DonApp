package com.example.donapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.donapp.databinding.FragmentSecondBinding;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;

String department;
String city;
String other = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentSecondBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> list = new ArrayList<>();
        Spinner spinner_dep = binding.spinnerDep;
        ArrayAdapter<CharSequence> adapter_dep = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, list);
        ArrayList<String> deps = ((MainActivity)getActivity()).getDepartmentsStrings();
        if(deps.size()>0)
        {
            adapter_dep.add("Buscar en Todos");
            adapter_dep.addAll(deps);
        }
        else
        {
            adapter_dep.add("No hay eventos disponibles");
        }
        adapter_dep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dep.setAdapter(adapter_dep);
        spinner_dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                department = adapterView.getItemAtPosition(position).toString();
                if(!department.equals("Buscar en Todos") && !department.equals("No hay eventos disponibles"))
                {
                    ArrayList<String> list_cit = new ArrayList<>();
                    Spinner spinner_cit = binding.spinnerCit;
                    ArrayAdapter<CharSequence> adapter_cit = new ArrayAdapter(getContext(),
                            android.R.layout.simple_spinner_item, list_cit);
                    adapter_cit.add("Buscar en todas");
                    adapter_cit.addAll(((MainActivity)getActivity()).getCitiesStrings(department));
                    adapter_cit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_cit.setAdapter(adapter_cit);
                    binding.textviewCit.setVisibility(View.VISIBLE);
                    spinner_cit.setVisibility(View.VISIBLE);
                    spinner_cit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            city = adapterView.getItemAtPosition(position).toString();
                            if(!city.equals("Buscar en todas"))
                            {
                                ArrayList<String> others = ((MainActivity)getActivity()).getOthersStrings(city);

                                if(others.size()>0)
                                 {
                                    Spinner spinner_oth = binding.spinnerOth;
                                    ArrayAdapter<CharSequence> adapter_oth = new ArrayAdapter(getContext(),
                                         android.R.layout.simple_spinner_item, list);
                                    adapter_oth.add("Buscar en todas");
                                    adapter_oth.addAll(others);
                                    adapter_oth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinner_oth.setAdapter(adapter_oth);
                                    binding.textviewOth.setVisibility(View.VISIBLE);
                                    spinner_oth.setVisibility(View.VISIBLE);
                                    spinner_oth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                                            other = adapterView.getItemAtPosition(pos).toString();
                                            if(!other.equals("Buscar en todas"))
                                            {
                                                binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                        ((MainActivity)getActivity()).setEventsByOther(other);

                                                    }
                                                });
                                            }
                                            else
                                            {
                                                ((MainActivity)getActivity()).setEventsByCity(city);
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });

                                 }
                                else
                                {
                                    ((MainActivity)getActivity()).setEventsByCity(city);
                                }
                            }
                            else
                            {
                                binding.textviewOth.setVisibility(View.INVISIBLE);
                                binding.spinnerOth.setVisibility(View.INVISIBLE);
                                ((MainActivity)getActivity()).setEventsByDepartment(department);

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
                else if (department.equals("Buscar en Todos"))
                {
                    binding.textviewCit.setVisibility(View.INVISIBLE);
                    binding.spinnerCit.setVisibility(View.INVISIBLE);
                    binding.textviewOth.setVisibility(View.INVISIBLE);
                    binding.spinnerOth.setVisibility(View.INVISIBLE);
                    ((MainActivity)getActivity()).setAllEvents();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing
            }
        });
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.buscar);
            }
        });

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}