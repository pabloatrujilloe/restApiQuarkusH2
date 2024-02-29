package quarkus.genre;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import net.bytebuddy.description.annotation.AnnotationValue;

import java.net.URI;
import java.util.NoSuchElementException;

@Path("/genres")
@Transactional
public class GenreResource {

    private GenreRepository genres;
    private GenreMapper mapper;

    @Inject
    public GenreResource(GenreRepository genres, GenreMapper mapper){
        this.genres = genres;
        this.mapper = mapper;
    }

    @GET
    public PaginatedResponse<GenreResponseDto> list(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("q") String q) {
        PanacheQuery<Genre> query = genres.findPage(page);
        PanacheQuery<GenreResponseDto> present = query.project(GenreResponseDto.class);
        if (q != null) {
            var nameLike = "%" + q + "%";
            present.filter("name.like", Parameters.with("name", nameLike));        //filtro dinamico  URL + ?page=1 o 2 o 3 te traera la pagina solicitada con todo sus genre //y como quiero ser mas especifico y mi genre no esta aqui le ponemos otro parametro para q fitre nuevamente y agregamos &q=nombre del genero.y nos devolvera el genre solicitado
        }
        return new PaginatedResponse<>(present);
    }

    @POST
    public Response create(@Valid CreateGenreDto genre) {                                               //aqui validamos cada vez que ingresamnos un nuevo objeto
        var entity = mapper.fromCreate(genre);
        genres.persist(entity);
        var representation = mapper.present(entity);
        return Response.created(URI.create("/genres/" + entity.getId())).entity(representation).build();
    }

    @GET
    @Path("{id}")
    public GenreResponseDto get(@PathParam("id") Long id) {
        return genres
                .findByIdOptional(id)
                .map(mapper::present)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + " not found"));

    }
    @PUT
    @Path("{id}")
    public GenreResponseDto update(@PathParam("id") Long id, @Valid UpdateGenreDto inbox){
        Genre found = genres
                .findByIdOptional(id)
                .orElseThrow(() ->new NoSuchElementException("Genre " + id + "not found"));
        mapper.update(inbox, found);
        genres.persist(found);
        return mapper.present(found);
    }
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        genres.deleteById(id);

    }
}
