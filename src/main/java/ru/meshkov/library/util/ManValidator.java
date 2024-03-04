package ru.meshkov.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.meshkov.library.dao.ManDAO;
import ru.meshkov.library.models.Man;

@Component
public class ManValidator implements Validator {
    private final ManDAO manDAO;

    @Autowired
    public ManValidator(ManDAO manDAO) {
        this.manDAO = manDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Man.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Man man = (Man) o;
        Man bdMan = manDAO.show(man.getFio());
        if(bdMan != null && bdMan.getId() != man.getId()) {
            errors.rejectValue("fio", "", "This fio is already existing");
        }
    }
}
