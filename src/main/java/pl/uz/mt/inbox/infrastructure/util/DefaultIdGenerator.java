package pl.uz.mt.inbox.infrastructure.util;

import pl.uz.mt.inbox.domain.IdGenerator;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class DefaultIdGenerator implements IdGenerator {

    private static final int LENGTH = 16;

    @Override
    public String generate(final String prefix) {
        final var randomNumericString = randomNumericString();
        final var checkDigit = generateLuhnCheckDigit(prefix, randomNumericString);
        return prefix + randomNumericString + checkDigit;
    }

    private String randomNumericString() {
        final var secureRandom = new SecureRandom();
        final var randomNumericString = new StringBuilder();
        for (var index = 0; index < LENGTH; index++) {
            randomNumericString.append(secureRandom.nextInt(10));
        }
        return randomNumericString.toString();
    }

    private String generateLuhnCheckDigit(final String prefix, final String randomNumericString) {
        final var identifier = getPrefixCode(prefix) + randomNumericString;
        var sum = 0;

        for (var i = 0; i < identifier.length(); i += 2) {
            var multiplied = identifier.codePointAt(i) * 2;
            if (multiplied > 9) {
                sum += multiplied - 9;
            } else {
                sum += multiplied;
            }
        }

        for (var i = 1; i < identifier.length(); i += 2) {
            sum += identifier.codePointAt(i);
        }
        return String.valueOf((10 - sum % 10) % 10);
    }

    private String getPrefixCode(final String prefix) {
        return prefix.chars()
                     .toString();
    }
}
