package com.crapz.conversordemonedas.modelos;

import java.math.BigDecimal;

public class ResultadoConversion {
    private String result;
    private String base_code;
    private String target_code;
    private BigDecimal conversion_rate;
    private BigDecimal conversion_result;

    public BigDecimal getConversionResult() {
        return conversion_result;
    }

    @Override
    public String toString() {
        return "ResultadoConversion{" +
                "conversion_result=" + conversion_result +
                '}';
    }
}