package main.view;

import main.dao.RoomDAOImpl;
import main.model.CurrentUser;
import main.model.Room;
import main.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladislav on 03.11.16.
 *
 * All methods that main use
 */
public class MethodsForMain {

    //MS: right not it just add 25 empty lines, little bit
    public final static void clrscr(){
        for(int clear = 0; clear < 50; clear++)
        {
            System.out.println() ;
        }
    }

    public final static long RoomId(BufferedReader br) throws IOException {
        System.out.println();
        System.out.println("Enter room's id: ");
        String roomid = br.readLine();
        return Integer.valueOf(roomid);
    }

   /* public final static long UserId(BufferedReader br) throws IOException {
        System.out.println("Enter user's id: ");
        String userid = br.readLine();
        return Integer.valueOf(userid);
    }*/

    public final static long HotelId(BufferedReader br) throws IOException {
        System.out.println("Enter hotel's id: ");
        String hotid = br.readLine();
        return Integer.valueOf(hotid);
    }

    public final static Map<String, String> Parameters(BufferedReader br) throws IOException {
        Map<String, String> map = new HashMap<>();
        System.out.println("Enter parameter's name for searching room (price, persons, id): ");
        String key = br.readLine();   // key - parameter name, value - parameter value
        if (key.equalsIgnoreCase("id"))
        {
            System.out.println("You want ID of hotel or ID of room?");
            key=br.readLine();
        }
        System.out.println("Enter parameter's value for searching room: ");
        String value = br.readLine();
        map.put(key, value);
        return map;
    }

    // prints rooms from db, return false if there are no free rooms
    public final static boolean PrintFreeRooms() throws IOException {
        boolean anyReserved = false;

        System.out.println("Available rooms:");
        Collection<Room> rooms = RoomDAOImpl.getRooms();
        for (Room room:rooms) {
            if(!room.isReserved()){
                anyReserved = true;
                System.out.println(room);
            }
        }
        if(!anyReserved){
            System.out.println("There are no free rooms");
        }
        return anyReserved;
    }

    //prints rooms reserved by CurrentUser, return false if there are no rooms reserved
    public final static boolean PrintUserRooms() throws IOException {
        boolean anyReserved = false;

        User currentUser = CurrentUser.getCurrentUser();
        Collection<Room> rooms = RoomDAOImpl.getRooms();
        System.out.println("Rooms reserved by " + currentUser.getName() + ":");

        for (Room room:rooms) {
            if (currentUser.equals(room.getUserReserved())){
                anyReserved = true;
                System.out.println(room);
            }
        }

        if(!anyReserved){
            System.out.println("There are no rooms reserved by " + currentUser.getName());
        }
        return anyReserved;
    }

    public final static boolean CheckUserReserv() throws IOException {
        boolean anyReserved = false;
        User currentUser = CurrentUser.getCurrentUser();
        Collection<Room> rooms = RoomDAOImpl.getRooms();

        for (Room room:rooms) {
            if (currentUser.equals(room.getUserReserved()))
                anyReserved = true;
        }
        return anyReserved;
    }

}
