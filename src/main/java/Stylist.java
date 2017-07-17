

import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private int id;
  private String image;


  public Stylist(String name, String image) {
    this.name = name;
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getImage() {
    return image;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name, image FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
     }
   }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where stylistId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
     if (!(otherStylist instanceof Stylist)) {
       return false;
     } else {
       Stylist newStylist = (Stylist) otherStylist;
       return this.getName().equals(newStylist.getName()) &&
              this.getImage().equals(newStylist.getImage()) &&
              this.getId() == newStylist.getId();
     }
   }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name, image) VALUES (:name, :image);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("image", this.image)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stylists WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
