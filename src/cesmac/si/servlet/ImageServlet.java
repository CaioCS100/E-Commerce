package cesmac.si.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cesmac.si.model.Funcionario;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Funcionario model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.model = (Funcionario) request.getSession().getAttribute("funcionario");
		try {
			if ((Funcionario) request.getSession().getAttribute("funcionario") != null) 
			{
				this.model = (Funcionario) request.getSession().getAttribute("funcionario");
				if (this.model.getImagem() != null) 
				{
					System.out.println(this.model.getNome());
					// InputStream input = new ByteArrayInputStream(this.model.getImagem());
					response.getOutputStream().write(this.model.getImagem());
					response.getOutputStream().close();
					response.getOutputStream().flush();
				}
			} else 
			{
				System.out.println("entrou no else");
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
