package es.uniovi.eii.ds.editor;

public class TextEditor {
    private StringBuilder text = new StringBuilder();

    public void insert(String[] words) {
        for (String word : words) {
            text.append(" ").append(word);
        }
    }

    public void delete() {
        int indexOfLastWord = text.toString().trim().lastIndexOf(" ");
        if (indexOfLastWord == -1) {
            text = new StringBuilder("");
        } else {
            text.setLength(indexOfLastWord);
        }
    }

    public void replace(String find, String replace) {
        text = new StringBuilder(text.toString().replace(find, replace));
    }

    public String getText() {
        return text.toString();
    }

    public void setText(String content) {
        text = new StringBuilder(content);
    }
}