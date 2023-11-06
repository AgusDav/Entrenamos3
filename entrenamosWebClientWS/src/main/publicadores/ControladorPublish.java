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
    public java.lang.String[] clasesProfe(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean logIn(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public void altaInstitucion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, main.publicadores.InstitucionDeportivaRepetidaException;
    public java.lang.String[] listarInstitutos() throws java.rmi.RemoteException;
    public main.publicadores.DtInstitucionDeportiva obtenerInstitucion(java.lang.String arg0) throws java.rmi.RemoteException;
    public void modificarInstitucion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public java.lang.String[] listarActividadesDeportivas(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] listarProfesores(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] listarSocios(java.lang.String arg0) throws java.rmi.RemoteException;
    public void modificarUsuario(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.util.Calendar arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6) throws java.rmi.RemoteException;
    public java.lang.String[] listarClases2() throws java.rmi.RemoteException;
    public java.lang.String[] listarClases(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public main.publicadores.DtUsuario obtenerUsuario(java.lang.String arg0) throws java.rmi.RemoteException;
    public main.publicadores.Usuario obtenerUsuarioReal(java.lang.String arg0) throws java.rmi.RemoteException;
    public main.publicadores.DtClase obtenerClase(java.lang.String arg0) throws java.rmi.RemoteException;
    public main.publicadores.Clase obtenerClaseR(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String obtenerInstitucionActividad(java.lang.String arg0) throws java.rmi.RemoteException;
    public main.publicadores.DtActividadDeportiva obtenerActividad(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String[] listarUsuarios() throws java.rmi.RemoteException;
    public main.publicadores.ActividadDeportiva obtenerActividadR(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerRankingActividadesDeportivas() throws java.rmi.RemoteException;
    public java.lang.String[] obtenerTodasActividadesDeportivas() throws java.rmi.RemoteException;
    public void modificarActividadDeportiva(java.lang.String arg0, java.lang.String arg1, int arg2, float arg3) throws java.rmi.RemoteException;
}
