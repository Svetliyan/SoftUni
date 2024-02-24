import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBApps {
    private static final DBTools DB_TOOLS = new DBTools("root", "12345678", "minions_db");

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        exercise_05();
    }

    public static void exercise_02() throws SQLException {
        String sql = "SELECT v.name, COUNT(mv.minion_id) AS count FROM villains v " +
                "JOIN minions_db.minions_villains mv on v.id = mv.villain_id " +
                "GROUP BY name" +
                " HAVING count > 15 ORDER BY count DESC";;

        ResultSet resultSet = DB_TOOLS.getConnection().createStatement().executeQuery(sql);

        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString(1), resultSet.getInt(2)).append(System.lineSeparator());
        }
    }

    public static void exercise_03() throws SQLException, IOException {
        int villain_id = Integer.parseInt(READER.readLine());

        String sql = "SELECT name FROM villains WHERE id = ?";
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villain_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()){
            System.out.printf("No villain with ID %d exists in database.", villain_id);
            return;
        }

        sql = "SELECT m.name, m.age FROM minions m " +
                "JOIN minions_villains mv ON m.id = mv.minion_id WHERE villain_id = ?";
        preparedStatement = DB_TOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villain_id);
        resultSet = preparedStatement.executeQuery();

        int index = 0;
        while (resultSet.next()){
            System.out.printf("%d. %s %d", ++index,
                    resultSet.getString("name"),
                    resultSet.getInt("age")).
                    append(System.lineSeparator());
        }
    }

    public static void exercise_04() throws IOException, SQLException {
        String[] minionsTokens = READER.readLine().split("\\s+");
        String minionName = minionsTokens[1];
        int minionAge = Integer.parseInt(minionsTokens[2]);
        String townName = minionsTokens[3];

        String villainName = READER.readLine().split("\\s+")[1];

        int townId = findTownIdByName(townName);
        if (townId == 0) {
            townId = createTown(townName);
            System.out.printf("Town %s was added to the database.%n",townName);
        }

        int minionId = createMinion(minionName, minionAge, townId);

        int villainId = findVillainByName(villainName);
        if (villainId == 0){
            villainId = createVillain(villainName);
            System.out.printf("Villain %s was added to the database.%n",villainName);
        }

        populateMinionsVillains(minionId, villainId);
        System.out.printf("Successfully added %s to be minion of %s",minionName, villainName);
    }

    private static void populateMinionsVillains(int minionId, int villainId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("INSERT INTO minions_villains VALUE (?, ?)");
        preparedStatement.setInt(1, minionId);
        preparedStatement.setInt(2, villainId);
        preparedStatement.executeUpdate();
    }

    private static int createVillain(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("INSERT INTO villains(name, evilness_factor) VALUE(?, 'evil')");
        preparedStatement.setString(1, villainName);
        preparedStatement.executeUpdate();

        preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM villains WHERE name = ?");
        preparedStatement.setString(1, villainName);
        preparedStatement.executeQuery();
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return  resultSet.getInt("id");
    }

    private static int findVillainByName(String villainName) throws SQLException {
        PreparedStatement preparedStatement =  DB_TOOLS.getConnection().prepareStatement("SELECT id FROM villains WHERE name = ?");
        preparedStatement.setString(1, villainName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt("id");
        }
        return 0;
    }

    private static int createMinion(String minionName, int minionAge, int townId) throws SQLException {
       PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("INSERT INTO minions(name, age, town_id) VALUE(?, ?, ?) ");
       preparedStatement.setString(1, minionName);
       preparedStatement.setInt(2, minionAge);
       preparedStatement.setInt(3, townId);
       preparedStatement.executeUpdate();

       preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM minions WHERE name = ?");
       preparedStatement.setString(1, minionName);
       ResultSet resultSet = preparedStatement.executeQuery();
       resultSet.next();

       return resultSet.getInt("id");
    }


    private static int createTown(String townName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("INSERT INTO towns(name) VALUE(?)");
        preparedStatement.setString(1, townName);
        preparedStatement.executeUpdate();

        preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM towns WHERE name = ?");
        preparedStatement.setString(1, townName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }


    private static Integer findTownIdByName(String townName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM towns WHERE name = ?");
        preparedStatement.setString(1, townName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }

        return 0;
    }

    private static void exercise_05() throws SQLException, IOException {
        String country = READER.readLine();

        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("UPDATE towns SET  name = UPPER(name) WHERE country = ?");
        preparedStatement.setString(1, country);
        int i = preparedStatement.executeUpdate();
        if (i == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", i);
            preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT towns.name FROM towns WHERE country = ?");
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> names = new ArrayList<>();
            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
            }

            System.out.printf("[%s]", String.join(", ", names));
        }
    }
}