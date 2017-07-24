import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "anya", "anya");
  }
    @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Client firstClient = new Client("Anna Timofeeva", 1);
    Client secondClient = new Client("Anna Timofeeva", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Client myClient = new Client("Anna Timofeeva" ,1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
    public void all_returnsAllInstancesOfTask_true() {
      Client firstClient = new Client("Anna Timofeeva", 1);
      firstClient.save();
      Client secondClient = new Client("Kirill Timofeev", 1);
      secondClient.save();
      assertEquals(true, Client.all().get(0).equals(firstClient));
      assertEquals(true, Client.all().get(1).equals(secondClient));
    }

  @Test
    public void save_assignsIdToObject() {
      Client myClient = new Client("Anna Timofeeva", 1);
      myClient.save();
      Client savedClient = Client.all().get(0);
      assertEquals(myClient.getId(), savedClient.getId());
    }

  @Test
    public void getId_clientsInstantiateWithAnID() {
      Client myClient = new Client("Anna Timofeeva", 1);
      myClient.save();
      assertTrue(myClient.getId() > 0);
    }

  @Test
    public void find_returnsTaskWithSameId_secondClient() {
      Client firstClient = new Client("Anna Timofeeva", 1);
      firstClient.save();
      Client secondClient = new Client("Kirill Timofeev", 1);
      secondClient.save();
      assertEquals(Client.find(secondClient.getId()), secondClient);
    }
  @Test
    public void save_savesStylistIdIntoDB_true() {
      Stylist myStylist = new Stylist("Anna Timofeeva");
      myStylist.save();
      Client myClient = new Client("Kirill Timofeev", myStylist.getId());
      myClient.save();
      Client savedClient = Client.find(myClient.getId());
      assertEquals(savedClient.getStylistId(), myStylist.getId());
    }
  }
