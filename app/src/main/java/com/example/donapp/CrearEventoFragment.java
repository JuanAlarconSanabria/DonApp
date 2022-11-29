package com.example.donapp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.donapp.databinding.FragmentCrearEventoBinding;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.util.Calendar;

public class CrearEventoFragment extends Fragment {

private FragmentCrearEventoBinding binding;
    int year = 0;
    int month = 0;
    int day = 0;
    int hour = 0;
    int minute = 0;
    LocalDateTime localDateTime;
    String name;
    String des;
    String dep;
    String ciu;
    String op;
    String dir;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
      binding = FragmentCrearEventoBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonTextDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                DatePicker datePicker = (DatePicker) getView().findViewById(R.id.date_picker);
                datePicker.setVisibility(View.VISIBLE);
                binding.buttonTextDate.setVisibility(View.INVISIBLE);
                binding.buttonTextTime.setVisibility(View.INVISIBLE);
                binding.textviewLoc.setVisibility(View.INVISIBLE);
                binding.textviewLoc.setVisibility(View.INVISIBLE);
                binding.editTextDep.setVisibility(View.INVISIBLE);
                binding.editTextCiu.setVisibility(View.INVISIBLE);
                binding.textviewLocOp.setVisibility(View.INVISIBLE);
                binding.editTextOp.setVisibility(View.INVISIBLE);
                binding.textviewLocDir.setVisibility(View.INVISIBLE);
                binding.editTextDir.setVisibility(View.INVISIBLE);


                datePicker.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        year = datePicker.getYear();
                        month = datePicker.getMonth();
                        day = datePicker.getDayOfMonth();
                        datePicker.setVisibility(View.GONE);
                        binding.buttonTextDate.setVisibility(View.VISIBLE);
                        binding.buttonTextTime.setVisibility(View.VISIBLE);
                        binding.textviewLoc.setVisibility(View.VISIBLE);
                        binding.textviewLoc.setVisibility(View.VISIBLE);
                        binding.editTextDep.setVisibility(View.VISIBLE);
                        binding.editTextCiu.setVisibility(View.VISIBLE);
                        binding.textviewLocOp.setVisibility(View.VISIBLE);
                        binding.editTextOp.setVisibility(View.VISIBLE);
                        binding.textviewLocDir.setVisibility(View.VISIBLE);
                        binding.editTextDir.setVisibility(View.VISIBLE);
                        binding.buttonTextDate.setText(day + "-" + month + "-" + year);
                    }
                });
            }
        });

        binding.buttonTextTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TimePicker timePicker = (TimePicker) getView().findViewById(R.id.time_picker);
                timePicker.setVisibility(View.VISIBLE);
                binding.buttonTextDate.setVisibility(View.INVISIBLE);
                binding.buttonTextTime.setVisibility(View.INVISIBLE);
                binding.textviewLoc.setVisibility(View.INVISIBLE);
                binding.textviewLoc.setVisibility(View.INVISIBLE);
                binding.editTextDep.setVisibility(View.INVISIBLE);
                binding.editTextCiu.setVisibility(View.INVISIBLE);
                binding.textviewLocOp.setVisibility(View.INVISIBLE);
                binding.editTextOp.setVisibility(View.INVISIBLE);
                binding.textviewLocDir.setVisibility(View.INVISIBLE);
                binding.editTextDir.setVisibility(View.INVISIBLE);
                timePicker.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hour = timePicker.getHour();
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            minute = timePicker.getMinute();
                        }
                        timePicker.setVisibility(View.GONE);
                        binding.buttonTextDate.setVisibility(View.VISIBLE);
                        binding.buttonTextTime.setVisibility(View.VISIBLE);
                        binding.textviewLoc.setVisibility(View.VISIBLE);
                        binding.textviewLoc.setVisibility(View.VISIBLE);
                        binding.editTextDep.setVisibility(View.VISIBLE);
                        binding.editTextCiu.setVisibility(View.VISIBLE);
                        binding.textviewLocOp.setVisibility(View.VISIBLE);
                        binding.editTextOp.setVisibility(View.VISIBLE);
                        binding.textviewLocDir.setVisibility(View.VISIBLE);
                        binding.editTextDir.setVisibility(View.VISIBLE);
                        binding.buttonTextTime.setText(hour + ":" + minute);
                    }
                });
            }

        });

        binding.buttonCreate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name = binding.editTextNombreEv.getText().toString().trim();
                des = binding.editTextDesEv.getText().toString().trim();
                dep= binding.editTextDep.getText().toString().trim();
                ciu = binding.editTextCiu.getText().toString().trim();
                op = binding.editTextOp.getText().toString().trim();
                dir = binding.editTextDir.getText().toString().trim();

                if(year == 0 || hour == 0 || name.length() == 0 || des.length() == 0 || dep.length() == 0 || ciu.length() == 0
                    || dir.length() == 0)
                {
                    Snackbar.make(view, "Aún faltan campos por llenar", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                             localDateTime = LocalDateTime.of(year, month,day,hour, minute);
                    }
                    if(op.length()==0)
                    {
                        String depa = dep.substring(0, 1).toUpperCase() + dep.substring(1).toLowerCase();
                        String ciud = ciu.substring(0, 1).toUpperCase() + ciu.substring(1).toLowerCase();
                        ((MainActivity)getActivity()).crearEvento(localDateTime,depa,ciud,null,dir,name, des);
                        Snackbar.make(view, name + " Creado", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        NavHostFragment.findNavController(CrearEventoFragment.this)
                                .navigate(R.id.action_crear);
                    }
                    else
                    {
                        String depa = dep.substring(0, 1).toUpperCase() + dep.substring(1).toLowerCase();
                        String ciud = ciu.substring(0, 1).toUpperCase() + ciu.substring(1).toLowerCase();
                        String opc = op.substring(0, 1).toUpperCase() + op.substring(1).toLowerCase();
                        ((MainActivity)getActivity()).crearEvento(localDateTime,depa,ciud,opc,dir,name, des);
                        Snackbar.make(view, name + " Creado", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        NavHostFragment.findNavController(CrearEventoFragment.this)
                                .navigate(R.id.action_crear);
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