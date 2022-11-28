package com.example.plisya;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;


public class JSON extends AppCompatActivity {

    public static String crearJson(String JNombre , String JFecha , String JArtista , String JUsuario , String JMail , int JEdad , int JBoleta , boolean JGenero , boolean JAnimal , String Password ) {
        MyInfo datos = new MyInfo();
        Gson gson = new Gson();
        Des myDes = new Des();

        datos.setNombre(JNombre);
        datos.setFecha(JFecha);
        datos.setArtista(JArtista);
        datos.setUsuario(JUsuario);
        datos.setMail(JMail);
        datos.setEdad(JEdad);
        datos.setBoletar(JBoleta);
        datos.setGenero(JGenero);
        datos.setAnimal(JAnimal);
        datos.setPassword(Password);

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static MyInfo leerJson(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        MyInfo datos = gson.fromJson(myDes.desCifrar(textoJson), MyInfo.class);

        return datos;
    }

    public static String crearJsonCuenta(String Name , String Password , int Image) {
        MyCuenta datos = new MyCuenta();
        Gson gson = new Gson();
        Des myDes = new Des();

        datos.setNameCuenta(Name);
        datos.setPassCuenta(Password);
        datos.setImage(Image);

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static MyCuenta leerJsonCuenta(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        MyCuenta datos = gson.fromJson(myDes.desCifrar(textoJson), MyCuenta.class);

        return datos;
    }

    public static String crearJsonCorreo(String Mail , String HTML) {
        Correo datos = new Correo();
        Gson gson = new Gson();

        datos.setMailCorreo(Mail);
        datos.setHTMLCorreo(HTML);

        String nuevojson = gson.toJson(datos);

        return nuevojson;
    }
}
