import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

    public class Main {
        private static final String CONNECTION_STRING =
                "jdbc:mysql://localhost:3306/";
        private static final String DBNAME = "minions_db";
        private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private static Connection connection;

        public static void main(String[] args) throws SQLException, IOException {

            connection = getConnection();
            System.out.println("Enter ex num:");
            int exNum = Integer.parseInt(reader.readLine());
            switch (exNum) {
                case 2:
                    exTwo();
                case 3:
                    exThree();
                case 5:
                    exFive();
                case 6:
                    exSix();
                case 7:
                    exSeven();
                case 9:
                    exNine();
            }
        }

        private static void exSix() throws IOException, SQLException {
            System.out.println("Enter villain id:");
            int villainId = Integer.parseInt(reader.readLine());
            int affectedEntities = deleteMinionByVillainId(villainId);
            String villainName = findEntityNameById("villains", villainId);
            deleteVillainById(villainId);
            System.out.printf("%s was deleted%n" + "%d minions released%n" , villainName,affectedEntities);
        }

        private static void deleteVillainById(int villainId) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM villains WHERE id = ?");
            preparedStatement.setInt(1, villainId);
            preparedStatement.executeUpdate();
        }

        private static int deleteMinionByVillainId(int villainId) throws SQLException {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");

            preparedStatement.setInt(1, villainId);

            return  preparedStatement.executeUpdate();
        }

        private static void exNine() throws IOException, SQLException {
            System.out.println("Enter minion id :");
            int minionId = Integer.parseInt(reader.readLine());

            CallableStatement callableStatement = connection.prepareCall("CALL usp_get_older(?)");
            callableStatement.setInt(1, minionId);

            int affected = callableStatement.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name , age FROM minions");

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.printf("%s %d %n",resultSet.getString("name"),
                        resultSet.getInt("age"));
            }
        }

        private static void exSeven() throws SQLException {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT name FROM minions");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> allMinionsNames = new ArrayList<>();

            while (resultSet.next()) {
                allMinionsNames.add(resultSet.getString(1));
            }
            int start = 0;
            int end = allMinionsNames.size() - 1;
            for (int i = 0; i < allMinionsNames.size(); i++) {
                System.out.println(i % 2 == 0 ? allMinionsNames.get(start ++) : allMinionsNames.get(end --));
            }

        }

        private static void exFive() throws IOException, SQLException {
            System.out.println("Enter country name:");
            String countryName = reader.readLine();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE towns SET name = UPPER(NAME) WHERE country = ?");
            preparedStatement.setString(1, countryName);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No town names were affected.");
                return;
            }
            System.out.printf("%d town names were affected.%n", affectedRows);
            PreparedStatement preparedStatementTowns = connection.prepareStatement("SELECT name FROM towns WHERE country = ?");

            preparedStatementTowns.setString(1, countryName);
            ResultSet resultSet = preparedStatementTowns.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        }

        private static void exThree() throws SQLException, IOException {
            System.out.println("Enter villain id:");
            int villainId = Integer.parseInt(reader.readLine());

//        String villainName = findVillainNameById(villainId);
            String villainName = findEntityNameById("villains", villainId);

            System.out.println("Villain:" + villainName);

            getAllMinionsByVillainId(villainId);
        }

        private static Set<String> getAllMinionsByVillainId(int villainId) throws SQLException {
            Set<String> result = new LinkedHashSet<>();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT m.name , m.age FROM minions m " +
                            "JOIN minions_villains mv on m.id = mv.minion_id " +
                            "WHERE mv.villain_id = ?;");

            preparedStatement.setInt(1, villainId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                result.add(String.format("%d. %s %d%n", ++counter, resultSet.getString("name"), resultSet.getInt("age")));
            }
            return result;
        }

        private static String findEntityNameById(String tableName, int entityId) throws SQLException {
            String query = String.format("SELECT name FROM %s WHERE id =? ", tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString(1);
        }

        private static String findVillainNameById(int villainId) throws SQLException {

            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT name FROM villains WHERE id = ?");
            preparedStatement.setInt(1, villainId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("name");
        }

        private static void exTwo() throws SQLException {

            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "SELECT v.name , COUNT(DISTINCT mv.minion_id)  as m_count FROM villains  v " +
                                    "JOIN minions_villains mv on v.id = mv.villain_id " +
                                    "GROUP BY v.name " +
                                    "HAVING m_count > ?;");


            preparedStatement.setInt(1, 15);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s %d %n", resultSet.getString(1),
                        resultSet.getInt(2));
            }

        }

        private static Connection getConnection() throws IOException, SQLException {
            System.out.println("Enter user:");
            String user = reader.readLine();
            System.out.println("Enter password");
            String password = reader.readLine();

            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);

            return DriverManager.getConnection(CONNECTION_STRING + DBNAME, properties);

        }
    }
