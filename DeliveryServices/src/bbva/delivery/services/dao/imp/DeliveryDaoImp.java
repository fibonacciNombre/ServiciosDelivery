package bbva.delivery.services.dao.imp;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.services.bean.Courier;
import bbva.delivery.services.bean.Delivery;
import bbva.delivery.services.bean.RequestChangeEstadoRegistro;
import bbva.delivery.services.bean.RequestGetVisitasUsuario;
import bbva.delivery.services.bean.RequestInformarEntregaCourier;
import bbva.delivery.services.bean.RequestValidarCourier;
import bbva.delivery.services.bean.Usuario;
import bbva.delivery.services.bean.ValidarCourier;
import bbva.delivery.services.bean.VisitasUsuario;
import bbva.delivery.services.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.services.dao.DeliveryDao;

import com.rimac.sas.utiles.comunes.JdbcHelper;



@Repository("deliveryDao")
public class DeliveryDaoImp extends JdbcDaoBase implements DeliveryDao {
	
	private static DeliveryDaoImp instance;
	
	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public DeliveryDaoImp() {
		super();
	}
	
	public static DeliveryDaoImp getInstance() {
	    if (instance == null) {
		  instance = new DeliveryDaoImp();
	    }
	    return instance;
	}
	
	
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("dao ok");

	}	
	
	public void validarUsuarioToken(Usuario usuario){
		
		usuario.setUsuario("too");
		usuario.setContrasena("old");
		
	}
	
	public void obtEstadoUsuarioWeb(String usuarioWeb) {
		 
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out		= null;

		logger.info("Ejecutando metodo obtEstadoUsuarioWeb");
		System.out.println("Ejecutando metodo obtEstadoUsuarioWeb");    
		call = new SimpleJdbcCall(getJdbcTemplate()).
//					withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
//					withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
					withSchemaName("esquema").
					withCatalogName("paquete").
					withProcedureName("SP_OBT_STS_USRWEB").
					withoutProcedureColumnMetaDataAccess().
					declareParameters(new SqlParameter("a_idetercero", OracleTypes.VARCHAR),
									  new SqlOutParameter("a_stsbloq", OracleTypes.VARCHAR),
									  new SqlOutParameter("a_num_visitas", Types.INTEGER),
									  new SqlOutParameter("a_idpstsusuario", Types.VARCHAR),
									  new SqlOutParameter("a_num_intentos", Types.INTEGER));
		  
		  in = new MapSqlParameterSource().
					addValue("a_idetercero", "");
		  		  
		  out = call.execute(in);      
	
//		  usuarioWeb.setIndbloqueo((String) out.get("a_stsbloq"));
//		  usuarioWeb.setNumvisitas((Integer) out.get("a_num_visitas"));
//		  usuarioWeb.setIdpstsusuario((String) out.get("a_idpstsusuario"));
//		  usuarioWeb.setNumintentos((Integer) out.get("a_num_intentos"));
		  
		  logger.info("Ejecutando metodo obtEstadoUsuarioWeb");
		  System.out.println("Ejecutando metodo obtEstadoUsuarioWeb");  
	  }
	
	public boolean validarUsuario(Usuario usuario) throws Exception{
		
		if ("android".equals(usuario.getCodusuario()) && "pepito123".equals(usuario.getContrasena()) ){
			return true;
		}else{
			return false;
		}
			
	}
	//trae un usuario
	@SuppressWarnings("unchecked")
	public Usuario getUsuario(Usuario usuario){
		
		System.out.println("INI: Ejecutando metodo getUsuario");
		List<Usuario> usr = null;
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "PQ_DEL_SERVICIOS", "sp_obt_usuario_servicio");
		
		JdbcHelper.setInOutParameter(call,in,"a_codusuario",Types.VARCHAR,usuario.getCodusuario());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Usuario.class);
		
		out = call.execute(in);
		
		
		usr = (List<Usuario>) out.get("a_cursor");
		
		System.out.println("FIN: Ejecutando metodo getUsuario");
		
		return usr.get(0);
		
		
