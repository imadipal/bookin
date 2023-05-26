package com.bookin.bookin.requestmodels;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AuthorityModelTest {

    @Test
    void testConstructor() {
        AuthorityModel actualAuthorityModel = new AuthorityModel("JaneDoe");
        actualAuthorityModel.setAuthority("JaneDoe");
        assertEquals("JaneDoe", actualAuthorityModel.getAuthority());
    }
}

