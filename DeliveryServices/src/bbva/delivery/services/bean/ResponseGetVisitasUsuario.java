package bbva.delivery.services.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ResponseGetVisitasUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7777116188101592030L;
	
	private Tx tx;
	
	private VisitasUsuario visitasUsuario;
	
	public Tx getTx() {
		return tx;
	}
	
	public void setTx(Tx tx) {
		this.tx = tx;
	}
	@JsonProperty("rpta")
	public VisitasUsuario getVisitasUsuario() {
		return visitasUsuario;
	}
	
	public void setVisitasUsuario(VisitasUsuario visitasUsuario) {
		this.visitasUsuario = visitasUsuario;
	}
}
