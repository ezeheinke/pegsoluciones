package com.peg.blackberry.planetaboca.util;

import java.util.Calendar;

public class Strings {
	
	
	// Opciones
	public static final String[] OPCIONES_SI_O_NO = {"SI","NO"};

	public static final String ID_ULTIMAS = null;
	public static final String HOME = "HOME";;
	public static final String CATEGORIA_RECIENTES = "recientes";
	public static final String FUTBOL = "futbol";
	public static final String VOLEY = "voley";
	public static final String BASKET = "basquet";
	public static final String MERCADO_DE_PASES = "mercadodepases";
	public static final String LOCAL = "local";
	public static final String FEMENINO = "femenino";
	public static final String INFERIORES = "inferiores";
	public static final String INSTITUCIONAL = "institucional";
	public static final String CERO_NOTICIAS = "En este momento no hay noticias diponibles.";
	public static final String ERROR_SERVICIO = "Hubo un error al intentar obtener las noticias," +
			"por favor intentelo más tarde.";
	public static final String DESEA_SALIR = "¿Deseas salir de PBJ?";
	public static final String PUBLICIDAD = "¿Queres que los más 700.000 lectores de " +
			"Planeta Boca Juniors te conozcan? Ponete en contacto con nosotros al " +
			"email comercial@planetabocajuniors.com.ar.\n ¿Desea mandarnos un email?";
	public static final String TAMANIO_PAGINA = "10";
	public static final String NO_MAS_NOTICIAS = "No se encontraron mas noticias";
	public static final String ACERCA_DE = "Iniciamos el proyecto de Planeta Boca Juniors el 5 de enero del 2009, con el objetivo de brindarle al hincha Xeneize un sitio distinto en el que pueda encontrar todas las novedades acerca del club de sus amores.\n" +
			"Teniendo a la innovación y a la creatividad como bandera, fuimos incorporando distintas secciones de acuerdo a las necesidades y preferencias del público, generando así una combinación armoniosa entre las noticias de último momento, los análisis, las notas de opinión y los infaltables informes históricos.\n" +
			"Con el paso del tiempo, las visitas fueron creciendo constantemente, alcanzando números para nada esperados en el comienzo del proyecto. Nosotros como miembros del staff sentimos el compromiso de brindarles a todos los lectores un espacio para informarse y opinar acerca de Boca Juniors, a lo largo de las 24 horas del día.\n" +
			"Como vía de contacto, también pueden encontrarnos en Facebook, donde tenemos más de 750.000 seguidores, y en Twitter, donde ya superamos la barrera de los 40.000. Además contamos con un canal en YouTube en el que más de 1200 suscriptores disfrutan del contenido audiovisual del mismo.\n" +
			"Desde Planeta Boca Juniors agradecemos tu visita y esperamos que nos sigas eligiendo.";
	public static final String DESEA_LLAMARLO_AHORA = "¿Desea llamarlo ahora?";

	public static final String ERROR_GUARDAR_CONTACTO = "No se pudo guardar este contacto.";

	public static final String SE_GUARDO_CONTACTO = "Se ha guardado el contacto: ";

	public static final String CONSTRUIDO_POR_PEG = "Esta aplicación fue construida por PEG Soluciones.\n" +
	           		"Contacto: comercial@pegsoluciones.com\n" +
	           		"http://www.pegsoluciones.com";

	public static final String AGENDAR_ERROR = "Ocurrio un error al agendar este evento";
	public static final String AGENDAR_OK = "Se agendo correctamente en evento ";

	public static final String ASUNTO_PUBLICIDAD = "Publicidad Planeta Boca Juniors Web y Mobile";
	public static final String MAIL_PUBLICIDAD = "comercial@planetabocajuniors.com.ar";

	public static final String CUERPO_MAIL_PUBLICIDAD = "Quiero publicar en PBJ, ";

	public static final String DARLE_PERMISOS = "PBJ Mobile no tiene los permisos" +
			" necesarios para correr, si no se los das PBJ mobile se cerrará." +
			"¿Desea darle permisos a PBJ Mobile?";

	private static Calendar c = Calendar.getInstance();
	public static final String COPYRIGHT = "Copyright © 2009 - " + c.get(Calendar.YEAR)
		+ " Planeta Boca Juniors | Todos los derechos reservados\n\n" + 
		"Copyright © 2012" + (c.get(Calendar.YEAR) == 2012 ? "" : "-" + c.get(Calendar.YEAR))
		+ " Planeta Boca Juniors Mobile | Todos los derechos reservados\n";

	public static final String ERROR_TWITTER = "No se pudo conectar con Twitter intentelo mas tarde.";

	public static final String AYUDA = "Ahora PBJ Mobile te permite" +
			" que las publicaciones en twitter se actualicen automaticamente o" +
			" a pedido, permitiendote seguir el minuto a minuto de los partidos de Boca desde nuestra applicación." +
			" Puedes activar la opcion de auto refresh desde la pantalla de Twitter o actualizarlo manualmente. " +
			"Recuerda que si tiene activado el auto refresh estaras consumiendo datos contantemente" +
			" de acuerdo a tu plan de datos esto puede generar gastos adicionales (por ejemplo si noposees redes sociales ilimitadas)" +
			" El resto de la aplicación NO genera consumo datos adicionales";
	
	public static final String BUSCA_AYUDA_EN_MENU = "\nEsta pantalla se mostrará " +
			" por única vez, si quieres volver a verla esta disponible en la opción" +
			" ayuda del menu de Blackberry";

	public static final String ERROR_COMENTARIOS = "Errro al obtener comentarios, inténtelo más tarde.";

	public static final String ERROR_COMENTAR = "Error al comentar.";

	public static final String COMENTARIO_ENVIADO = "Su comentario ha sido enviado." +
			" Éste será revisado por un adminsitrador, luego de su aprobación podrá" +
			" visualizarse en la pagina de comentarios.";

	public static final String CERO_COMENTARIOS = "No hay comentarios para esta noticia." +
			" ¿Queres ser el primero en agregar uno?";

	public static final String VER_SIGUIENTES_NOTICIAS = "Ver siguientes noticias.";

	public static final String AUTO_REFRESH = "Auto refresh";

	public static final String ACTUALIZA_AHORA = "¡Actualiza ahora!";

	public static final String USER_TWITTER = "PlanetaBoca";

	public static final String COMPLETAR_DATOS = "Debe completar todos los campos.";	
	
}
