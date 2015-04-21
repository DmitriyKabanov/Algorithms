package com.sample.algorithms.compressor;

import java.util.ArrayList;
import java.util.List;

public class LZWCompressor {
    private static final List<String> DICTIONARY = new ArrayList<String>(255);

    static {
        for (int i = 0; i < 256; i++) {
            String word = new String(new byte[]{(byte) i});
            DICTIONARY.add(word);
        }
    }

    public int[] compress(String input) {
        int[] result = new int[input.length()];

        int inputIndex = 0;
        int outputIndex = 0;

        char character = input.charAt(inputIndex);
        String word = String.valueOf(character);

        do {
            result[outputIndex] = DICTIONARY.indexOf(word);

            inputIndex++;
            character = input.charAt(inputIndex);
            word += String.valueOf(character);

            if (!DICTIONARY.contains(word)) {
                DICTIONARY.add(word);
                word = String.valueOf(character);
                outputIndex++;
            }

        } while (inputIndex < input.length() - 1);

        result[outputIndex] = DICTIONARY.indexOf(word);

        return result;
    }

    public String decompress(int[] input) {
        String result = DICTIONARY.get(input[0]);
        String previous = result;

        for (int i = 1; i < input.length; i++) {
            int dictionaryIndex = input[i];

            String word = DICTIONARY.get(dictionaryIndex);
            result += word;
            previous += word.charAt(0);

            if (!DICTIONARY.contains(previous)) {
                DICTIONARY.add(previous);
                previous = word;
            }
        }

        return result;
    }
}