package com.jakubmikula.demo.bootstrap;

import com.jakubmikula.demo.domain.Author;
import com.jakubmikula.demo.domain.Book;
import com.jakubmikula.demo.domain.Publisher;
import com.jakubmikula.demo.repositories.AuthorRepository;
import com.jakubmikula.demo.repositories.BookRepository;
import com.jakubmikula.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("Mikula Publisher");
        publisher.setAddressLine1("ul. Nieborowska 36/4");
        publisher.setCity("Gdansk");
        publisher.setState("Pomorskie");
        publisher.setZip("80-034");

        Author sapkowski = new Author("Andrzej", "Sapkowski");

        Book wiedzmin = new Book("Wiedzmin", "0000001");
        Book narrenturm = new Book("Narrenturm", "123456");

        bookRepository.save(wiedzmin);
        bookRepository.save(narrenturm);
        authorRepository.save(sapkowski);

        sapkowski.getBooks().add(wiedzmin);
        sapkowski.getBooks().add(narrenturm);
        wiedzmin.getAuthors().add(sapkowski);
        narrenturm.getAuthors().add(sapkowski);

        Author zafon = new Author("Carlos Ruiz", "Zafon");
        Book cien_wiatru = new Book("Cien wiatru", "123123");
        bookRepository.save(cien_wiatru);
        authorRepository.save(zafon);
        zafon.getBooks().add(cien_wiatru);
        cien_wiatru.getAuthors().add(zafon);

        wiedzmin.setPublisher(publisher);
        narrenturm.setPublisher(publisher);
        cien_wiatru.setPublisher(publisher);

        publisher.getBooks().add(wiedzmin);
        publisher.getBooks().add(narrenturm);
        publisher.getBooks().add(cien_wiatru);

        publisherRepository.save(publisher);

        System.out.println("Number of books in repo: " + bookRepository.count());
        System.out.println("Number of authors in repo: " + authorRepository.count());
        System.out.println("Number of publisher in repo: " + publisherRepository.count());

    }
}
