package ru.mtuci.policy.dto;

import lombok.Data;
import ru.mtuci.policy.model.Doctype;

@Data
public class DoctypeDto {
    private String type;

    public Doctype toDoctype() {
        Doctype doctype = new Doctype();
        doctype.setType(type);

        return doctype;
    }

    public static DoctypeDto fromDoctype(Doctype doctype) {
        DoctypeDto doctypeDto = new DoctypeDto();
        doctypeDto.setType(doctype.getType());

        return doctypeDto;
    }
}
