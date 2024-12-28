

import com.nttdata.backend.app.domain.dto.UserDTO;
import com.nttdata.backend.app.domain.service.ClientService;
import com.nttdata.backend.app.exception.NotFoundException;
import com.nttdata.backend.app.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
 class ClientServiceTest {

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService();
    }

    @Test
    void testGetClientSuccess() {
        UserDTO client = clientService.getClient("C", "23445322");
        assertNotNull(client);
        assertEquals("Juan", client.getPrimerNombre());
        assertEquals("Carlos", client.getSegundoNombre());
        assertEquals("Perez", client.getPrimerApellido());
        assertEquals("Gomez", client.getSegundoApellido());
        assertEquals("1234567890", client.getTelefono());
        assertEquals("Calle 123", client.getDireccion());
        assertEquals("23445322", client.getNumeroCedula());
    }

    @Test
    void testGetClientNotFound() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            clientService.getClient("C", "0000000000");
        });
        assertEquals("Client not found.", exception.getMessage());
    }

    @Test
    void testGetClientBadRequest() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            clientService.getClient("X", "1234567890");
        });
        assertEquals("Invalid document type. It must be 'C' or 'P'.", exception.getMessage());
    }
}
