package quarkus.genre;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.boot.model.naming.ImplicitUniqueKeyNameSource;
import org.hibernate.validator.constraints.UniqueElements;

import javax.lang.model.element.Name;
import javax.xml.namespace.QName;


public record CreateGenreDto(
        @NotBlank
        String name) {
}
//@NotBlank(message = "El nombre no puede estar en blanco")




