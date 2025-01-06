package com.example.spring6webapp.bootstrap;

import com.example.spring6webapp.domain.Author;
import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.domain.Publisher;
import com.example.spring6webapp.repositories.AuthorRepository;
import com.example.spring6webapp.repositories.BookRepository;
import com.example.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Dan");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("12345");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rob = new Author();
        rob.setFirstName("Rob");
        rob.setLastName("Dan");

        Book noEJB = new Book();
        noEJB.setTitle("No-EJB");
        noEJB.setIsbn("1234598");

        Author robSaved = authorRepository.save(rob);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        robSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(robSaved);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("vamshi");
        publisher.setAddress("123Main");
        publisher.setCity("New York");
        Publisher savedPublisher = publisherRepository.save(publisher);

        dddSaved.setPublisher(savedPublisher);
        noEJBSaved.setPublisher(savedPublisher);



        authorRepository.save(robSaved);
        authorRepository.save(ericSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

            System.out.println("In BootStrap");
            System.out.println("Author Count:" + authorRepository.count());
            System.out.println("Book Count:" + bookRepository.count());




        System.out.println("Publisher Count:" + publisherRepository.count());

    }
}
