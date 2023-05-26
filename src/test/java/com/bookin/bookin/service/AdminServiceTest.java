package com.bookin.bookin.service;

import com.bookin.bookin.audit.AuditEntity;
import com.bookin.bookin.dao.bookRepository;
import com.bookin.bookin.entity.Book;
import com.bookin.bookin.kafka.Producer;
import com.bookin.bookin.requestmodels.AddBookRequest;
import com.bookin.bookin.util.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFutureAdapter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AdminServiceTest {

    @Mock
    private bookRepository bookRepository;

    @Mock
    private JsonParser jsonParser;

    @Mock
    private Producer producer;

    @InjectMocks
    private AdminService adminService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testIncreaseBookQuantity() throws Exception {
        when(jsonParser.stringify((AuditEntity) any())).thenReturn("Stringify");

        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCategory("Category");
        book.setCopies(1);
        book.setCopiesAvailable(1);
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setImg("Img");
        book.setTitle("Dr");
        when(bookRepository.save((Book) any())).thenReturn(book);
        when(bookRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> adminService.increaseBookQuantity(123L));
        verify(bookRepository).findById((Long) any());
    }


    @Test
    void testDecreaseBookQuantity() throws Exception {
        when(jsonParser.stringify((AuditEntity) any())).thenReturn("Stringify");

        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCategory("Category");
        book.setCopies(1);
        book.setCopiesAvailable(1);
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setImg("Img");
        book.setTitle("Dr");
        when(bookRepository.save((Book) any())).thenReturn(book);
        when(bookRepository.findById((Long) any())).thenReturn(Optional.empty());
        Book book1 = mock(Book.class);
        when(book1.getCopies()).thenReturn(1);
        when(book1.getCopiesAvailable()).thenReturn(1);
        doNothing().when(book1).setAuthor((String) any());
        doNothing().when(book1).setCategory((String) any());
        doNothing().when(book1).setCopies((Integer) any());
        doNothing().when(book1).setCopiesAvailable((Integer) any());
        doNothing().when(book1).setDescription((String) any());
        doNothing().when(book1).setId((Long) any());
        doNothing().when(book1).setImg((String) any());
        doNothing().when(book1).setTitle((String) any());
        book1.setAuthor("JaneDoe");
        book1.setCategory("Category");
        book1.setCopies(1);
        book1.setCopiesAvailable(1);
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setImg("Img");
        book1.setTitle("Dr");
        assertThrows(Exception.class, () -> adminService.decreaseBookQuantity(123L));
        verify(bookRepository).findById((Long) any());
        verify(book1).setAuthor((String) any());
        verify(book1).setCategory((String) any());
        verify(book1).setCopies((Integer) any());
        verify(book1).setCopiesAvailable((Integer) any());
        verify(book1).setDescription((String) any());
        verify(book1).setId((Long) any());
        verify(book1).setImg((String) any());
        verify(book1).setTitle((String) any());
    }


    @Test
    void testDeleteBook() throws Exception {
        when(jsonParser.stringify((AuditEntity) any())).thenReturn("Stringify");
        doNothing().when(bookRepository).delete((Book) any());
        when(bookRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> adminService.deleteBook(123L));
        verify(bookRepository).findById((Long) any());
    }
}

