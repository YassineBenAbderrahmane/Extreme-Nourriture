package servlet;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import db.DaoUtilisateur;


/*Filtre dont l'utilité est d'obliger le client à se connecter si il souhaite utiliser le panier*/
public class Filtre implements Filter {

  private FilterConfig fconfig;

  public void init(FilterConfig fconfig) {
    this.fconfig = fconfig;
  }

  public void destroy() {
    this.fconfig   = null;
  }

  public void doFilter(ServletRequest requete, ServletResponse reponse, FilterChain chain) throws ServletException, IOException {
    ServletContext context = this.fconfig.getServletContext();
    HttpServletRequest request = (HttpServletRequest)requete;
    HttpServletResponse response = (HttpServletResponse)reponse;
    HttpSession session = request.getSession();
    PrintWriter out = reponse.getWriter();

    String mail = (String)session.getAttribute("mail");
    String pas = (String)session.getAttribute("password");
    
    if (mail != null && pas != null){
      
      if (DaoUtilisateur.utilisateurExiste(mail) && (DaoUtilisateur.verificationMotDePasse(mail, pas))){
        chain.doFilter(request,response);
      }else{
        response.sendRedirect(request.getContextPath()+"/Logout" );
      }
    }else{
        response.sendRedirect(request.getContextPath()+"/Login" );
      }

  }

}

