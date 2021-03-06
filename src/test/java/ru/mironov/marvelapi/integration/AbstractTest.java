package ru.mironov.marvelapi.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

/**
 * @author mironovAlexanderJR
 * @since 01.02.2022
 */
@ActiveProfiles("TEST")
@AutoConfigureWebTestClient
@DirtiesContext(classMode = BEFORE_CLASS)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebTestClient webTestClient;

    protected String token;

    protected <T> T getClassPathResourceAsObject(String path, TypeReference<T> reference) {
        try {
            InputStream resource = new ClassPathResource(path).getInputStream();
            return objectMapper.readValue(resource, reference);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}
