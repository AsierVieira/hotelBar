package donamayor.hotelbar.model;

import javafx.beans.property.*;

import java.awt.image.BufferedImage;

public class Producto {

    private int id_producto;
    private  String nombre_es;

    private  String nombre_en;
    private  double precio;

    private  String tipo;
    private  String subtipo;
    private  String descripcion_es;

    private String descripcion_en;

    //private BufferedImage foto;



    public Producto(){

    }



    public Producto(int id_producto, String nombre_es, String nombre_en, double precio, String tipo, String subtipo, String descripcion_es, String descripcion_en/*,BufferedImage foto*/) {
       this.id_producto = id_producto;
        this.nombre_es = nombre_es;
        this.nombre_en = nombre_en;
        this.precio = precio;
        this.tipo = tipo; // vino, bebida, snack
        this.subtipo = subtipo;
        this.descripcion_es = descripcion_es;
        this.descripcion_en = descripcion_en;
        //this.foto = foto;

    }

    //getters y setters

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_es() {
        return nombre_es;
    }



    public void setNombre_es(String nombre_es) {
        this.nombre_es=nombre_es;
    }

    public String getNombre_en() {
        return nombre_en;
    }



    public void setNombre_en(String nombre_en) {
        this.nombre_en=nombre_en;
    }

    public double getPrecio() {
        return precio;
    }



    public void setPrecio(double precio) {
        this.precio=precio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo=(tipo);
    }
    public String getSubtipo() {
        return subtipo;
    }



    public void setSubtipo(String subtipo) {
        this.subtipo=(subtipo);
    }

    public String getDescripcion_es() {
        return descripcion_es;
    }



    public void setDescripcion_es(String descripcion_es) {
        this.descripcion_es=(descripcion_es);
    }



    public String getDescripcion_en() {
        return descripcion_en;
    }

    public void setDescripcion_en(String descripcion_en) {
        this.descripcion_en=descripcion_en;
    }

   // public BufferedImage getFoto() {
     //   return this.foto;
   // }

    //public void setFoto(BufferedImage foto) {
      //  this.foto = foto;
    //}







}

