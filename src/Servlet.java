import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolaMundo
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String lat = request.getParameter("LAT");
		String lng = request.getParameter("LONG");
		String result = new HTTPGetter()
				.HTTPGet("https://api.instagram.com/v1/media/search?lat=" + lat
						+ "&lng=" + lng
						+ "&client_id=506e25bd5b3c40338a96e04bcd02fea6");
		ArrayList<String> listaUrls = obtenerUrls(result);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Imagenes instagram para LAT="+lat+" y LONG="+lng+"</title></head>");
		out.println("<body>");
		out.println("<center>");
		for (String url : listaUrls) {
			out.print("<IMG SRC=\"");
			out.print(url.replaceAll("/", ""));
			out.print("\">");
			out.println("<br>");
		}
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	ArrayList<String> obtenerUrls(String respuesta) {
		ArrayList<String> retorno = new ArrayList<String>();
		int tamano = 0;
		String aux = "";
		while (respuesta.indexOf("\"standard_resolution\":{\"url\":\"") > -1
				&& tamano < 10) {
			respuesta = respuesta.substring(respuesta
					.indexOf("\"standard_resolution\":{\"url\":\"") + 30);
			aux = respuesta.substring(0, respuesta.indexOf("\""));
			if(aux.substring(aux.length()-3, aux.length()).equals("jpg")){
			retorno.add(aux);
			tamano++;
			}
		}
		return retorno;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
