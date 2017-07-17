import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist newStylist = new Stylist("Anna Timofeeva", "");
    assertEquals(true, newStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_JaneDoe() {
    Stylist newStylist = new Stylist("Anna Timofeeva", "");
    assertEquals("Anna Timofeeva", newStylist.getName());
  }

 @Test
 public void all_returnsAllInstancesOfStylist_true() {
   Stylist firstStylist = new Stylist("Anna Timofeeva", "");
   firstStylist.save();
   Stylist secondStylist = new Stylist("Kirill Timofeev", "");
   secondStylist.save();
   assertEquals(true, Stylist.all().get(0).equals(firstStylist));
   assertEquals(true, Stylist.all().get(1).equals(secondStylist));
 }

 @Test
 public void getId_stylistsInstantiateWithAnId_1() {
   Stylist newStylist = new Stylist("Anna Timofeeva", "");
   newStylist.save();
   assertTrue(newStylist.getId() > 0);
 }

 @Test
 public void find_returnsStylistWithSameId_secondStylist() {
   Stylist firstStylist = new Stylist("Anna Timofeeva", "");
   firstStylist.save();
   Stylist secondStylist = new Stylist("Kirill Timofeev", "");
   secondStylist.save();
   assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
 }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Anna Timofeeva", "");
    Stylist secondStylist = new Stylist("Anna Timofeeva", "");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesStylistsIntoDatabase_true() {
    Stylist newStylist = new Stylist("Anna Timofeeva", "");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist newStylist = new Stylist("Anna Timofeeva", "");
    newStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(newStylist.getId(), savedStylist.getId());
  }

  @Test
  public void update_updatesStylistName_true() {
    Stylist newStylist = new Stylist("Anna Timofeeva", "");
    newStylist.save();
    newStylist.update("Kirill Timofeev");
    assertEquals("Kirill Timofeev", Stylist.find(newStylist.getId()).getName());
  }

  @Test
  public void delete_deletesStylist_true() {
    Stylist newStylist = new Stylist("Anna Timofeeva", "");
    newStylist.save();
    int newStylistId = newStylist.getId();
    newStylist.delete();
    assertEquals(null, Stylist.find(newStylistId));
  }
}
