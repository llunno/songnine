package com.ddd.project.songnine.CobrancaDomain.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bandeira {
    VISA("/^4[0-9]{12}(?:[0-9]{3})?$/"),
    MASTERCARD("/^5[1-5][0-9]{14}$|^2(?:2(?:2[1-9]|[3-9][0-9])|[3-6][0-9][0-9]|7(?:[01][0-9]|20))[0-9]{12}$/"),
    AMERICAN_EXPRESS("/^3[47][0-9]{13}$/"),
    ELO("/^((((636368)|(438935)|(504175)|(451416)|(636297))\\d{0,10})|((5067)|(4576)|(4011))\\d{0,12})$/"),
    DESCONHECIDA("");

    private String regex;
}
