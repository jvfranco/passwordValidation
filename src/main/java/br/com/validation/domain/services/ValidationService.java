package br.com.validation.domain.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    public boolean validQuantityChars(String password) {
        return password.length() < 9;
    }

    public boolean validDigitsChars(String password) {
        Pattern pattern = Pattern.compile("\\p{Digit}+");
        return pattern.matcher(password).matches();
    }

    public boolean validLowerChars(String password) {
        Pattern pattern = Pattern.compile("\\p{Lower}+");
        return pattern.matcher(password).matches();
    }

    public boolean validUpperChars(String password) {
        Pattern pattern = Pattern.compile("\\p{Upper}+");
        return pattern.matcher(password).matches();
    }

    public boolean validPunctChars(String password) {
        Pattern pattern = Pattern.compile("\\p{Punct}+");
        return pattern.matcher(password).matches();
    }

    public boolean validDuplicatedChars(String password) {
        String[] letters = password.split("");
        HashSet<String> lettersSet = new HashSet<>();
        lettersSet.addAll(Arrays.asList(letters));

        return password.length() != lettersSet.size();
    }
}
