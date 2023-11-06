/**
 * ControladorPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package main.publicadores;

public interface ControladorPublish extends java.rmi.Remote {
    public main.publicadores.DtProfesor obtenerProfesor(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean esSocio(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] usuarioEnClase(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean logIn(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public void altaInstitucion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, main.publicadores.InstitucionDeportivaRepetidaException;
    public main.publicadores.Clase obtenerClaseR(java.lang.String arg0) throws java.rmi.RemoteException;
}
