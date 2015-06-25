package bbva.delivery.services.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RequestTransferirArchivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7838119333599216113L;
	
	private String idEntrega;
	
	private String archivo;

	public String getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(String idEntrega) {
		this.idEntrega = idEntrega;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
}
