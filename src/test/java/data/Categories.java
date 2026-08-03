package data;

public enum Categories {
    Аниме("Лучшие аниме"),
    Манга("Манга"),
    Ранобэ("Ранобэ");

    public final String description;

    Categories(String description){
        this.description = description;
    }
}
