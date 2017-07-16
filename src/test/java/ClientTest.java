import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "anya", "anya");
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
    public void Client_instantiatesCorrectly_true() {
      Client myClient = new Client("Anna Timofeeva");
      assertEquals(true, myClient instanceof Client);
    }
  @Test
    public void Client_getFullNname_Anna_Timofeeva(){
      Client myClient = new Client("Anna Timofeeva");
      assertEquals("Anna Timofeeva", myClient.getName());
    }

    @Test
   public void equals_returnsTrueIfNamesAretheSame() {
    Client firstClient = new Client("Anna Timofeeva");
    Client secondClient = new Client("Anna Timofeeva");
    assertTrue(firstClient.equals(secondClient));
  }
 }
