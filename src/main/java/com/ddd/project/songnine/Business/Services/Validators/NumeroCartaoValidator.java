package com.ddd.project.songnine.Business.Services.Validators;

import com.ddd.project.songnine.CobrancaDomain.Constants.Bandeira;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroCartaoValidator implements ConstraintValidator<NumeroCartao, Integer>{

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean matchesSomeRecognizedRegex = false;
        for (var bandeira : Bandeira.values()) {
            if (value.toString().matches(bandeira.getRegex())) {
                matchesSomeRecognizedRegex = true;
                break;
            }
        }
        return matchesSomeRecognizedRegex && isValidLuhn(String.valueOf(value));
    }

    /**
     * Verifica se um determinado cartão é válido de acordo com o algoritmo de Luhn.
     *
     * @param number o número a ser verificado
     * @return true se o número for válido, false caso contrário
     */
    private boolean isValidLuhn(String number) {
        int n = number.length();
        int total = 0;
        boolean even = true;
        // iterate from right to left, double every 'even' value
        for (int i = n - 2; i >= 0; i--) {
            int digit = number.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                // value may only contain digits
                return false;
            }
            if (even) {
                digit <<= 1; // double value
            }
            even = !even;
            total += digit > 9 ? digit - 9 : digit;
        }
        int checksum = number.charAt(n - 1) - '0';
        return (total + checksum) % 10 == 0;
    }
}
