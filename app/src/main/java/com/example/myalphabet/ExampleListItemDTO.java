package com.example.myalphabet;

public class ExampleListItemDTO {
    String word;
    String w_transcript;


    ExampleListItemDTO(String item_word, String item_w_transcript){
        word=item_word;
        w_transcript=item_w_transcript;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getW_transcript() {
        return w_transcript;
    }

    public void setW_transcript(String w_transcript) {
        this.w_transcript = w_transcript;
    }

}
