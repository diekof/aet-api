package com.aet.api.resource;


import com.aet.api.model.TrechoRodovia;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TrechoRodoviaMapper {

    private static final DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.'); // Se quiser 1.000,00
        decimalFormat = new DecimalFormat("#,##0.00", symbols);
    }

    public static List<TrechoRodoviaResponseDto> toDtoList(List<TrechoRodovia> rodovias) {
        return rodovias.stream()
                .map(rodovia -> TrechoRodoviaResponseDto.builder()
                        .BR(rodovia.getBr())
                        .UF(rodovia.getEstadoId())
                        .KM_INICIAL(decimalFormat.format(rodovia.getKmInicial()))
                        .KM_FINAL(decimalFormat.format(rodovia.getKmFinal()))
                        .TRECHO_INICIO(rodovia.getTrechoInicial())
                        .TRECHO_FINAL(rodovia.getTrechoFinal())
                        .ID_SEGMENTO(rodovia.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
