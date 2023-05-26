package com.bookin.bookin.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookin.bookin.service.CustomUserDetailService;
import com.bookin.bookin.util.JwtUtil;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

class JwtAuthenticationFilterTest {
  @Mock private CustomUserDetailService customUserDetailService;
  @Mock private JwtUtil jwtUtil;
  @InjectMocks private JwtAuthenticationFilter jwtAuthenticationFilter;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testDoFilterInternal() throws IOException, ServletException {
    MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
    Response response = new Response();
    FilterChain filterChain = mock(FilterChain.class);
    doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    jwtAuthenticationFilter.doFilterInternal(mockHttpServletRequest, response, filterChain);
    verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
    assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
    assertFalse(mockHttpServletRequest.isAsyncSupported());
    assertFalse(mockHttpServletRequest.isAsyncStarted());
    assertTrue(mockHttpServletRequest.isActive());
    assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
    assertEquals("", mockHttpServletRequest.getServletPath());
    assertEquals(80, mockHttpServletRequest.getServerPort());
    assertEquals("localhost", mockHttpServletRequest.getServerName());
    assertEquals("http", mockHttpServletRequest.getScheme());
    assertEquals("", mockHttpServletRequest.getRequestURI());
    assertEquals(80, mockHttpServletRequest.getRemotePort());
    assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
    assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
    assertEquals("", mockHttpServletRequest.getMethod());
    assertEquals(80, mockHttpServletRequest.getLocalPort());
    assertEquals("localhost", mockHttpServletRequest.getLocalName());
    assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
    assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
    assertEquals("", mockHttpServletRequest.getContextPath());
    assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
  }

  @Test
  void testDoFilterInternal2() throws IOException, ServletException {
    DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest =
        mock(DefaultMultipartHttpServletRequest.class);
    when(defaultMultipartHttpServletRequest.getHeader((String) any()))
        .thenReturn("https://example.org/example");
    Response response = new Response();
    FilterChain filterChain = mock(FilterChain.class);
    doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    jwtAuthenticationFilter.doFilterInternal(
        defaultMultipartHttpServletRequest, response, filterChain);
    verify(defaultMultipartHttpServletRequest).getHeader((String) any());
    verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
  }
}
