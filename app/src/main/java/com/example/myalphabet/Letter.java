package com.example.myalphabet;

import java.io.OptionalDataException;

public class Letter {
        private String uppercase;
    private String capital;
    private String image;
    private String transcript;
    private String example;
    private String type;

    public static final Letter[] letters={
        new Letter("A","a","ay","ay","[ay]","dt"),
        new Letter("Ä","ä","azhe","azhe","[aje]","dt"),
        new Letter("B","b","besik","besik","[besyk]","ds"),
        new Letter("C","c","cebe","cebe","jebe","ds"),
        new Letter("Ç","ç","çay","çay","[cay]","ds"),
        new Letter("D","d","dorba","dorba","[dorba]","ds"),
        new Letter("E","e","elik","elik","[el'ik]","dt"),
        new Letter("F","f","formula","formula","[formula]","ds"),
        new Letter("G","g","gul","gul","[gul']","ds"),
        new Letter("Ğ","ğ","garysh","garysh","[gharysh]","ds"),
        new Letter("H","h","han","han","[khan]","ds")

    };
    private Letter(String capital,String uppercase,String image,String example,String transcript,String type ) {
        this.capital=capital;
        this.uppercase=uppercase;
        this.image=image;
        this.transcript=transcript;
        this.example=example;
        this.type=type;
    }
    public String getUppercase() {
        return uppercase;
    }

    public String getCapital() {
        return capital;
    }

    public String getImage() {
        return image;
    }

    public String getTranscript() {
        return transcript;
    }

    public String getExample() {
        return example;
    }

    public String getType() {
        return type;
    }
}

