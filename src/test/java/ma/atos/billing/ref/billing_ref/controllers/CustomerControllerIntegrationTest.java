package ma.atos.billing.ref.billing_ref.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // ========== CREATE TESTS ==========
    @Test
    public void testCreateCustomer() throws Exception {
        String customerJson = "{\"nom\":\"Dupont\",\"prenom\":\"Jean\",\"firstName\":\"Jean\",\"lastName\":\"Dupont\",\"email\":\"jean" + System.currentTimeMillis() + "@example.com\",\"adresse\":\"123 Rue de Paris\",\"paymentType\":\"VIREMENT\"}";

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nom").value("Dupont"));
    }

    // ========== GET TESTS ==========
    @Test
    public void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        String customerJson = "{\"nom\":\"Martin\",\"prenom\":\"Paul\",\"firstName\":\"Paul\",\"lastName\":\"Martin\",\"email\":\"paul" + System.currentTimeMillis() + "@example.com\",\"adresse\":\"456 Rue de Lyon\",\"paymentType\":\"CHEQUE\"}";

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // ========== UPDATE TESTS ==========
    @Test
    public void testUpdateCustomer() throws Exception {
        String customerJson = "{\"nom\":\"Dupont\",\"prenom\":\"Jean\",\"firstName\":\"Jean\",\"lastName\":\"Dupont\",\"email\":\"dupontjean" + System.currentTimeMillis() + "@example.com\",\"adresse\":\"123 Rue de Paris\",\"paymentType\":\"VIREMENT\"}";

        // 1. CRÉE le customer et récupère l'ID
        MvcResult createResult = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andReturn();

        // 2. EXTRAIT l'ID de la réponse JSON
        String responseBody = createResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long customerId = jsonNode.get("id").asLong();  // ← L'ID créé !

        // 3. MODIFIE le customer avec  ID
        String updatedJson = "{\"nom\":\"Durand\",\"prenom\":\"Pierre\",\"firstName\":\"Pierre\",\"lastName\":\"Durand\",\"email\":\"durandpierre" + System.currentTimeMillis() + "@example.com\",\"adresse\":\"456 Rue de Lyon\",\"paymentType\":\"CHEQUE\"}";

        mockMvc.perform(put("/customers/" + customerId)  // ← Utilise l'ID récupéré
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Durand"));
    }

    // ========== DELETE TESTS ==========
    @Test
    public void testDeleteCustomer() throws Exception {
        String customerJson = "{\"nom\":\"Bernard\",\"prenom\":\"Louis\",\"firstName\":\"Louis\",\"lastName\":\"Bernard\",\"email\":\"louisbernadrd" + System.currentTimeMillis() + "@example.com\",\"adresse\":\"789 Rue de Marseille\",\"paymentType\":\"ESPECE\"}";

        // 1. CRÉE le customer et récupère l'ID
        MvcResult createResult = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andReturn();

        // 2. EXTRAIT l'ID de la réponse JSON
        String responseBody = createResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long customerId = jsonNode.get("id").asLong();  // ← L'ID créé !

        // 3. SUPPRIME le customer avec ID
        mockMvc.perform(delete("/customers/" + customerId)  // ← Utilise l'ID récupéré
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // ========== SEARCH TESTS ==========
    @Test
    public void testFindByNom() throws Exception {
        String customerJson = "{\"nom\":\"Garcia\",\"prenom\":\"Maria\",\"firstName\":\"Maria\",\"lastName\":\"Garcia\",\"email\":\"mariagarcia" + System.currentTimeMillis() + "@example.com\",\"adresse\":\"321 Rue de Toulouse\",\"paymentType\":\"VIREMENT\"}";

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/customers/search/nom/Garcia")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByPaymentType() throws Exception {
        String customerJson = "{\"nom\":\"Rodriguez\",\"prenom\":\"Juan\",\"firstName\":\"Juan\",\"lastName\":\"Rodriguez\",\"email\":\"juanrodriguez" + System.currentTimeMillis() + "@example.com\",\"adresse\":\"654 Rue de Nice\",\"paymentType\":\"ESPECE\"}";

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/customers/search/payment/ESPECE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}