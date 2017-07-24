import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

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
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Anna Timofeeva");
    Stylist secondStylist = new Stylist("Anna Timofeeva");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Anna Timofeeva");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Anna Timofeeva");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Kirill Timofeev");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Anna Timofeeva");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void getId_stylistsInstantiateWithAnId_1() {
    Stylist testStylist = new Stylist("Anna Timofeeva");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Anna Timofeeva");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Kirill Timofeev");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
 }

 @Test
  public void getClients_retrievesALlClientsFromDatabase_clientsList() {
    Stylist myStylist = new Stylist("Anna Timofeeva");
    myStylist.save();
    Client firstClient = new Client("Kirill Timofeev", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Alena Golovina", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }




}
