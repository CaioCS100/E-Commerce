package cesmac.si.controller;

import cesmac.si.model.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;

import static cesmac.si.shared.Constantes.Mensagens.MSG_ERRO_REDIRECIONAR;
import static cesmac.si.shared.Constantes.Mensagens.MSG_LOGIN_INCORRETO;
import static cesmac.si.shared.Constantes.Modals.MODAL_LOGIN;
import static cesmac.si.util.JSFUtil.*;
import static cesmac.si.util.MensagemUtil.exibirMensagemErro;

@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    public UsuarioController() {
        this.usuario = new Usuario();
    }

    public void abrirModalLogin() {
        limparObjetoUsuario();
        abrirModal(MODAL_LOGIN);
    }

    public void fecharModalLogin() {
        fecharModal(MODAL_LOGIN);
    }

    public void limparObjetoUsuario() {
        this.usuario = new Usuario();
    }

    public void fazerLogin() {
        if (this.usuario.getEmail() != null && this.usuario.getSenha() != null && this.usuario.getSenha().equals("123")) {
            redirecionarParaPagPrincipalFuncionario();
        }
        else {
            exibirMensagemErro(MSG_LOGIN_INCORRETO);
        }
    }

    private void redirecionarParaPagPrincipalFuncionario() {
        try {
            redirecionarParaPagina("/pages/funcionarios/principal");
        } catch (IOException ex) {
            exibirMensagemErro(String.format(MSG_ERRO_REDIRECIONAR, "principal", ex.getMessage()));
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
