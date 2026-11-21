package database;

import br.com.wilsouuza.database.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionFactoryTest {

    @Test
    void connectionsMustBeIndependent() throws SQLException {
        try(Connection conn1 = ConnectionFactory.getConnection();
            Connection conn2 = ConnectionFactory.getConnection()){

            assertNotSame(conn1,conn2);
            assertFalse(conn1.isClosed());
            assertFalse(conn2.isClosed());

            conn1.close();

            assertTrue(conn1.isClosed());
            assertFalse(conn2.isClosed());
        }
    }
}
