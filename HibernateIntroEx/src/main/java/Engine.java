

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private BufferedReader bufferedReader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        System.out.println("Select ex number:");
        try {
            int exNum = Integer.parseInt(bufferedReader.readLine());
            switch (exNum) {
                case 2:
                    changeCasingExTwo();
                case 3:
                    containsEmployeeExThree();
                case 4:
                    employeesWithSalaryOver50000ExFour();
                case 5:
                    employeesFromDepartmentExFive();
                case 6:
                    addingANewAddressAndUpdatingEmployeeExSix();
                case 7:
                    addressesWithEmployeeCountExSeven();
                case 8:
                    increaseSalaries();
                case 9:
                    employeesMaximumSalaries();
                case 10:
                    removeTowns();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private void removeTowns() throws IOException {
        System.out.println("Enter town name:");
        String townName = bufferedReader.readLine();

        Town town = entityManager
                .createQuery("SELECT t FROM Town t WHERE t.name = :t_name", Town.class)
                .setParameter("t_name", townName)
                .getSingleResult();


      int affectedRows =  removeAddressesByTownId(town.getId());
      entityManager.getTransaction().begin();
        entityManager.remove(town);
        entityManager.getTransaction().commit();
        System.out.printf("%d address in %s is deleted", affectedRows, townName);

    }

    private int removeAddressesByTownId(Integer id) {

        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a "+
                 "WHERE a.town.id = :p_id", Address.class)
                .setParameter("p_id" , id)
                        .getResultList();

        entityManager.getTransaction().begin();
        addresses.forEach(entityManager::remove);
        entityManager.getTransaction().commit();

        return addresses.size();
    }

    private void employeesMaximumSalaries() {
        List<Object[]> rows = entityManager.createNativeQuery("SELECT d.name,MAX(e.salary) AS m_salary FROM departments d " +
                        "join employees e on department_id = e.department_id " +
                        "GROUP BY d.name " +
                        "HAVING m_salary NOT BETWEEN 30000 AND 70000")
                .getResultList();
    }

    private void increaseSalaries() {
        entityManager.getTransaction().begin();
        int affectedRows = entityManager.createQuery("UPDATE Employee e " +
                        "SET e.salary = e.salary * 1.2" +
                        "WHERE e.department.id IN :ids")
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();

        entityManager.getTransaction().commit();
        System.out.println(affectedRows);
    }

    private void addressesWithEmployeeCountExSeven() {
        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a " +
                        "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses.forEach(address -> {
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(),
                    address.getTown() == null
                            ? "Unknown" : address.getTown().getName(),
                    address.getEmployees().size());
        });

    }

    private void addingANewAddressAndUpdatingEmployeeExSix() throws IOException {
        System.out.println("Enter employee last name:");
        String lastName = bufferedReader.readLine();

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.lastName = :l_name", Employee.class)
                .setParameter("l_name", lastName)
                .getSingleResult();

        Address address = createAddress("Vitoshka 15");

        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }

    private void employeesFromDepartmentExFive() {
        entityManager
                .createQuery("SELECT e FROM Employee  e " +
                        "WHERE e.department.name = :d_name " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .setParameter("d_name", "Research and Development")
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s from %s - $%.2f%n",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getDepartment().getName(),
                            employee.getSalary());
                });
    }

    private void employeesWithSalaryOver50000ExFour() {
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.salary > :min_salary", Employee.class)
                .setParameter("min_salary", BigDecimal.valueOf(50000L))
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    private void changeCasingExTwo() {
        entityManager.getTransaction().begin();
        int update = entityManager.createQuery("UPDATE Town t " +
                        "SET t.name = upper(t.name) " +
                        "WHERE LENGTH(t.name) <= 5 ")
                .executeUpdate();

        System.out.println(update);

        entityManager.getTransaction().commit();

    }

    private void containsEmployeeExThree() throws IOException {
        System.out.println("Enter employee full name:");
        String[] fullName = bufferedReader.readLine().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        Long singleResult = entityManager.createQuery("SELECT count(e) FROM Employee e " +
                                "WHERE e.firstName = :f_name AND e.lastName = :l_name",
                        Long.class)
                .setParameter("f_name", firstName)
                .setParameter("l_name", lastName)
                .getSingleResult();

        System.out.println(singleResult == 0
                ? "No" : "Yes");

    }

}