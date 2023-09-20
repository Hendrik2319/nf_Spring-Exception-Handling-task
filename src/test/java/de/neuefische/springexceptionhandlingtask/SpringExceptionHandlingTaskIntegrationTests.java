package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringExceptionHandlingTaskIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetAnimalSpecies_getsDog() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/animals/dog")
                )

                // Then
                .andExpect(status().isOk())
                .andExpect(content().string("dog"));
    }

    @Test
    void whenGetAnimalSpecies_getsNoDog() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/animals/cat")
                )

                // Then
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json("""
                        {
                             "error": "IllegalArgumentException: Only 'dog' is allowed"
                        }
                        """));
    }

    @Test
    void getAllAnimals() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/animals")
                )

                // Then
                .andExpect(status().is(404))
                .andExpect(content().json("""
                        {
                             "error": "NoSuchElementException: No Animals found"
                        }
                        """));
    }

    @Test
    void whenGetCarBrand_getsPorsche() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/cars/porsche")
                )

                // Then
                .andExpect(status().isOk())
                .andExpect(content().string("porsche"));
    }

    @Test
    void whenGetCarBrand_getsNoPorsche() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/cars/trabant")
                )

                // Then
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json("""
                        {
                            "error": "IllegalArgumentException: Only 'porsche' allowed"
                        }
                        """));
    }

    @Test
    void getAllCars() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/cars")
                )

                // Then
                .andExpect(status().is(404))
                .andExpect(content().json("""
                        {
                            "error": "NoSuchElementException: No Cars found"
                        }
                        """));
    }

}
