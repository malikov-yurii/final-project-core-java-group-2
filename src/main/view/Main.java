package main.view;

import main.controller.InterfaceAPIImpl;
import main.dao.RoomDAOImpl;
import main.datasourse.TestData;
import main.model.CurrentUser;
import main.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException {

        TestData.initializeDAOWithTestData();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
/*
//      Я пока оставлю это здесь, пусть будет как один из вариантов, так как я его еще днем накидал
//      Но я не смотрел последний вебинар, поэтому правильная реализация авторизации за Михаилом Косицким.
        while (true) {
            System.out.print("Please authorize to continue. \nEnter your login : ");
            String login = br.readLine();
            System.out.print("\nEnter your password : ");
            String password = br.readLine();
            User authorizedUser = new User(login, password);
            if (UserDAOImpl.getInstance().getUsers().contains(authorizedUser)) {
                System.out.println("Access allowed.");
                break;
            }
            System.out.println("Access denied.");
        }
*/
        //-----------------Start Authorization--------------------------------
        InterfaceAPIImpl interfaceAPI = new InterfaceAPIImpl();
        System.out.print("Введите имя пользователя без пробелов(иначе будет взято только первое слово):");
        Scanner scanner = new Scanner(System.in);
        String readUser = scanner.next();
        System.out.print("Введите пароль:");
        String readPassword = scanner.next();
        interfaceAPI.registerUser(new User(readUser, readPassword));
        //scanner.close(); /*Вызывает закрытие потока ввода данных. Подходит для теста, но не для прода*/
        System.out.println("The current User is: "+ CurrentUser.getCurrentUser());
        //-----------------------Finish Authorization------------------------

        String choice1 = "";
        //InterfaceAPIImpl interfaceAPI = new InterfaceAPIImpl();


        while (!choice1.equalsIgnoreCase("0")){
            System.out.println("\n---------Menu---------\n\n1. Find hotel by name\n2. Find hotel by city\n3. Book room\n4. Cancel reservation\n5. Find room by params.\n0. Quit.\n");
            choice1 = br.readLine();

            switch(choice1){
                case "1":
                {

                    // MethodsForMain.clrscr();
                    System.out.println("Enter name of hotel: ");    //find hotel by name
                    String hotname = br.readLine();
                    System.out.println("Rezult of your request: "+interfaceAPI.findHotelbyName(hotname));
                    break;
                }
                case "2":
                {
                    //MethodsForMain.clrscr();
                    System.out.println("\nEnter city of hotel: ");  //find hotel by city
                    String hotcity = br.readLine();
                    System.out.println("Rezult of your request: "+interfaceAPI.findHotelbyCity(hotcity));
                    break;
                }
                case "3":
                {
                    MethodsForMain.clrscr();                    //book room
                    //MethodsForMain.PrintFreeRooms();  //print available rooms
                    interfaceAPI.bookRoom(MethodsForMain.RoomId(br), MethodsForMain.UserId(br), MethodsForMain.HotelId(br));
                    break;


                }
                case "4":
                {
                    MethodsForMain.clrscr();                //cancel reserv
                    //MethodsForMain.PrintUserRooms(); //print rooms of current user
                    interfaceAPI.cancelReservation(MethodsForMain.RoomId(br), MethodsForMain.UserId(br), MethodsForMain.HotelId(br));
                    break;
                }
                case "5":
                {
                    Map<String, String> map = new HashMap<>();      //find room by params

                    //interfaceAPI.findRoom();
                    break;
                }
                case "0":
                {
                    //MethodsForMain.clrscr();
                    System.out.println("Good Bye!");            //exit
                    break;
                }
                default:
                    System.out.print("\nPlease, choose 1-5 or 0 : ");
            }
        }

        br.close();
        /*while (true) {
            System.out.print("\nEnter \"1\" to find hotel by name.");
            System.out.print("\nEnter \"2\" to find hotel by city.");
            System.out.print("\nEnter \"3\" to book room.");
            System.out.print("\nEnter \"4\" to cancel reservation.");
            System.out.print("\nEnter \"5\" to find room by params.");
            System.out.print("\nEnter \"Q\" or \"q\" to quit.");
            System.out.print("\n\nEnter your choice : ");
            String choice = br.readLine();
            if ("Q".equals(choice) || "q".equals(choice)) {
                System.out.println("Application terminated.");
                break;
            }

            while (true) {
                switch (choice) {
                    case "1": /* find hotel by name *//*
                        break;
                    case "2": /* find hotel by city *//*
                        break;
                    case "3": /* book room. *//*
                        break;
                    case "4": /* cancel reservation. *//*
                        break;
                    case "5": /* find room by params. *//*
                        break;
                    default:
                        System.out.print("\n\nNot correct Choice. Please enter {1-5} choice : ");
                        choice = br.readLine();
                }
            }
        }*/
    }
}
