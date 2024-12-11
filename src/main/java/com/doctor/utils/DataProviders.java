package com.doctor.utils;
import com.doctor.model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class DataProviders {
    //! Передаёт в тест набор строк
    @DataProvider
    public Iterator<Object[]> addContactString() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Name1", "LastName1", "1234567891", "email1@gmail.com", "Germany 1", "Description 1"});
        list.add(new Object[]{"Name2", "LastName2", "1234567892", "email2@gmail.com", "Germany 2", "Description 2"});
        list.add(new Object[]{"Name3", "LastName3", "1234567893", "email3@gmail.com", "Germany 3", "Description 3"});
        list.add(new Object[]{"Name4", "LastName4", "1234567894", "email4@gmail.com", "Germany 4", "Description 4"});
        return list.iterator();
    }
//
//    //! Передаёт в тест объекты класса Contact
//    @DataProvider
//    public Iterator<Object[]> addContactObject() {
//        List<Object[]> list = new ArrayList<>();
//        list.add(new Object[]{new Contact().setName("Name5").setLastName("LastName5").setPhone("1234567895").setEmail("email5@gmail.com").setAddress("Germany 5").setDescription("Description 5")});
//        list.add(new Object[]{new Contact().setName("Name6").setLastName("LastName6").setPhone("1234567896").setEmail("email6@gmail.com").setAddress("Germany 6")});
//        list.add(new Object[]{new Contact().setName("Name7").setLastName("LastName7").setPhone("1234567897").setEmail("email7@gmail.com").setAddress("Germany 7")});
//        list.add(new Object[]{new Contact().setName("Name8").setLastName("LastName8").setPhone("1234567898").setEmail("email8@gmail.com").setAddress("Germany 8")});
//        return list.iterator();
//    }
//! DataProvider для регистрации пользователя
@DataProvider
public Iterator<Object[]> userRegistrationData() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{"John", "Doe", System.currentTimeMillis() + "@t.test", "1234567890",  "Password123"}); // Valid data
    list.add(new Object[]{"Alice", "Smith",System.currentTimeMillis()   + "alice.smith@t.test", "1234567890", "SecurePass1"}); // Valid with phone
    list.add(new Object[]{"Bob", "Brown", System.currentTimeMillis()+"bob.brown@t.test", "123456789", "Password123"}); // Invalid email
    list.add(new Object[]{"Chris", "White",System.currentTimeMillis() +  "chris.white@t.test", "1256788899", "Password123"}); // Invalid password
    return list.iterator();
}
    @DataProvider
    public Iterator<Object[]> userRegistrationFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/registration.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .setName(split[0])
                    .setLastName(split[1])
                    .setEmail(split[2])
                    .setPhone(split[3])
                    .setPassword(split[4])

            });
            line = reader.readLine();
        }
        // Закрываем чтение файла
        reader.close();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/login.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .setName(split[0])
                    .setLastName(split[1])
            });
            line = reader.readLine();
        }
        reader.close();
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> userUpdateData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"updatedUser1", "One", "1234567890"});
        list.add(new Object[]{"updatedUser2", "Two",  "0987654321"});
        list.add(new Object[]{"updatedUser3", "Three", "12345877655"});

        return list.iterator();
    }

    @DataProvider(name = "userUpdateFromCSV")
    public Iterator<Object[]> userUpdateFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/update.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .setName(split[0])
                    .setLastName(split[1])
                    .setPhone(split[2])

            });
            line = reader.readLine();
        }
        // Close the file reader
        reader.close();
        return list.iterator();
    }

    // Static method to create a User object for testing
    public static User createUser(String name, String email, String phone, String password) {
        return new User().setName(name).setEmail(email).setPhone(phone).setPassword(password);
    }
}




