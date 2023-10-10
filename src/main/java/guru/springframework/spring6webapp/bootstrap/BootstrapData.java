package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootstrapData implements CommandLineRunner {

    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private  final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");


        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("654321");

        Publisher koketso = new Publisher();
        koketso.setPublisherName("Koketso Seopela");
        koketso.setAddress("Duvha 6");
        koketso.setCity("Emalahleni");
        koketso.setState("Mpumalanga");
        koketso.setZip("1039");
        Publisher koketsoSaved = publisherRepository.save(koketso);
        ddd.setPublisher(koketsoSaved);
        noEJB.setPublisher(koketsoSaved);

        eric.getBooks().add(ddd);
        rod.getBooks().add(noEJB);
        ddd.getAuthors().add(eric);
        noEJB.getAuthors().add(rod);

        authorRepository.save(eric);
        authorRepository.save(rod);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);



        System.out.println("In Bootstrap");
        System.out.println("Author count = " + authorRepository.count());
        System.out.println("Book count = " + bookRepository.count());
        System.out.println("Publisher count = " + publisherRepository.count());







    }
}
