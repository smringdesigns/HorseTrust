package com.vargas.horsemarket.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 * Value Object de dinero.
 * Inmutable por diseño.
 */
@Getter
@EqualsAndHashCode
public final class Money {

    private final BigDecimal amount;
    private final String currencyCode;

    public Money(BigDecimal amount, String currencyCode) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto no puede ser nulo o negativo");
        }
        if (currencyCode == null || currencyCode.isBlank()) {
            throw new IllegalArgumentException("El código de moneda es requerido");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currencyCode = currencyCode.toUpperCase();
    }

    public static Money of(BigDecimal amount, String currencyCode) {
        return new Money(amount, currencyCode);
    }

    public static Money ofMXN(BigDecimal amount) {
        return new Money(amount, "MXN");
    }

    public static Money ofUSD(BigDecimal amount) {
        return new Money(amount, "USD");
    }

    public boolean isGreaterThan(Money other) {
        assertSameCurrency(other);
        return this.amount.compareTo(other.amount) > 0;
    }

    private void assertSameCurrency(Money other) {
        if (!this.currencyCode.equals(other.currencyCode)) {
            throw new IllegalArgumentException("No se pueden comparar monedas distintas: "
                    + this.currencyCode + " vs " + other.currencyCode);
        }
    }

    @Override
    public String toString() {
        return currencyCode + " " + amount.toPlainString();
    }
}
