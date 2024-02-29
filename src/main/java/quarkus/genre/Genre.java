package quarkus.genre;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.*;
import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.Column;

@Entity
@FilterDef(
        name = "name.like",
        parameters = @ParamDef(name = "name", type = String.class)
)
@Filter(name = "name.like", condition = "LOWER(name) LIKE LOWER(:name)")    //si ponemos LIKE solo y hacemos la peticion con mayuscula no devulve nada.// pero si le agregamos LOWER da lo mismo si la solicitud en posmant lo haces con mayuscula o miniscula


public class Genre {

    @Id
    @GeneratedValue
    private Long id;

    //@JsonProperty("genreName")                                //para cuando mapea lo puedas llamar de esa forma
    //@JsonAlias({"genreName", "name"})                           //aqui para mapear de las 2 formas te lo traera
                                                                //esto se hace cuando en la base de datos no definimos nada de validacion
    @Column(unique = true)
    private String name;
    @CreationTimestamp
    //@JsonIgnore
    private LocalDate createDate;
    @UpdateTimestamp
    //@JsonIgnore
    private LocalDate updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;
        return Objects.equals(getId(), genre.getId()) && Objects.equals(getName(), genre.getName()) && Objects.equals(getCreateDate(), genre.getCreateDate()) && Objects.equals(getUpdateDate(), genre.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreateDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
