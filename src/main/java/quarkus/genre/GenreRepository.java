package quarkus.genre;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {
    public PanacheQuery<Genre> findPage(int page) {
        Page p = new Page(page - 1, 5);                                                         //aqui definimos la cantidad de objetos q estaran en cada pagina repository tambien lo podemos ocupar como servicio
        var query = findAll(Sort.descending("id"));
        query.page(p);
        return query;
    }
}
