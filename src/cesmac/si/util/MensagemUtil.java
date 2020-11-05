package cesmac.si.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import static cesmac.si.shared.Constantes.*;

public class MensagemUtil {

    public static void exibirMensagemSucesso(String mensagem) {
        gerarMensagem(FacesMessage.SEVERITY_INFO, mensagem, SUCESSO);
    }

    public static void exibirMensagemErro(String mensagem) {
        gerarMensagem(FacesMessage.SEVERITY_ERROR, mensagem, ERRO);
    }

    public static void exibirMensagemAdvertencia(String mensagem) {
        gerarMensagem(FacesMessage.SEVERITY_WARN, mensagem, AVISO);
    }

    private static void gerarMensagem(FacesMessage.Severity tipoSeverity, String mensagem, String detalhe) {
        obterInstanciaFaces().addMessage(null, new FacesMessage(tipoSeverity, mensagem, detalhe));
    }

    private static FacesContext obterInstanciaFaces() {
        return FacesContext.getCurrentInstance();
    }
}
