package ma.atos.billing.ref.billing_ref.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testCreateCustomer() throws Exception {
        String customerJson = "{\"nom\":\"Dupont\",\"prenom\":\"Jean\",\"firstName\":\"Jean\",\"lastName\":\"Dupont\",\"email\":\"jean@example.com\",\"adresse\":\"123 Rue de Paris\",\"paymentType\":\"VIREMENT\"}";

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nom").value("Dupont"));
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}