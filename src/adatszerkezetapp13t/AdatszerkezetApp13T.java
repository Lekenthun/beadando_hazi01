package adatszerkezetapp13t;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AdatszerkezetApp13T {
    public static boolean logged = false;
    public static String loggedUsername = "";
    
    public static Map<String, User> userek = new TreeMap<>();
    public static Map<String, Message> uzenetek = new TreeMap<>();
    
    public static void getEmail(String username, String password) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Kérek egy email címet: ");
        String email = br.readLine();
        //Set<User> userek = new HashSet<>();
        userek.put(username, new User(username, password, email));
        System.out.println("Sikeres regisztráció!");
        menu();
    }
    
    public static void getPassword(String username) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Kérek egy jelszót: ");
        String pass = br.readLine();
        if(pass.length() > 3) {
            getEmail(username, pass);
        } else {
            System.out.println("Legalább 3 karakteresnek kell lennie a jelszónak!");
            getPassword(username);
        }
    }
    
    public static void getUserName() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Kérek egy felhasználónevet: ");
        String username = br.readLine();
        if(username.length() > 3) {
            getPassword(username);
        } else {
            System.out.println("Legalább 3 karakteresnek kell lennie a felhasználónévnek!");
            getUserName();
        }
    }
    
    public static void getLoginName() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Kérek egy felhasználónevet: ");
        String username = br.readLine();
        if(userek.containsKey(username)) {
            System.out.println("Sikeres bejelentkezés!");
            logged = true;
            loggedUsername = username;
            menu();
        } else {
            System.out.println("Nem létező felhasználónév!");
            getLoginName();
        }
    }
    
    public static void getMessages() {
        Set<String> keys = uzenetek.keySet();
        for(String key: keys){
            System.out.println(uzenetek.get(key));
        }
    }
    
    public static void newMessage() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Kérek egy üzenetet: ");
        String msg = br.readLine();
        if(userek.containsKey(loggedUsername)) {
            User email = userek.get(loggedUsername);
            uzenetek.put(email.toString(), new Message(email.toString(), msg));
            System.out.println("Sikeresen felvettük az üzenetedet!");
            menu();
        } else {
            menu();
        }
    }
    
    public static void menu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        if(!logged) {
            System.out.print("Kérlek írd be a parancsot: (newuser, login) ");
            String input = br.readLine();

            if("newuser".equals(input)) {
                getUserName();
            } else if("login".equals(input)) {
                getLoginName();
            } else {
                menu();
            }
        } else {
            System.out.print("Kérlek írd be a parancsot: (getmsg, newmsg) ");
            String input = br.readLine();

            if("getmsg".equals(input)) {
                getMessages();
            } else if("newmsg".equals(input)) {
                newMessage();
            } else {
                menu();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        menu();  
    }
    
    static class User implements Comparable<User> {
        private String usernev;
        private String email;
        private String password;

        public User(String usernev, String password, String email) {
            this.usernev = usernev;
            this.password = password;
            this.email = email;
        }

        @Override
        public String toString() {
            return email;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + Objects.hashCode(this.usernev);
            hash = 23 * hash + Objects.hashCode(this.password);
            hash = 23 * hash + Objects.hashCode(this.email);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final User other = (User) obj;
            if (!Objects.equals(this.usernev, other.usernev)) {
                return false;
            }
            if (!Objects.equals(this.password, other.password)) {
                return false;
            }
            if (!Objects.equals(this.email, other.email)) {
                return false;
            }
            return true;
        }

        @Override
        public int compareTo(User t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
    
   static class Message implements Comparable<Message> {
        private String email;
        private String msg;

        public Message(String email, String uzi) {
            this.email = email;
            this.msg = uzi;
        }

        @Override
        public String toString() {
            return email + " (" + msg + ")";
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + Objects.hashCode(this.email);
            hash = 23 * hash + Objects.hashCode(this.msg);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Message other = (Message) obj;
            if (!Objects.equals(this.email, other.email)) {
                return false;
            }
            if (!Objects.equals(this.msg, other.msg)) {
                return false;
            }
            return true;
        }

        public int compareTo(User t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int compareTo(Message t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
    
}
