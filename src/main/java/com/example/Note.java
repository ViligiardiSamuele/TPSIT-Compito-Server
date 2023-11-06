package com.example;

import java.util.ArrayList;
import java.util.List;

public class Note {
    List<String> notes = new ArrayList<String>();

    public Note (List<String> notes){
        this.notes = notes;
    }
    public Note (){
        this.notes = new ArrayList<String>();
    }

    public void addNote(String note){
        this.notes.add(note);
    }

    public void rmNote(String note){
        this.notes.remove(this.notes.indexOf(note));
    }

    public String toString(){
        String out = "";
        for (int i = 0; i < notes.size(); i++) {
            out += (i+1) + " - " + notes.get(i) + "&";
        }
        return out;
    }
}
