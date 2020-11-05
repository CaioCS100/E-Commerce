package cesmac.si.util;

import org.primefaces.PrimeFaces;

import javax.faces.context.FacesContext;
import java.io.IOException;

public class JSFUtil {

    public static void abrirModal(String modal) {
        obterInstancia().executeScript("$('#" + modal + "').modal('show')");
    }

    public static void fecharModal(String modal) {
        obterInstancia().executeScript("$('#" + modal + "').modal('hide')");
    }

    public static void atualizarComponente(String componente) {
        obterInstancia().ajax().update(componente);
    }

    public static void redirecionarParaPagina(String pagina) throws IOException {
        String url = obterInstanciaFaces().getExternalContext().getRequestContextPath();
        obterInstanciaFaces().getExternalContext().redirect(url + pagina + ".xhtml");
    }

    private static PrimeFaces obterInstancia() {
        return PrimeFaces.current();
    }

    private static FacesContext obterInstanciaFaces() {
        return FacesContext.getCurrentInstance();
    }
}
