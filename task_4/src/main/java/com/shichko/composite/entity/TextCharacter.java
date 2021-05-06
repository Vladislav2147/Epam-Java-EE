package com.shichko.composite.entity;

public class TextCharacter implements TextComponent {

    private final TextCharacterType characterType;
    private char value;

    public TextCharacter(char value, TextCharacterType characterType) {
        this.value = value;
        this.characterType = characterType;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public TextCharacterType getCharacterType() {
        return characterType;
    }

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextCharacter that = (TextCharacter) o;

        if (value != that.value) return false;
        return characterType == that.characterType;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + characterType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextCharacter{");
        sb.append("value=").append(value);
        sb.append(", characterType=").append(characterType);
        sb.append('}');
        return sb.toString();
    }
}
