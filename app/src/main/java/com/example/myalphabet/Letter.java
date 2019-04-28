package com.example.myalphabet;

import java.io.OptionalDataException;

public class Letter {
        private String uppercase;
    private String capital;
    public static final Letter[] letters={
        new Letter("A","a"),
        new Letter("Ä","ä"),
        new Letter("B","b"),
        new Letter("C","c"),
        new Letter("Ç","ç"),
        new Letter("D","d"),
        new Letter("E","e"),
        new Letter("F","f"),
        new Letter("G","g"),
        new Letter("Ğ","ğ"),
        new Letter("H","h")

    };
    private Letter(String capital, String uppercase) {
        this.capital=capital;
        this.uppercase=uppercase;
    }
    public String getUppercase() {
        return uppercase;
    }

    public String getCapital() {
        return capital;
    }
}
