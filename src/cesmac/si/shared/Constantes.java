package cesmac.si.shared;

public interface Constantes {

    String SUCESSO = "Sucesso!";
    String ERRO = "Erro!";
    String AVISO = "Aviso!";
    String GET = "GET";
    String CONTENT_TYPE = "application/json";
    String USER_AGENT = "Mozilla/5.0";

    interface Modals {
        String MODAL_LOGIN = "mdLogin";
    }

    interface Forms {
        String FORM_LOGIN = "formLogin";
    }

    interface Mensagens {
        String MSG_ERRO_REDIRECIONAR = "Ocorreu um erro ao tentar redirecionar para a página %s! \n Erro: %s";
        String MSG_LOGIN_INCORRETO = "Login ou Senha incorretos!";
    }
}
