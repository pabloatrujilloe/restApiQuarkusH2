package quarkus.resource;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import quarkus.entity.Book;
import quarkus.repository.BookRepository;


import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional                                                                      //cada vez q vallamos a modificar la base de datos. esta anotacion la actualiza.
public class BookResource {

    @Inject
    private BookRepository booksRepository;

    @GET
    public List<Book> index(@QueryParam("q") String query){
        if (query == null){                                                                                 // si no recibe nada osea null que devuelva lo de abajo
        return booksRepository.listAll(Sort.by("pubDate", Sort.Direction.Descending));               //aqui estamos ordenando el medoto get por fecha de publicacion
        }else {                                                                                             //si hay una query en la url devolver lo de abajo
            String  filter ="%" + query + "%";                                                                  //el % porque el string o palabra puede estar en cualquier parte de la cadena del string o titulo o decripcion
           Sort crit = Sort.by("pubDate", Sort.Direction.Descending);
           return booksRepository.list("title ILIKE ?1 OR description ILIKE ?2",crit, filter, filter);   // porque 2 fitro para que filtre en title = ?1. que es el titulo o
        }                                                                                                      //filtre en descripcion = ?2 fitra la descripcion.
                                                                                                                //cada ? es un fitro
    }
    @POST
    public Book insert(Book book) {                                                 //ingresamos o creamos un nuevo book. para q esto se pueda crear tenemos q poner @Transactional. de lo contrario nos dara un error.
        booksRepository.persist(book);
        return book;
    }

    @GET
    @Path("{id}")                                                                   //para buscar book con el ID en la bd h2 memoria temporal u otra que estemos ocupando.
    public Book retrieve(@PathParam("id") long id){
        var book = booksRepository.findById(id);
        if ( book != null) {
            return book;
        }
        throw  new NoSuchElementException("no hay libro con el ID " + id + ".");
    }
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        booksRepository.deleteById(id);
    }

    @PUT
    @Path("{id}")
    public Book update(@PathParam("id") Long id, Book book){
        var updateBook = booksRepository.findById(id);
        if (updateBook !=null){
            updateBook.setTitle(book.getTitle());
            updateBook.setPubDate(book.getPubDate());
            updateBook.setNumPages(book.getNumPages());
            updateBook.setDescription(book.getDescription());
            booksRepository.persist(updateBook);
            return updateBook;
        }
        throw  new NoSuchElementException("no hay libro con el ID " + id + ".");
    }

}
