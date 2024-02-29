package quarkus.genre;

import org.h2.command.dml.Update;

public interface GenreMapper {
    Genre fromCreate(CreateGenreDto dto);
    void update(UpdateGenreDto dto, Genre genre);
    GenreResponseDto present(Genre g);
}



