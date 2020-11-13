package cesmac.si.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import static cesmac.si.util.CpfUtil.validarCpf;

@FacesValidator("cpfValidator")
public class CpfValidator implements Validator {

    @Override
    public void validate(FacesContext contexto, UIComponent componente, Object valor) throws ValidatorException {
        String cpf = (String) valor;

        if (!validarCpf(cpf))
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "CPF inválido!", "CPF inválido!"));
    }
}