//		Usuario j = new Usuario();
//		j.setContrasena("en2wrf7xCzW4AauuAHCgvQ==");
//		j.setCodusuario("Android");
//		return j;
	}
	
	@SuppressWarnings("unchecked")
	public List<Delivery> lstDelivery(Delivery param){
		System.out.println("INI: Ejecutando metodo lstDelivery");
		List<Delivery> lista = null;
		
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "pq_del_courier", "sp_lst_delivery");
		
		JdbcHelper.setInParameter(call, in, "a_nrodocumentocli", OracleTypes.VARCHAR, param.getNrodocumentocli());
		JdbcHelper.setInParameter(call, in, "a_nombrescli", OracleTypes.VARCHAR, param.getNombrescli());
		JdbcHelper.setInParameter(call, in, "a_nrodocumentocou", OracleTypes.VARCHAR, param.getNrodocumentocli());
		JdbcHelper.setInParameter(call, in, "a_nombrecou", OracleTypes.VARCHAR, param.getNombrescli());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Delivery.class);
		
		out = call.execute(in);
		
		lista = (List<Delivery>) out.get("a_cursor");
		System.out.println("FIN: Ejecutando metodo lstDelivery");
		return lista;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Delivery> lstDelivery(Delivery param) {
//		
//		List<Delivery> lista = null; 
//		MapSqlParameterSource in = null;
//
//		SimpleJdbcCall call = null;
//		Map<String, Object> out = null;
//		in = new MapSqlParameterSource();
//
//		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
//				resources.getString("BBVA"),
//				resources.getString("pq_del_courier"),
//				"sp_lst_delivery");
// 
//		 
//		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Delivery.class);
//		
//
//		out = call.execute(in);
//		lista = (List<Delivery>) out.get("a_cursor");
//		 
//		return lista;​
//	}
	
	@SuppressWarnings("unchecked")
	public List<Courier> obtenerListaCourier(){
		System.out.println("INI: Ejecutando metodo obtenerListaCourier");
		List<Courier> lista = null;
		
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "pq_del_servicios", "sp_lst_courier_servicio");
		
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Courier.class);
		
		out = call.execute(in);
		
		lista = (List<Courier>) out.get("a_cursor");
		System.out.println("FIN: Ejecutando metodo obtenerListaCourier");
		return lista;
	}
	
	public Usuario addUsuario(Usuario usuario){
		System.out.println("INI: Ejecutando metodo addUsuario");
		Usuario usr = new Usuario();
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "pq_del_usuario", "sp_mnt_usuario");
		
		JdbcHelper.setInOutParameter(call,in,"a_idusuario",Types.NUMERIC, usuario.getIdeusuario());
		JdbcHelper.setInParameter(call, in, "a_contrasena", OracleTypes.VARCHAR, usuario.getContrasena());
		JdbcHelper.setInParameter(call, in, "a_idtercero", OracleTypes.NUMERIC, usuario.getIdtercero());
		JdbcHelper.setInParameter(call, in, "a_idperfil", OracleTypes.NUMERIC, usuario.getIdperfil());
		JdbcHelper.setInParameter(call, in, "a_idpestado", OracleTypes.NUMERIC, usuario.getIdpestado());
		JdbcHelper.setInParameter(call, in, "a_codusuario", OracleTypes.VARCHAR, usuario.getCodusuario());
		JdbcHelper.setInParameter(call, in, "a_historial", OracleTypes.VARCHAR, usuario.getHistorial());
		JdbcHelper.setInParameter(call, in, "a_comentario", OracleTypes.VARCHAR, usuario.getComentario());
		JdbcHelper.setInParameter(call, in, "a_usuario", OracleTypes.VARCHAR, usuario.getUsuario());
