package cesmac.si.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cesmac.si.util.VerificadorUtil.naoEstaVazioOuNulo;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private static final String EXPRESSAO_REGULAR = "^[_A-Za-z0-9-]+(\\." +
            "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
            "(\\.[A-Za-z]{2,})$";

    @Override
    public void validate(FacesContext contexto, UIComponent componente, Object objeto) throws ValidatorException {
        String email = (String) objeto;
        if (!verificarEmail(email))
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Email inválido!", "Email inválido!"));
    }

    private static Boolean verificarEmail(String email) {
        if (naoEstaVazioOuNulo(email)) {
            Pattern pattern = Pattern.compile(EXPRESSAO_REGULAR, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches())
                return true;
        }

        return false;
    }
}
