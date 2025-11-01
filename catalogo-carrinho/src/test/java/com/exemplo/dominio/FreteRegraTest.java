package com.exemplo.dominio;


import com.exemplo.dominio.portas.FreteAPI;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FreteRegraTest {

    @Test
    void deveCalcularFreteComMock() {
        FreteAPI api = mock(FreteAPI.class);
        when(api.cotar("89255-000", new BigDecimal("2.0"))).thenReturn(new BigDecimal("15.00"));

        FreteRegra regra = new FreteRegra(api);
        BigDecimal resultado = regra.calcular("89255-000", new BigDecimal("2.0"));
        assertEquals(new BigDecimal("15.00"), resultado);
    }

    @Test
    void deveLancarExcecaoSePesoInvalido() {
        FreteAPI api = mock(FreteAPI.class);
        FreteRegra regra = new FreteRegra(api);
        assertThrows(IllegalArgumentException.class, () ->
                regra.calcular("89255-000", BigDecimal.ZERO));
    }
}
