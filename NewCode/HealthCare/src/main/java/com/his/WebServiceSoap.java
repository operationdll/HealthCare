/**
 * WebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.his;

public interface WebServiceSoap extends java.rmi.Remote {
    public java.lang.String HIS_Interface(java.lang.String tradeCode, java.lang.String inputParameter) throws java.rmi.RemoteException;
    public java.lang.String HIPMessageServer(java.lang.String action, java.lang.String message) throws java.rmi.RemoteException;
    public java.lang.String TCM_HIS_05(java.lang.String inputParameter) throws java.rmi.RemoteException;
    public java.lang.String TCM_HIS_06(java.lang.String inputParameter) throws java.rmi.RemoteException;
    public java.lang.String TCM_AE_04(java.lang.String inputParameter) throws java.rmi.RemoteException;
}
