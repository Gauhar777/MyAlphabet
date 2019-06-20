package com.example.myalphabet;

public class ExampleListItemDTO {
    String word;
    String w_transcript;
    String w_voice;
    String m_voice;

    ExampleListItemDTO(String item_word, String item_w_transcript,String item_w_voice,String item_m_voice){
        word=item_word;
        w_transcript=item_w_transcript;
        w_voice=item_w_voice;
        m_voice=item_m_voice;
    }

    public String getWord() {
        return word;
    }
    public String getW_transcript() {
        return w_transcript;
    }

    public String getW_voice() {
        return w_voice;
    }

    public String getM_voice() {
        return m_voice;
    }
}
