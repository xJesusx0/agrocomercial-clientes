package com.agrocomercial.clientes.utils;

import java.util.Optional;

public class ParseUtils {

    private ParseUtils() {

    }

    public static Optional<Integer> toInt(String value) {
        try {
            return Optional.of(Integer.parseInt(value.trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return Optional.empty();
        }
    }

    public static Optional<Long> toLong(String value) {
        try {
            return Optional.of(Long.parseLong(value.trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> toDouble(String value) {
        try {
            return Optional.of(Double.parseDouble(value.trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return Optional.empty();
        }
    }
}
