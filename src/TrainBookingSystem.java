import java.util.*;

interface Train {
    int SEATS_PER_COACH = 50;
    int FIRST_CLASS_COACHES = 2;
    int BUSINESS_CLASS_COACHES = 3;
    int ECONOMY_CLASS_COACHES = 5;
    String getName();
    boolean bookFirstClassTicket(int numTickets);
    boolean bookBusinessClassTicket(int numTickets);
    boolean bookEconomyClassTicket(int numTickets);
    boolean deleteFirstClassTicket(int numTickets);
    boolean deleteBusinessClassTicket(int numTickets);
    boolean deleteEconomyClassTicket(int numTickets);
    int getAvailableFirstClassSeats();
    int getAvailableBusinessClassSeats();
    int getAvailableEconomyClassSeats();
    List<String> getBookedTickets();
    String getStartStation();
    String getEndStation();
    int getNumFirstClassCoaches();
    int getNumBusinessClassCoaches();
    int getNumEconomyClassCoaches();
}
class TrainImpl implements Train {
    private final String name;
    private final String startStation;
    private final String endStation;
    private final int[][] firstClassSeats;
    private final int[][] businessClassSeats;
    private final int[][] economyClassSeats;
    public TrainImpl(String name, String startStation, String endStation) {
        this.name = name;
        this.startStation = startStation;
        this.endStation = endStation;
        this.firstClassSeats = new int[FIRST_CLASS_COACHES][SEATS_PER_COACH];
        this.businessClassSeats = new int[BUSINESS_CLASS_COACHES][SEATS_PER_COACH];
        this.economyClassSeats = new int[ECONOMY_CLASS_COACHES][SEATS_PER_COACH];
        initializeSeats();
    }
    private void initializeSeats() {
        for (int i = 0; i < FIRST_CLASS_COACHES; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                firstClassSeats[i][j] = 0;
            }
        }
        for (int i = 0; i < BUSINESS_CLASS_COACHES; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                businessClassSeats[i][j] = 0;
            }
        }
        for (int i = 0; i < ECONOMY_CLASS_COACHES; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                economyClassSeats[i][j] = 0;
            }
        }
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getStartStation() {
        return startStation;
    }
    @Override
    public String getEndStation() {
        return endStation;
    }
    @Override
    public int getNumFirstClassCoaches() {
        return FIRST_CLASS_COACHES;
    }
    @Override
    public int getNumBusinessClassCoaches() {
        return BUSINESS_CLASS_COACHES;
    }
    @Override
    public int getNumEconomyClassCoaches() {
        return ECONOMY_CLASS_COACHES;
    }
    boolean bookSeats(int numTickets, int coachType) {
        int availableSeats = 0;
        int[][] classSeats;
        int numCoaches;
        switch (coachType) {
            case 1:
                classSeats = firstClassSeats;
                numCoaches = FIRST_CLASS_COACHES;
                break;
            case 2:
                classSeats = businessClassSeats;
                numCoaches = BUSINESS_CLASS_COACHES;
                break;
            case 3:
                classSeats = economyClassSeats;
                numCoaches = ECONOMY_CLASS_COACHES;
                break;
            default:
                return false;
        }
        for (int i = 0; i < numCoaches; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                if (classSeats[i][j] == 0) {
                    availableSeats++;
                }
            }
        }
        if (numTickets <= availableSeats) {
            int ticketsBooked = 0;
            for (int i = 0; i < numCoaches; i++) {
                for (int j = 0; j < SEATS_PER_COACH && ticketsBooked < numTickets; j++) {
                    if (classSeats[i][j] == 0) {
                        classSeats[i][j] = 1;
                        ticketsBooked++;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    boolean deleteSeats(int numTickets, int coachType) {
        int[][] classSeats;
        int numCoaches;
        switch (coachType) {
            case 1:
                classSeats = firstClassSeats;
                numCoaches = FIRST_CLASS_COACHES;
                break;
            case 2:
                classSeats = businessClassSeats;
                numCoaches = BUSINESS_CLASS_COACHES;
                break;
            case 3:
                classSeats = economyClassSeats;
                numCoaches = ECONOMY_CLASS_COACHES;
                break;
            default:
                return false;
        }
        int ticketsDeleted = 0;
        for (int i = 0; i < numCoaches; i++) {
            for (int j = 0; j < SEATS_PER_COACH && ticketsDeleted < numTickets; j++) {
                if (classSeats[i][j] == 1) {
                    classSeats[i][j] = 0;
                    ticketsDeleted++;
                }
            }
        }
        return ticketsDeleted == numTickets;
    }
    @Override
    public boolean bookFirstClassTicket(int numTickets) {
        return bookSeats(numTickets, 1);
    }
    @Override
    public boolean bookBusinessClassTicket(int numTickets) {
        return bookSeats(numTickets, 2);
    }
    @Override
    public boolean bookEconomyClassTicket(int numTickets) {
        return bookSeats(numTickets, 3);
    }
    @Override
    public boolean deleteFirstClassTicket(int numTickets) {
        return deleteSeats(numTickets, 1);
    }
    @Override
    public boolean deleteBusinessClassTicket(int numTickets) {
        return deleteSeats(numTickets, 2);
    }
    @Override
    public boolean deleteEconomyClassTicket(int numTickets) {
        return deleteSeats(numTickets, 3);
    }
    @Override
    public int getAvailableFirstClassSeats() {
        return getAvailableSeats(1);
    }
    @Override
    public int getAvailableBusinessClassSeats() {
        return getAvailableSeats(2);
    }
    @Override
    public int getAvailableEconomyClassSeats() {
        return getAvailableSeats(3);
    }
    int getAvailableSeats(int coachType) {
        int[][] classSeats;
        int numCoaches;
        switch (coachType) {
            case 1:
                classSeats = firstClassSeats;
                numCoaches = FIRST_CLASS_COACHES;
                break;
            case 2:
                classSeats = businessClassSeats;
                numCoaches = BUSINESS_CLASS_COACHES;
                break;
            case 3:
                classSeats = economyClassSeats;
                numCoaches = ECONOMY_CLASS_COACHES;
                break;
            default:
                return 0;
        }
        int availableSeats = 0;
        for (int i = 0; i < numCoaches; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                if (classSeats[i][j] == 0) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }
    @Override
    public List<String> getBookedTickets() {
        List<String> bookedTickets = new ArrayList<>();
        String[] classTypes = {"First Class", "Business Class", "Economy Class"};
        for (int coachType = 1; coachType <= ECONOMY_CLASS_COACHES; coachType++) {
            int[][] classSeats;
            int numCoaches;
            switch (coachType) {
                case 1:
                    classSeats = firstClassSeats;
                    numCoaches = FIRST_CLASS_COACHES;
                    break;
                case 2:
                    classSeats = businessClassSeats;
                    numCoaches = BUSINESS_CLASS_COACHES;
                    break;
                case 3:
                    classSeats = economyClassSeats;
                    numCoaches = ECONOMY_CLASS_COACHES;
                    break;
                default:
                    continue;
            }
            for (int i = 0; i < numCoaches; i++) {
                for (int j = 0; j < SEATS_PER_COACH; j++) {
                    if (classSeats[i][j] == 1) {
                        bookedTickets.add(classTypes[coachType - 1] + " - Coach " + (i + 1) + ", Seat " + (j + 1));
                    }
                }
            }
        }
        return bookedTickets;
    }
}
class HighSpeedTrain extends TrainImpl {
    private static final int HIGH_SPEED_COACHES = 10;

    public HighSpeedTrain(String name, String startStation, String endStation) {
        super(name, startStation, endStation);
        initializeHighSpeedSeats();
    }

    private void initializeHighSpeedSeats() {
        for (int i = 0; i < HIGH_SPEED_COACHES; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                highSpeedSeats[i][j] = 0;
            }
        }
    }

    private final int[][] highSpeedSeats = new int[HIGH_SPEED_COACHES][SEATS_PER_COACH];

    @Override
    public int getAvailableFirstClassSeats() {
        return getAvailableSeats(1) + getAvailableHighSpeedSeats(1);
    }

    @Override
    public int getAvailableBusinessClassSeats() {
        return getAvailableSeats(2) + getAvailableHighSpeedSeats(2);
    }

    @Override
    public int getAvailableEconomyClassSeats() {
        return getAvailableSeats(3) + getAvailableHighSpeedSeats(3);
    }

    private int getAvailableHighSpeedSeats(int coachType) {
        int[][] classSeats;
        int numCoaches;
        switch (coachType) {
            case 1:
                classSeats = highSpeedSeats;
                numCoaches = HIGH_SPEED_COACHES;
                break;
            default:
                return 0;
        }
        int availableSeats = 0;
        for (int i = 0; i < numCoaches; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                if (classSeats[i][j] == 0) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }


    @Override
    public List<String> getBookedTickets() {
        List<String> bookedTickets = super.getBookedTickets();
        for (int i = 0; i < HIGH_SPEED_COACHES; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                if (highSpeedSeats[i][j] == 1) {
                    bookedTickets.add("High-Speed Class - Coach " + (i + 1) + ", Seat " + (j + 1));
                }
            }
        }
        return bookedTickets;
    }
}
public class TrainBookingSystem {
    static Train[] createTrains() {
        Train[] trains = new Train[6]; // Adding one more train (High-Speed Train)
        trains[0] = new TrainImpl("Rajdhani Express (Train No. 12951)", "NDLS", "BCT");
        trains[1] = new TrainImpl("Shatabdi Express (Train No. 12002)", "BPL", "NDLS");
        trains[2] = new TrainImpl("Duronto Express (Train No. 12213)", "JP", "NDLS");
        trains[3] = new TrainImpl("Gatimaan Express (Train No. 12049)", "NDLS", "AGC");
        trains[4] = new TrainImpl("Kaveri Express (Train No. 16021)", "SBC", "MAS");
        trains[5] = new HighSpeedTrain("Bullet Express (Train No. 8001)", "CDG", "BLR");
        return trains;
    }
    private static void printBookedTickets(Train[] trains) {
        boolean hasBookedTickets = false;
        for (Train train : trains) {
            List<String> bookedTickets = train.getBookedTickets();
            if (!bookedTickets.isEmpty()) {
                hasBookedTickets = true;
                System.out.println("\nBooked Tickets for " + train.getName() + " (From " + train.getStartStation() +
                        " to " + train.getEndStation() + "):");
                for (String ticket : bookedTickets) {
                    System.out.println(ticket);
                }
                System.out.println();
            }
        }
        if (!hasBookedTickets) {
            System.out.println("\nNo tickets have been booked yet.");
        }
    }
    private static Train changeTrain(Train[] trains, Scanner scanner) {
        Train selectedTrain = null;
        while (selectedTrain == null) {
            System.out.println("\nAvailable Trains:");
            for (int i = 0; i < trains.length; i++) {
                System.out.println((i + 1) + ". " + trains[i].getName());
            }
            System.out.print("Enter the train number you want to book (0 to exit): ");
            int selectedTrainNumber = scanner.nextInt();
            if (selectedTrainNumber == 0) {
                System.out.println("\nSorry to see you go, Hoping to meet you again");
                drawEndBox();
                System.exit(0);
            }
            if (selectedTrainNumber < 1 || selectedTrainNumber > trains.length) {
                System.out.println("\nInvalid train number. Please try again.");
            } else {
                selectedTrain = trains[selectedTrainNumber - 1];
                System.out.println("\nYou have selected " + selectedTrain.getName());
            }
        }
        return selectedTrain;
    }
    private static void printDeletionStatus(String className, int numTickets, boolean success) {
        if (success) {
            System.out.println("\n" + numTickets + " " + className + " ticket(s) deleted successfully!");
        } else {
            System.out.println("\nFailed to delete " + className + " tickets. Tickets not found.");
        }
    }
    private static void printBookingStatus(String className, int numTickets, boolean success) {
        if (success) {
            System.out.println("\n" + numTickets + " " + className + " ticket(s) booked successfully!");
        } else {
            System.out.println("\nFailed to book " + className + " tickets. Not enough seats available.");
        }
    }
    static void runTrainBookingSystem(Train[] trains, Scanner scanner) {
        Train selectedTrain = null;
        while (selectedTrain == null) {
            System.out.println("\nAvailable Trains:");
            for (int i = 0; i < trains.length; i++) {
                System.out.println((i + 1) + ". " + trains[i].getName());
            }
            System.out.print("Enter the train number you want to book (0 to exit): ");
            int selectedTrainNumber = scanner.nextInt();
            if (selectedTrainNumber == 0) {
                System.out.println("\nSorry to see you go, Hoping to meet you again");
                drawEndBox();
                return;
            }
            if (selectedTrainNumber < 1 || selectedTrainNumber > trains.length) {
                System.out.println("\nInvalid train number. Please try again.");
            } else {
                selectedTrain = trains[selectedTrainNumber - 1];
                System.out.println("\nYou have selected " + selectedTrain.getName());
                System.out.println("\nNumber of First Class Coaches: " + selectedTrain.getNumFirstClassCoaches());
                System.out.println("Number of Business Class Coaches: " + selectedTrain.getNumBusinessClassCoaches());
                System.out.println("Number of Economy Class Coaches: " + selectedTrain.getNumEconomyClassCoaches());
            }
        }
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book First Class Ticket");
            System.out.println("2. Book Business Class Ticket");
            System.out.println("3. Book Economy Class Ticket");
            System.out.println("4. Delete First Class Ticket");
            System.out.println("5. Delete Business Class Ticket");
            System.out.println("6. Delete Economy Class Ticket");
            System.out.println("7. Get Available First Class Seats");
            System.out.println("8. Get Available Business Class Seats");
            System.out.println("9. Get Available Economy Class Seats");
            System.out.println("10. View Booked Tickets");
            System.out.println("11. Change Train");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            int numTickets;
            boolean success;
            switch (choice) {
                case 1:
                    System.out.print("\nEnter the number of First Class tickets to book: ");
                    numTickets = scanner.nextInt();
                    success = selectedTrain.bookFirstClassTicket(numTickets);
                    printBookingStatus("First Class", numTickets, success);
                    break;
                case 2:
                    System.out.print("\nEnter the number of Business Class tickets to book: ");
                    numTickets = scanner.nextInt();
                    success = selectedTrain.bookBusinessClassTicket(numTickets);
                    printBookingStatus("Business Class", numTickets, success);
                    break;
                case 3:
                    System.out.print("\nEnter the number of Economy Class tickets to book: ");
                    numTickets = scanner.nextInt();
                    success = selectedTrain.bookEconomyClassTicket(numTickets);
                    printBookingStatus("Economy Class", numTickets, success);
                    break;
                case 4:
                    System.out.print("\nEnter the number of First Class tickets to delete: ");
                    numTickets = scanner.nextInt();
                    success = selectedTrain.deleteFirstClassTicket(numTickets);
                    printDeletionStatus("First Class", numTickets, success);
                    break;
                case 5:
                    System.out.print("\nEnter the number of Business Class tickets to delete: ");
                    numTickets = scanner.nextInt();
                    success = selectedTrain.deleteBusinessClassTicket(numTickets);
                    printDeletionStatus("Business Class", numTickets, success);
                    break;
                case 6:
                    System.out.print("\nEnter the number of Economy Class tickets to delete: ");
                    numTickets = scanner.nextInt();
                    success = selectedTrain.deleteEconomyClassTicket(numTickets);
                    printDeletionStatus("Economy Class", numTickets, success);
                    break;
                case 7:
                    System.out.println("\nAvailable First Class seats: " + selectedTrain.getAvailableFirstClassSeats());
                    break;
                case 8:
                    System.out.println("\nAvailable Business Class seats: " + selectedTrain.getAvailableBusinessClassSeats());
                    break;
                case 9:
                    System.out.println("\nAvailable Economy Class seats: " + selectedTrain.getAvailableEconomyClassSeats());
                    break;
                case 10:
                    printBookedTickets(trains);
                    break;
                case 11:
                    System.out.println("\nChanging Train...");
                    selectedTrain = changeTrain(trains, scanner);
                    break;
                case 0:
                    System.out.println("\nThank You, Safe Travels");
                    drawEndBox();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
    static void validateEmail(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
    static void drawBox(String message) {
        int messageLength = message.length();
        int boxWidth = messageLength + 4;
        System.out.println("+" + "-".repeat(boxWidth) + "+");
        System.out.println("|  " + message + "  |");
        System.out.println("+" + "-".repeat(boxWidth) + "+");
    }
    static void drawEndBox() {
        System.out.println("\n" + "+---------------------------+");
        System.out.println("|        End of Program     |");
        System.out.println("+---------------------------+");
    }


}