//		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Usuario.class);
		
		out = call.execute(in);
		
		
		usr.setIdeusuario(Integer.parseInt(((BigDecimal) out.get("a_idusuario")).toString()));
		System.out.println("FIN: Ejecutando metodo addUsuario");
		
		return usr;
	}
	
	@SuppressWarnings("unchecked")
	public Usuario obtUsuario(Integer id){
		System.out.println("INI: Ejecutando metodo obtUsuario");
		List<Usuario> usr = null;
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "pq_del_usuario", "sp_obt_usuario");
		
		JdbcHelper.setInOutParameter(call,in,"a_idusuario",Types.NUMERIC,id);

		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Usuario.class);
		
		out = call.execute(in);
		
		
		usr = (List<Usuario>) out.get("a_cursor");
		
		System.out.println("FIN: Ejecutando metodo obtUsuario");
		
		return usr.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<VisitasUsuario> getVisitasUsuario( RequestGetVisitasUsuario requestGetVisitasUsuario, String fecha){
		System.out.println("INI: Ejecutando metodo getVisitasUsuario");
		List<VisitasUsuario> visitasUsuarios = null;
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "PQ_DEL_SERVICIOS", "sp_lst_visitas_usuario");
		
		JdbcHelper.setInParameter(call, in, "a_codbbva", OracleTypes.VARCHAR, requestGetVisitasUsuario.getCodbbva());
		JdbcHelper.setInParameter(call, in, "a_nrodocumento", OracleTypes.VARCHAR, requestGetVisitasUsuario.getDni());
		JdbcHelper.setInParameter(call, in, "a_fecentrega", OracleTypes.VARCHAR, fecha);
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, VisitasUsuario.class);
		
		
		out = call.execute(in);
		
		
		visitasUsuarios = (List<VisitasUsuario>) out.get("a_cursor");
		
		System.out.println("FIN: Ejecutando metodo getVisitasUsuario");
		
		return visitasUsuarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<ValidarCourier> validarDNICourier( RequestValidarCourier requestValidarCourier){
		System.out.println("INI: Ejecutando metodo getVisitasUsuario");
		List<ValidarCourier> validarCourier = null;
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "PQ_DEL_SERVICIOS", "sp_validar_usuario");
		
		JdbcHelper.setInParameter(call, in, "a_codbbva", OracleTypes.VARCHAR, requestValidarCourier.getCodbbva());
		JdbcHelper.setInParameter(call, in, "a_nrodocumento", OracleTypes.VARCHAR, requestValidarCourier.getDni());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, ValidarCourier.class);
		
		out = call.execute(in);
		
		validarCourier = (List<ValidarCourier>) out.get("a_cursor");
		
		System.out.println("FIN: Ejecutando metodo getVisitasUsuario");
		
		return validarCourier;
	}
	
	
	public RequestChangeEstadoRegistro changeEstadoRegistro( RequestChangeEstadoRegistro requestChangeEstadoRegistro){
		System.out.println("INI: Ejecutando metodo changeEstadoRegistro");
		//List<ValidarCourier> validarCourier = null;
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "PQ_DEL_SERVICIOS", "sp_act_delivery_entrega");
		
		JdbcHelper.setInParameter(call, in, "a_iddelivery", OracleTypes.NUMERIC, requestChangeEstadoRegistro.getCodigoEntrega());
		JdbcHelper.setInParameter(call, in, "a_estado", OracleTypes.NUMERIC, requestChangeEstadoRegistro.getEstado());
		JdbcHelper.setInParameter(call, in, "a_motivo", OracleTypes.VARCHAR, requestChangeEstadoRegistro.getMotivo());
		JdbcHelper.setInParameter(call, in, "a_codbbva", OracleTypes.VARCHAR, requestChangeEstadoRegistro.getCodbbva());
		JdbcHelper.setInParameter(call, in, "a_nrodocumento", OracleTypes.VARCHAR, requestChangeEstadoRegistro.getDni());
		//JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, ValidarCourier.class);
		
		out = call.execute(in);
		
		//validarCourier = (List<ValidarCourier>) out.get("a_cursor");
		
		System.out.println("FIN: Ejecutando metodo changeEstadoRegistro");
		
		return requestChangeEstadoRegistro;
	}
	
	@SuppressWarnings("unchecked")
	public List<Delivery> informarEntregaCourier( RequestInformarEntregaCourier requestInformarEntregaCourier){
		System.out.println("INI: Ejecutando metodo informarEntregaCourier");
		List<Delivery> deliveries = null;
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), "BBVA", "PQ_DEL_SERVICIOS", "sp_obt_delivery_servicio");
		
		JdbcHelper.setInParameter(call, in, "a_nrodocumento", OracleTypes.VARCHAR, requestInformarEntregaCourier.getDni());
		JdbcHelper.setInParameter(call, in, "a_codbbva", OracleTypes.VARCHAR, requestInformarEntregaCourier.getCodbbva());
		JdbcHelper.setInParameter(call, in, "a_iddelivery", OracleTypes.NUMERIC, requestInformarEntregaCourier.getCodigoEntrega());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Delivery.class);
		
		out = call.execute(in);
		
		deliveries = (List<Delivery>) out.get("a_cursor");
		
		System.out.println("FIN: Ejecutando metodo informarEntregaCourier");
		
		return deliveries;
	}
	

}

