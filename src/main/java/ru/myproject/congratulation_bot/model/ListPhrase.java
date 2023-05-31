package ru.myproject.congratulation_bot.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListPhrase {

    private List<String> table = new ArrayList<>();

    public void ListPhraseAdd(String... phrase) {
        Collections.addAll(this.table, phrase);
    }

    public int getSize() {
        return table.size();
    }

    public void setTable(List<String> table) {
        this.table = table;
    }

    public String getTable(int random_num) {
        return table.get(random_num);
    }
}
