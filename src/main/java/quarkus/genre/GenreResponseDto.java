package quarkus.genre;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record GenreResponseDto(Long id, String name) {
}
    //@ProjectedFieldName("name") String title
// solo nos devolvera lo que le estamos pidiendo el id y el nombre. aqui se le solicita lcuantos campos quieres que te devuelva


