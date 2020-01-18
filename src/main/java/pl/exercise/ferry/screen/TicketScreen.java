package pl.exercise.ferry.screen;

import pl.exercise.ferry.Basket;
import pl.exercise.ferry.ticket.*;
import pl.exercise.ferry.ticket.personTicket.AdultTicket;
import pl.exercise.ferry.ticket.personTicket.SeniorTicket;
import pl.exercise.ferry.ticket.personTicket.YoungTicket;

import java.util.Scanner;

public class TicketScreen {

    private final Scanner scanner = new Scanner(System.in);
    Basket basket = new Basket();


    public void interact() {

        boolean repaetBuying = true;

        while (repaetBuying) {
            System.out.println("Wybierz bilet , który chcesz kupić");
            System.out.println("1. Osoba");
            System.out.println("2. Pojazd");
            String secondResponse = scanner.nextLine();
            if ("1".equals(secondResponse)) {
                System.out.println("Kupiłeś bilet dla człowieka. Wpisz jego wiek:");
                int age = scanner.nextInt();
                System.out.println("Podaj swoje imie i nazwisko");
                String owner = scanner.nextLine();
                Ticket ticket = paxType(age, owner);
                scanner.nextLine();
                System.out.println("Za bilet zapłacisz: " + ticket.getPrice() + " zł. Czy chcesz kupić kolejny bilet?");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("tak")) repaetBuying = true;
                if (answer.equalsIgnoreCase("nie")) repaetBuying = false;

            }
            if ("2".equals(secondResponse)) {
                System.out.println("Kupiłeś bilet dla pojazdu. Jaki to będzie pojazd? <CAR, BUS, BIKE, TRUCK>");
                String vehicle = scanner.nextLine();
                System.out.println("Podaj swoje imie i nazwisko");
                String owner = scanner.nextLine();
                Ticket ticket = parseVehicle(vehicle.toUpperCase(), owner);
                System.out.println("Za bilet zapłacisz: " + ticket.getPrice() + " zł.");
                basket.addToPrice(ticket.getPrice());
                basket.addToBasket(ticket);
            }
            if ("3".equals(secondResponse))
                System.out.println("Kupiłeś bilet na ładunek");

            System.out.println("Co chcesz dalej zrobić? <Nowy bilet> <Pokaż stan> <End>");
            scanner.nextLine();
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Nowy bilet")) repaetBuying = true;
            if (answer.equalsIgnoreCase("End")) repaetBuying = false;
            if (answer.equalsIgnoreCase("Pokaż stan")) System.out.println(basket.getBalance());
        }
    }

   /* private BigDecimal personPrice(int age) {
        if (age < 0) throw new IllegalArgumentException("Podałeś wiek poniżej zera.");
        if (age <= 3) return new PersonTicket(BigDecimal.valueOf(0)).getPrice();
        if (age > 3 && age < 18) return new PersonTicket(BigDecimal.valueOf(5)).getPrice();
        if (age > 18 && age < 70) return new PersonTicket(BigDecimal.valueOf(10)).getPrice();
        else return new PersonTicket(BigDecimal.valueOf(5)).getPrice();
    }*/


    private Ticket parseVehicle(String vehicle, String owner) {
        switch (vehicle) {
            case "CAR":
                return new CarTicket(owner);
            case "BIKE":
                return new BikeTicket(owner);
            case "BUS":
                System.out.println("Podaj długość autobusu: ");
                float length = scanner.nextFloat();
                System.out.println(length);
                return new BusTicket(length, owner);
            case "TRUCK":
                System.out.println("Podaj wagę ciężarówki");
                float weight = scanner.nextFloat();
                return new TruckTicket(weight, owner);
            default:
                System.out.println("Podałeś zły typ pojazdu");
                return null;
        }
    }

    public Ticket paxType(int age, String owner) {
        if (age < 0) throw new IllegalArgumentException("Podałeś wiek poniżej zera.");
        if (age > 0 && age <= 3) return new CarTicket(owner);
        if (age > 3 && age < 18) return new YoungTicket(owner);
        if (age > 18 && age < 70) return new AdultTicket(owner);
        if (age >= 70) return new SeniorTicket(owner);
        else return null;
    }
}