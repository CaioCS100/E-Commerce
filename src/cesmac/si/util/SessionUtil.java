package cesmac.si.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cesmac.si.model.Pessoa;


public class SessionUtil {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getNomeUsuario() {
        return ((Pessoa) getSession().getAttribute("usuario")).getNome();
    }

    public static Long getIdUsuario() {
        return (long) ((Pessoa) getSession().getAttribute("usuario")).getId();
    }

    public static Pessoa getUsuario() {
        return ((Pessoa) getSession().getAttribute("usuario"));
    }

    public static Pessoa getUsuarioServidor() {
        return ((Pessoa) getSession().getAttribute("usuario"));
    }

    public static Pessoa getUsuarioContribuinte() {
        return ((Pessoa) getSession().getAttribute("usuario"));
    }

    public static void setSessionAttribute(String attrName, Object attrValue) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(attrName, attrValue);
    }
}