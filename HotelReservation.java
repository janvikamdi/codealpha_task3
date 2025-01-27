import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class HotelReservation{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Room> rooms = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        // Initialize sample rooms (replace with actual data)
        rooms.add(new Room("Single", 50.00, true));
        rooms.add(new Room("Double", 80.00, true));
        rooms.add(new Room("Suite", 150.00, true));

        while (true) {
            System.out.println("\n1. Search for Rooms\n2. Make Reservation\n3. View Reservations\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchRooms(rooms, scanner);
                    break;
                case 2:
                    makeReservation(rooms, reservations, scanner);
                    break;
                case 3:
                    viewReservations(reservations);
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void searchRooms(List<Room> rooms, Scanner scanner) {
        System.out.print("Enter check-in date (YYYY-MM-dd): ");
        String checkInStr = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-dd): ");
        String checkOutStr = scanner.nextLine();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkIn = dateFormat.parse(checkInStr);
            Date checkOut = dateFormat.parse(checkOutStr);

            System.out.println("\nAvailable Rooms:");
            for (Room room : rooms) {
                if (room.isAvailable(checkIn, checkOut)) {
                    System.out.println(room);
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-dd.");
        }
    }

    private static void makeReservation(List<Room> rooms, List<Reservation> reservations, Scanner scanner) {
        System.out.print("Enter room type: ");
        String roomType = scanner.nextLine();

        System.out.print("Enter check-in date (YYYY-MM-dd): ");
        String checkInStr = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-dd): ");
        String checkOutStr = scanner.nextLine();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkIn = dateFormat.parse(checkInStr);
            Date checkOut = dateFormat.parse(checkOutStr);

            Room selectedRoom = null;
            for (Room room : rooms) {
                if (room.getType().equalsIgnoreCase(roomType) && room.isAvailable(checkIn, checkOut)) {
                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom != null) {
                System.out.print("Enter customer name: ");
                String customerName = scanner.nextLine();

                // Simulate payment processing (replace with actual integration)
                System.out.println("Processing payment...");
                // ... (payment processing logic)

                Reservation reservation = new Reservation(customerName, selectedRoom, checkIn, checkOut);
                reservations.add(reservation);
                System.out.println("Reservation confirmed!");
            } else {
                System.out.println("No available rooms of that type for the specified dates.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-dd.");
        }
    }

    private static void viewReservations(List<Reservation> reservations) {
        System.out.println("\nReservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    static class Room {
        private String type;
        private double pricePerNight;
        private boolean isAvailable;

        public Room(String type, double pricePerNight, boolean isAvailable) {
            this.type = type;
            this.pricePerNight = pricePerNight;
            this.isAvailable = isAvailable;
        }

        public String getType() {
            return type;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        public boolean isAvailable(Date checkIn, Date checkOut) {
            // Simplified availability check (replace with actual logic)
            return isAvailable;
        }

        @Override
        public String toString() {
            return "Room Type: " + type + ", Price: $" + pricePerNight + " per night";
        }
    }

    static class Reservation {
        private String customerName;
        private Room room;
        private Date checkIn;
        private Date checkOut;

        public Reservation(String customerName, Room room, Date checkIn, Date checkOut) {
            this.customerName = customerName;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return "Customer: " + customerName + ", Room: " + room.getType() +
                    ", Check-in: " + dateFormat.format(checkIn) +
                    ", Check-out: " + dateFormat.format(checkOut);
        }
    }
}