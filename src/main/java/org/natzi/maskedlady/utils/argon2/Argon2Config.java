package org.natzi.maskedlady.utils.argon2;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class Argon2Config {
    private static final int ITERATIONS = 2;
    private static final int MEM_LIMIT = 65536; // KB
    private static final int HASH_LENGTH = 32;
    private static final int PARALLELISM = 1;
    private static final int SALT_LENGTH = 32;

    private Argon2 createIntanceArgon2() {
        return Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2id,
                SALT_LENGTH,
                HASH_LENGTH
        );
    }

    public String encrypt(String text) {
        if (text == null) return null;

        Argon2 argon2 = createIntanceArgon2();
        try {
            return argon2.hash(ITERATIONS, MEM_LIMIT, PARALLELISM, text.toCharArray(), StandardCharsets.UTF_8);
        } finally {
            argon2.wipeArray(text.toCharArray());
        }
    }

    public boolean verifyText(String text, String storedHash) {
        Argon2 argon2 = createIntanceArgon2();
        char[] textChar = text.toCharArray();
        try {
            return argon2.verify(storedHash, textChar);
        } finally {
            argon2.wipeArray(textChar);
        }
    }
}
