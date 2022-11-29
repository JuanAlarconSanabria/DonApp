package com.example.donapp;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.donapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.example.donapp.back.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
private ActivityMainBinding binding;
private Main main;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        main = new Main();

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        testCrearUsuario();
    }

    public void testCrearUsuario()
    {
        Organization org = main.crearOrganizacion("Fundación Gabo", "Realiza talleres, premios, becas, publicaciones y lidera iniciativas para el estímulo, calidad y proyección del periodismo en Iberoamérica");
        Organization org2 = main.crearOrganizacion("Fundación Pies Descalzos", "Tiene el objetivo de ayudar a los niños pobres y víctimas del conflicto colombiano");
        Organization org3 = main.crearOrganizacion("Fundación Belcorp", "Tiene el objetivo impulsar potencial de la mujer para transformar la sociedad. A través de programas que fortalecen su liderazgo y capacidades emprendedoras, buscamos contribuir a su empoderamiento económico y a la construcción de una sociedad más equitativa.");
        Organization org4 = main.crearOrganizacion("Fundación Gaia Amazonas", "rganización no gubernamental colombiana, con sede en Bogotá, que trabaja desde hace más que 30 años de la mano de los pueblos indígenas amazónicos por la protección biológica y cultural del bioma");

        main.crearUsuario("david", "pass", org);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            main.crearEvento( LocalDateTime.of(2023, Month.JULY, 28, 15, 30),"Bogotá", "Bogotá", null, "Carrera 24 # 123 - 456", "Donacion de libros", "Donacion de libros de historia");
            main.crearEvento( LocalDateTime.of(2024, Month.JULY, 20, 10, 30),"Cundinamarca", "Sopó", null, "Carrera 12 # 456 - 123", "Donacion de cuadernos", "Donacion de cuadernos");
            main.crearUsuario("juan", "123", org2);
            main.crearEvento( LocalDateTime.of(2024, Month.JULY, 20, 10, 30),"Bogotá", "Bogotá", null, "Calle 85 # 18-32", "Programa Creando juntos", "Desde Creando Juntos incorporamos el Aprendizaje Basado en Proyectos – ABP mediado por tecnología para favorecer el desarrollo de las competencias del siglo XXI en estudiantes mejorando, además, su motivación hacia el aprendizaje y sus desempeños.");
            main.crearUsuario("JFK", "123", org3);
            main.crearEvento( LocalDateTime.of(2024, Month.JULY, 20, 10, 30),"Bogotá", "Bogotá", null, "Calle 113 # 7 – 80 Oficina 702", "Programa Mujeres Sin Limites", "Fortalecer el liderazgo de mujeres emprendedoras y acelerar el crecimiento de sus negocios a través de capacitación");
            main.crearUsuario("Pineapple", "123", org4);
            main.crearEvento( LocalDateTime.of(2024, Month.JULY, 20, 10, 30),"Amazonas", "Leticia", null, "Cra. 11 #9-43", "Programa jornada de concientización", "Concientizar a la población acerca de la importancia de la protección de las comunidades Indígenas");
        }

    }

    public boolean login(String name, String pass)
    {

        return main.login(name, pass);
    }

    public ArrayList<String> getDepartmentsStrings()
    {
        return main.getDepartmentsString();
    }
    public ArrayList<String> getCitiesStrings(String dep)
    {
        return main.getCitiesString(dep);
    }
    public ArrayList<String> getOthersStrings(String city)
    {
        return main.getOthersString(city);
    }

    public void setEventsByDepartment(String dep)
    {
        main.setCurrentEventsDep(dep);
    }
    public void setEventsByCity(String city)
    {
        main.setCurrentEventsCity(city);
    }
    public void setEventsByOther(String other)
    {
        main.setCurrentEventsOther(other);
    }
    public void setAllEvents()
    {
        main.setCurrentEventsAll();
    }
    public String[] getLastEventInfo()
    {
        return main.getLastEventInfo();
    }
    public boolean existeUsuario(String name)
    {
        return main.existeUsuario(name);
    }

    public void crearEvento(LocalDateTime date, String department, String city, String other, String address, String name, String description)
    {
        main.crearEvento(date,department,city,other,address,name,description);
    }
    public void crearUsuarioSinOrg(String name, String pass, String orgName, String orgDes)
    {
        Organization org = main.crearOrganizacion(orgName, orgDes);
        main.crearUsuario(name, pass, org);
    }
    public void crearUsuarioConOrg(String name, String pass, String orgName)
    {
        Organization org = main.encontrarOrganizacion(orgName);
        main.crearUsuario(name, pass, org);
    }
    public boolean existeOrganizacion(String name)
    {
        if(main.encontrarOrganizacion(name) != null)
        {
            return true;
        }
        return false;
    }
    public String[] getCurrentEventInfo()
    {
        return main.getCurrentEventInfo();
    }
    public void setCurrentEvent(String name)
    {
        main.setCurrentEvent(name);
    }
    public ArrayList<String> getCurrentEventsStrings()
    {
        return main.getCurrentsStrings();
    }
    public ArrayList<String> getOrganizations()
    {
        return main.getOrganizationsString();
    }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}