package com.his;

public class WebServiceSoapProxy implements WebServiceSoap {
	private String _endpoint = null;
	private WebServiceSoap webServiceSoap = null;
	private String WebServiceSoap_address = "";

	public void setWebServiceSoap_address(String webServiceSoap_address) {
		WebServiceSoap_address = webServiceSoap_address;
	}

	public WebServiceSoapProxy() {
		_initWebServiceSoapProxy();
	}

	public WebServiceSoapProxy(String endpoint) {
		_endpoint = endpoint;
		_initWebServiceSoapProxy();
	}

	private void _initWebServiceSoapProxy() {
		try {
			webServiceSoap = (new WebServiceLocator(WebServiceSoap_address)).getWebServiceSoap();
			if (webServiceSoap != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address",
							_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) webServiceSoap)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (webServiceSoap != null)
			((javax.xml.rpc.Stub) webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public WebServiceSoap getWebServiceSoap() {
		if (webServiceSoap == null)
			_initWebServiceSoapProxy();
		return webServiceSoap;
	}

	public java.lang.String HIS_Interface(java.lang.String tradeCode, java.lang.String inputParameter)
			throws java.rmi.RemoteException {
		if (webServiceSoap == null)
			_initWebServiceSoapProxy();
		return webServiceSoap.HIS_Interface(tradeCode, inputParameter);
	}

	public java.lang.String HIPMessageServer(java.lang.String action, java.lang.String message)
			throws java.rmi.RemoteException {
		if (webServiceSoap == null)
			_initWebServiceSoapProxy();
		return webServiceSoap.HIPMessageServer(action, message);
	}

	public java.lang.String TCM_HIS_05(java.lang.String inputParameter) throws java.rmi.RemoteException {
		if (webServiceSoap == null)
			_initWebServiceSoapProxy();
		return webServiceSoap.TCM_HIS_05(inputParameter);
	}

	public java.lang.String TCM_HIS_06(java.lang.String inputParameter) throws java.rmi.RemoteException {
		if (webServiceSoap == null)
			_initWebServiceSoapProxy();
		return webServiceSoap.TCM_HIS_06(inputParameter);
	}

	public java.lang.String TCM_AE_04(java.lang.String inputParameter) throws java.rmi.RemoteException {
		if (webServiceSoap == null)
			_initWebServiceSoapProxy();
		return webServiceSoap.TCM_AE_04(inputParameter);
	}

}