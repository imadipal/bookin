package com.bookin.bookin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookin.bookin.GoogleBooksApi.GoogleApiWrapper;
import com.bookin.bookin.GoogleBooksApi.GoogleBooks;
import com.bookin.bookin.requestmodels.BookInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

class GoogleBooksServiceTest {
  @InjectMocks private GoogleBooksService googleBooksService;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetRestTemplate() {
    RestTemplate actualRestTemplate = googleBooksService.getRestTemplate();
    List<ClientHttpRequestInitializer> clientHttpRequestInitializers =
        actualRestTemplate.getClientHttpRequestInitializers();
    assertTrue(clientHttpRequestInitializers.isEmpty());
    UriTemplateHandler uriTemplateHandler = actualRestTemplate.getUriTemplateHandler();
    assertTrue(uriTemplateHandler instanceof DefaultUriBuilderFactory);
    assertTrue(actualRestTemplate.getRequestFactory() instanceof SimpleClientHttpRequestFactory);
    assertTrue(actualRestTemplate.getErrorHandler() instanceof DefaultResponseErrorHandler);
    List<HttpMessageConverter<?>> messageConverters = actualRestTemplate.getMessageConverters();
    assertEquals(7, messageConverters.size());
    assertEquals(clientHttpRequestInitializers, actualRestTemplate.getInterceptors());
    assertTrue(((DefaultUriBuilderFactory) uriTemplateHandler).getDefaultUriVariables().isEmpty());
    assertEquals(
        DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT,
        ((DefaultUriBuilderFactory) uriTemplateHandler).getEncodingMode());
    assertEquals(1, messageConverters.get(2).getSupportedMediaTypes().size());
    assertEquals(2, messageConverters.get(1).getSupportedMediaTypes().size());
    assertEquals(
        6,
        ((AllEncompassingFormHttpMessageConverter) messageConverters.get(4))
            .getPartConverters()
            .size());
    assertFalse(((Jaxb2RootElementHttpMessageConverter) messageConverters.get(5)).isSupportDtd());
    assertFalse(
        ((Jaxb2RootElementHttpMessageConverter) messageConverters.get(5))
            .isProcessExternalEntities());
    ObjectMapper objectMapper =
        ((MappingJackson2HttpMessageConverter) messageConverters.get(6)).getObjectMapper();
    assertNull(objectMapper.getPropertyNamingStrategy());
    assertTrue(objectMapper.getPolymorphicTypeValidator() instanceof LaissezFaireSubTypeValidator);
    assertSame(objectMapper.getJsonFactory(), objectMapper.getFactory());
    assertTrue(
        objectMapper.getDeserializationContext() instanceof DefaultDeserializationContext.Impl);
    assertTrue(objectMapper.getDateFormat() instanceof StdDateFormat);
    assertTrue(objectMapper.getSubtypeResolver() instanceof StdSubtypeResolver);
    assertTrue(objectMapper.getSerializerFactory() instanceof BeanSerializerFactory);
    assertTrue(
        objectMapper.getSerializerProviderInstance() instanceof DefaultSerializerProvider.Impl);
    assertTrue(objectMapper.getVisibilityChecker() instanceof VisibilityChecker.Std);
    assertTrue(objectMapper.getSerializerProvider() instanceof DefaultSerializerProvider.Impl);
  }

  @Test
  void testGetmodifiedbooks() {
    GoogleBooks googleBooks = new GoogleBooks();
    googleBooks.setItems(new ArrayList<>());
    googleBooks.setTotalItems(1000);
    assertTrue(googleBooksService.getmodifiedbooks(googleBooks).isEmpty());
  }

  @Test
  void testGetmodifiedbooks2() {
    GoogleBooks googleBooks = mock(GoogleBooks.class);
    when(googleBooks.getItems()).thenReturn(new ArrayList<>());
    doNothing().when(googleBooks).setItems((List<GoogleApiWrapper>) any());
    doNothing().when(googleBooks).setTotalItems(anyInt());
    googleBooks.setItems(new ArrayList<>());
    googleBooks.setTotalItems(1000);
    assertTrue(googleBooksService.getmodifiedbooks(googleBooks).isEmpty());
    verify(googleBooks).getItems();
    verify(googleBooks).setItems((List<GoogleApiWrapper>) any());
    verify(googleBooks).setTotalItems(anyInt());
  }

  @Test
  void testGetmodifiedbooks3() {
    GoogleApiWrapper GoogleApiWrapper = new GoogleApiWrapper();
    GoogleApiWrapper.setVolumeInfo(new BookInfo());

    ArrayList<GoogleApiWrapper> GoogleApiWrapperList = new ArrayList<>();
    GoogleApiWrapperList.add(GoogleApiWrapper);
    GoogleBooks googleBooks = mock(GoogleBooks.class);
    when(googleBooks.getItems()).thenReturn(GoogleApiWrapperList);
    doNothing().when(googleBooks).setItems((List<GoogleApiWrapper>) any());
    doNothing().when(googleBooks).setTotalItems(anyInt());
    googleBooks.setItems(new ArrayList<>());
    googleBooks.setTotalItems(1000);
    assertEquals(1, googleBooksService.getmodifiedbooks(googleBooks).size());
    verify(googleBooks).getItems();
    verify(googleBooks).setItems((List<GoogleApiWrapper>) any());
    verify(googleBooks).setTotalItems(anyInt());
  }

  @Test
  void testGetmodifiedbooks4() {
    GoogleApiWrapper GoogleApiWrapper = mock(GoogleApiWrapper.class);
    when(GoogleApiWrapper.getVolumeInfo()).thenReturn(null);
    doNothing().when(GoogleApiWrapper).setVolumeInfo((BookInfo) any());
    GoogleApiWrapper.setVolumeInfo(new BookInfo());

    ArrayList<GoogleApiWrapper> GoogleApiWrapperList = new ArrayList<>();
    GoogleApiWrapperList.add(GoogleApiWrapper);
    GoogleBooks googleBooks = mock(GoogleBooks.class);
    when(googleBooks.getItems()).thenReturn(GoogleApiWrapperList);
    doNothing().when(googleBooks).setItems((List<GoogleApiWrapper>) any());
    doNothing().when(googleBooks).setTotalItems(anyInt());
    googleBooks.setItems(new ArrayList<>());
    googleBooks.setTotalItems(1000);
    assertEquals(1, googleBooksService.getmodifiedbooks(googleBooks).size());
    verify(googleBooks).getItems();
    verify(googleBooks).setItems((List<GoogleApiWrapper>) any());
    verify(googleBooks).setTotalItems(anyInt());
    verify(GoogleApiWrapper, atLeast(1)).getVolumeInfo();
    verify(GoogleApiWrapper).setVolumeInfo((BookInfo) any());
  }
}
