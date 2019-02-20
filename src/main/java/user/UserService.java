package user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserService {

    private final String filePath = "users.json";

    private ObjectMapper objectMapper = new ObjectMapper();

    public User createUser(User user) {

        try {
            //Pobierz wszystkich Userow z pliku
            ArrayList<User> users = getUsers();
            //Dodaj kolejnego Usera
            users.add(user);
            //Zapisz ArrayListe do pliku JSON
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println(e);
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        try {
            //Odczytaj plik users JSON jako ArrayListe Userów
            ArrayList<User> users = objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<User>>() {});
            return users;
        } catch (IOException e) {
            System.out.println(e);
            //Jesli plik nie istniał utwórz pustą ArrayListe Userów
            return new ArrayList<User>();
        }
    }
}
