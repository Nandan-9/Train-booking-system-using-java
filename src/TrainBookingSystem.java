interface Train {
    int NUM_COACHES = 10;
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
}

class TrainImpl implements Train {
    private String name;
    private int[][] seats; // 2D array to represent seats in different classes

    public TrainImpl(String name) {
        this.name = name;
        this.seats = new int[NUM_COACHES][SEATS_PER_COACH];
        initializeSeats();
    }

    private void initializeSeats() {
        for (int i = 0; i < NUM_COACHES; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                seats[i][j] = 0; // 0 means seat is available, 1 means seat is booked
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean bookSeats(int numTickets, int coachType) {
        int availableSeats = 0;
        int[][] classSeats;
        int numCoaches;

        switch (coachType) {
            case 1: // First Class
                classSeats = seats;
                numCoaches = FIRST_CLASS_COACHES;
                break;
            case 2: // Business Class
                classSeats = seats;
                numCoaches = BUSINESS_CLASS_COACHES;
                break;
            case 3: // Economy Class
                classSeats = seats;
                numCoaches = ECONOMY_CLASS_COACHES;
                break;
            default:
                return false;
        }

        // Check for availability of seats
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

    private boolean deleteSeats(int numTickets, int coachType) {
        int[][] classSeats;
        int numCoaches;

        switch (coachType) {
            case 1: // First Class
                classSeats = seats;
                numCoaches = FIRST_CLASS_COACHES;
                break;
            case 2: // Business Class
                classSeats = seats;
                numCoaches = BUSINESS_CLASS_COACHES;
                break;
            case 3: // Economy Class
                classSeats = seats;
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
        return true;
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

    private int getAvailableSeats(int coachType) {
        int availableSeats = 0;
        int[][] classSeats;
        int numCoaches;

        switch (coachType) {
            case 1: // First Class
                classSeats = seats;
                numCoaches = FIRST_CLASS_COACHES;
                break;
            case 2: // Business Class
                classSeats = seats;
                numCoaches = BUSINESS_CLASS_COACHES;
                break;
            case 3: // Economy Class
                classSeats = seats;
                numCoaches = ECONOMY_CLASS_COACHES;
                break;
            default:
                return 0;
        }
        // Check for availability of seats
        for (int i = 0; i < numCoaches; i++) {
            for (int j = 0; j < SEATS_PER_COACH; j++) {
                if (classSeats[i][j] == 0) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }
}
public class TrainBookingSystem {
    public static void main(String[] args) {
        Train[] trains = new Train[5];
        for (int i = 0; i < 5; i++) {
            trains[i] = new TrainImpl("Train " + (i + 1));
        }

        // Example usage:
        int numTickets = 3;
        Train selectedTrain = trains[0]; // Selecting the first train (Train 1)

        // Booking tickets
        if (selectedTrain.bookFirstClassTicket(numTickets)) {
            System.out.println(numTickets + " First Class ticket(s) booked successfully!");
        } else {
            System.out.println("Failed to book First Class tickets. Not enough seats available.");
        }

        // Deleting tickets
        if (selectedTrain.deleteFirstClassTicket(numTickets)) {
            System.out.println(numTickets + " First Class ticket(s) deleted successfully!");
        } else {
            System.out.println("Failed to delete First Class tickets. Tickets not found.");
        }

        // Getting available seats
        System.out.println("Available First Class seats: " + selectedTrain.getAvailableFirstClassSeats());
        System.out.println("Available Business Class seats: " + selectedTrain.getAvailableBusinessClassSeats());
        System.out.println("Available Economy Class seats: " + selectedTrain.getAvailableEconomyClassSeats());
    }
}
