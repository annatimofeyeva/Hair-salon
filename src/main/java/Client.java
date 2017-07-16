import java.util.List;
import org.sql2o.*;

public class Client{
  private String name;
  private int Id;

  public Client(String name){
    this.name = name;
    }

  public String getName(){
    return name;
  }

  public int getId(){
    return Id;
  }

  public static List<Client> all(){
    String sql = "select id, full_name from clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
    public boolean equals(Object otherClient){
      if (!(otherClient instanceof Client)) {
        return false;
      } else {
        Client newClient = (Client) otherClient;
        return this.getName().equals(newClient.getName());
    }
  }
}