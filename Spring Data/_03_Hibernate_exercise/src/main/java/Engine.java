import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class Engine implements Runnable {
    private final EntityManager entityManager;

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void run() {
        //TODO: WRITE THE LOGIC
        try {
            exercise_08();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // transform the name of all towns to uppercase if their length > 5.
    private void exercise_02(){
        this.entityManager.getTransaction().begin();

        List<Town> towns = this.entityManager
                                .createQuery("FROM Town",Town.class)
                                .getResultList();

        for (Town town : towns){
            if (town.getName().length() > 5){
                town.setName(town.getName().toUpperCase());
                this.entityManager.merge(town);
            }
        }
       this.entityManager.getTransaction().commit();
    }

    // check if the given name exist in the Employees database.
    private void exercise_03() throws IOException {
        this.entityManager.getTransaction().begin();

        String[] input = READER.readLine().split("\\s+");
        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE firstName = :first_name AND lastName = :last_name", Employee.class)
                .setParameter("first_name", input[0])
                .setParameter("last_name", input[1])
                .getResultList();

        System.out.println(!employees.isEmpty() ? "yes" : "no");
        this.entityManager.getTransaction().commit();
    }

    // print the first name of all employees which have salary > 50 000.
    private void exercise_04(){
        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();

        for (Employee employee : employees){
            String fullName = employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName();
            System.out.println(fullName);
        }

        this.entityManager.getTransaction().commit();
    }

    // extract all employees from Research and Development.
    private void exercise_05(){
        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM Employee e JOIN e.department d WHERE d.id = 6 ORDER BY e.salary ASC, e.id ASC ", Employee.class)
                .getResultList();

        for (Employee employee : employees){
            System.out.printf("%s %s form Research and Development - $%.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary());
        }
    }

    // adds new address "Vitoshka 15" and set it to the guy with lastname Nakov.
    private void exercise_06() throws IOException {
        this.entityManager.getTransaction().begin();

        Town town = entityManager.find(Town.class, 32);
        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);
        entityManager.persist(address);

        String lastName = READER.readLine();
        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE lastName = :last_name", Employee.class)
                .setParameter("last_name", lastName)
                .getResultList();

        if (!employees.isEmpty()){
            Employee employee = employees.get(0);
            employee.setAddress(address);
            entityManager.persist(employee);
            this.entityManager.merge(address);
            this.entityManager.merge(employee);
        }
        this.entityManager.getTransaction().commit();
    }

    // find all addresses ordered by number of employees who live there.
    private void exercise_07(){
        List<Address> addresses = entityManager.createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : addresses){
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size());
        }
    }

    // receive employee id and print the full name, the job title and the projects
    private void exercise_08() throws IOException {
        Employee employee = entityManager.createQuery("FROM Employee e WHERE e.id = ?1", Employee.class)
                .setParameter(1, Integer.parseInt(READER.readLine()))
                .getSingleResult();

        System.out.printf("%s %s - %s%n", employee.getFirstName()
                ,employee.getLastName()
                ,employee.getJobTitle());

        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("   %s%n", p.getName()));
    }
}
