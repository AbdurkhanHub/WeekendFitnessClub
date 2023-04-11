import java.util.*;


public class WeekendFitnessClub {

    private static final String[][] TIMETABLE = {
            {"Sunday", "SPIN", "BODYSCULPT"},
            {"Saturday", "BODYSCULPT", "YOGA"},
            {"Saturday", "YOGA", "ZUMBA"},
            {"Saturday", "YOGA", "SPIN"},
            {"Saturday", "BODYSCULPT", "SPIN"},
            {"Sunday", "ZUMBA", "YOGA"},
            {"Sunday", "BODYSCULPT", "ZUMBA"},
            {"Sunday", "YOGA", "ZUMBA"},
            {"Saturday", "YOGA", "ZUMBA"},
            {"Saturday", "BODYSCULPT", "SPIN"},
            {"Saturday", "ZUMBA", "SPIN"},
            {"Sunday", "SPIN", "BODYSCULPT"},
            {"Saturday", "SPIN", "YOGA"},
            {"Saturday", "YOGA", "ZUMBA"},
            {"Sunday", "BODYSCULPT", "ZUMBA"},
            {"Saturday", "YOGA", "ZUMBA"}
    };

    private static final Map<String, Double> LESSON_PRICES = new HashMap<String, Double>() {{
        put("SPIN", 20.0);
        put("YOGA", 15.0);
        put("BODYSCULPT", 25.0);
        put("ZUMBA", 18.0);
    }};

    private static final int MAX_CUSTOMERS_PER_LESSON = 5;

    private static final List<Customer> CUSTOMERS = new ArrayList<Customer>() {
        {
            add(new Customer("John"));
            add(new Customer("Peter"));
        }
    };

    private static Customer getCustomer(String customerName) {
        for (Customer customer : CUSTOMERS) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        return null;
    }
    public static void displayMenu(){
        System.out.println("\nWelcome to the Weekend Fitness Club booking system!");
        System.out.println("Please choose an option:");
        System.out.println("1. Check timetable by day");
        System.out.println("2. Check timetable by fitness type");
        System.out.println("3. Add a customer");
        System.out.println("4. Book a lesson");
        System.out.println("5. Change a booking");
        System.out.println("6. Cancel a booking");
        System.out.println("7. Provide a review and rating");
        System.out.println("8. Print reports");
        System.out.println("9. Exit");
        System.out.print("Please choose options: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    seeTimetableByDay();
                    break;
                case 2:
                    seeTimetableByFitnessType();
                    break;
                case 3:
                    addCustomer(scanner);
                    break;
                case 4:
                    bookLesson(scanner);
                    break;
                case 5:
                    changeBooking(scanner);
                    break;
                case 6:
                    cancelBooking(scanner);
                    break;
                case 7:
                    writeReviewAndRating(scanner);
                    break;
                case 8:
                    printReportsForLessons();
                    break;
                case 9:
                    System.out.println("Thank you for using the Weekend Fitness Club booking system.");
                    System.exit(0);
                default:
                    System.out.println(CUSTOMERS);
                    System.out.println("HEYY Invalid option. Please choose again.");
                    break;
            }
        }
    }

    private static void seeTimetableByDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the day (Saturday or Sunday) to view the timetable:");
        String day = scanner.nextLine();
        boolean hasLessons = false;
        int count = 0;
        System.out.println();
        for (String[] lesson : TIMETABLE) {
            if (lesson[0].equalsIgnoreCase(day)) {
                System.out.println("Weekend " + ++count);
                System.out.println(lesson[1] + " at " + lesson[0] + " at 9:00 AM");
                System.out.println(lesson[2] + " at " + lesson[0] + " at 10:00 AM");
                hasLessons = true;
            }
        }
        if (!hasLessons) {
            System.out.println("There is no lesson for " + day + ". Thanks");
        }
    }

      private static void seeTimetableByFitnessType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the fitness type (SPIN, YOGA, BODYSCULPT, or ZUMBA) to view the timetable:");
        String fitnessType = scanner.nextLine();
        boolean hasLessons = false;
        System.out.println();
        for (String[] lesson : TIMETABLE) {
            if (lesson[1].equalsIgnoreCase(fitnessType)) {
                System.out.println(lesson[1] + " at " + lesson[0] + " at 9:00");
                hasLessons = true;
            } else if (lesson[2].equalsIgnoreCase(fitnessType)) {
                System.out.println(lesson[2] + " at " + lesson[0] + " at 10:00");
                hasLessons = true;
            }
        }
        if (!hasLessons) {
            System.out.println("There are no lesson for " + fitnessType);
        }
    }

    private static int countCustomersBookedForLesson(String date, String lessonType) {
        int count = 0;
        for (Customer customer : CUSTOMERS) {
            List<Lesson> bookings = customer.getBookings();
            for (Lesson booking : bookings) {
                String day = booking.getDay();
                String type = booking.getFitnessType();
                if (day.equals(date) && type.equals(lessonType)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void bookLesson(Scanner scanner) {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        Customer customer = getCustomer(name);
        if (customer == null) {
            System.out.println("No customer found with that name. Please add the customer first.");
            return;
        }
        System.out.println("Please choose a lesson type:");
        for (String lessonType : LESSON_PRICES.keySet()) {
            System.out.println(lessonType);
        }
        String lessonType = scanner.nextLine().toUpperCase();
        if (!LESSON_PRICES.containsKey(lessonType)) {
            System.out.println("Invalid lesson type. Please choose again.");
            return;
        }
        System.out.println("Please choose a date (e.g. Saturday or Sunday):");
        String day = scanner.nextLine();
        boolean isLessonAvailable = false;
        for (String[] lesson : TIMETABLE) {
            if (lesson[0].equalsIgnoreCase(day) && (lesson[1].equals(lessonType) || lesson[2].equals(lessonType))) {
                int numCustomersBooked = countCustomersBookedForLesson(day, lessonType);
                if (numCustomersBooked < MAX_CUSTOMERS_PER_LESSON) {
                    isLessonAvailable = true;
                    Lesson ob = new Lesson(day, lessonType);
                    customer.getBookings().add(ob);
                    System.out.println("Lesson booked successfully for " + name + " on " + day + " for " + lessonType);
                    break;
                }
            }
        }
        if (!isLessonAvailable) {
            System.out.println("Sorry, there are no available lessons of that type on " + day + ". Please try again after some time.");
        }
    }

    private static void changeBooking(Scanner scanner) {
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();
        Customer customer = getCustomer(customerName);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Please choose a date (e.g. Saturday or Sunday) for change:");
        String day = scanner.nextLine();
        if (!day.equalsIgnoreCase("saturday") && !day.equalsIgnoreCase("sunday")) {
            System.out.println("Please enter correct day");
            return;
        }
        System.out.println("Please choose a lesson type for change:");
        for (String lessonType : LESSON_PRICES.keySet()) {
            System.out.println(lessonType);
        }
        String lessonType = scanner.nextLine().toUpperCase();
        if (!LESSON_PRICES.containsKey(lessonType)) {
            System.out.println("Invalid lesson type. Please choose again.");
            return;
        }

        boolean isLessonAvailable = false;


        for (Lesson l : customer.getBookings()) {
            if (l.getDay().equalsIgnoreCase(day) && l.getFitnessType().equalsIgnoreCase(lessonType)) {

                System.out.println("Please choose a date (e.g. Saturday or Sunday) for update:");
                String updatedDay = scanner.nextLine();
                if (!updatedDay.equalsIgnoreCase("saturday") && !updatedDay.equalsIgnoreCase("sunday")) {
                    System.out.println("Please enter correct day");
                    return;
                }
                System.out.println("Please choose a lesson type for update:");
                for (String l1 : LESSON_PRICES.keySet()) {
                    System.out.println(l1);
                }
                String updatedLessonType = scanner.nextLine().toUpperCase();
                if (!LESSON_PRICES.containsKey(updatedLessonType)) {
                    System.out.println("Invalid lesson type. Please choose again.");
                    return;
                }
                for (String[] lesson : TIMETABLE) {
                    if (lesson[0].equalsIgnoreCase(updatedDay) && (lesson[1].equals(updatedLessonType) || lesson[2].equals(updatedLessonType))) {
                        int numCustomersBooked = countCustomersBookedForLesson(updatedDay, updatedLessonType);
                        if (numCustomersBooked < MAX_CUSTOMERS_PER_LESSON) {
                            isLessonAvailable = true;
                            customer.getBookings().remove(l);
                            customer.getBookings().add(new Lesson(updatedDay, updatedLessonType));
                            System.out.println("Booking changed successfully.");
                            return;
                        }
                    }
                }
            }
        }

        if (!isLessonAvailable) {
            System.out.println("Sorry, there are no available lessons of that type on " + day + ". Please try again after some time.");
        } else {
            System.out.println("Somthing went wring. please enter correct data which is available.");
        }

    }


    private static void cancelBooking(Scanner scanner) {
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();
        Customer customer = getCustomer(customerName);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        List<Lesson> bookings = customer.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for customer " + customerName);
            return;
        }
        System.out.println("Enter the day (Saturday or Sunday) of the lesson to cancel:");
        String day = scanner.nextLine();
        if (!day.equalsIgnoreCase("saturday") && !day.equalsIgnoreCase("sunday")) {
            System.out.println("Please enter correct day");
            return;
        }
        System.out.println("Enter the fitness type of the lesson to cancel:");
        for (String lessonType : LESSON_PRICES.keySet()) {
            System.out.println(lessonType);
        }
        String lessonType = scanner.nextLine().toUpperCase();
        if (!LESSON_PRICES.containsKey(lessonType)) {
            System.out.println("Invalid lesson type. Please choose again.");
            return;
        }
        boolean isBooking = false;
        for (Lesson l : customer.getBookings()) {
            if (l.getDay().equalsIgnoreCase(day) && l.getFitnessType().equalsIgnoreCase(lessonType)) {
                isBooking = true;
                customer.getBookings().remove(l);
                System.out.println("Booking cancelled for " + customerName + " on " + day + " for " + lessonType);
                return;
            }
        }
        if (!isBooking) {
            System.out.println("No Booking available for " + customerName + " on " + day + " for " + lessonType);
        }
    }

    private static void addCustomer(Scanner scanner) {
        System.out.println("Enter the customer name:");
        String customerName = scanner.nextLine();
        Customer existingCustomer = getCustomer(customerName);
        if (existingCustomer != null) {
            System.out.println("Customer already exists.");
            return;
        }
        Customer newCustomer = new Customer(customerName);
        CUSTOMERS.add(newCustomer);
        System.out.println("Customer added successfully.");
    }

    private static void writeReviewAndRating(Scanner scanner) {
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();
        Customer customer = getCustomer(customerName);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Enter the day (Saturday or Sunday) of the lesson to cancel:");
        String day = scanner.nextLine();
        if (!day.equalsIgnoreCase("saturday") && !day.equalsIgnoreCase("sunday")) {
            System.out.println("Please enter correct day");
            return;
        }
        System.out.println("Enter the fitness type of the lesson:");
        for (String lessonType : LESSON_PRICES.keySet()) {
            System.out.println(lessonType);
        }
        String lessonType = scanner.nextLine().toUpperCase();
        if (!LESSON_PRICES.containsKey(lessonType)) {
            System.out.println("Invalid lesson type. Please choose again.");
            return;
        }
        boolean isBooking = false;
        for (Lesson l : customer.getBookings()) {
            if (l.getDay().equalsIgnoreCase(day) && l.getFitnessType().equalsIgnoreCase(lessonType)) {
                isBooking = true;
                System.out.println("Enter your review:");
                String review = scanner.nextLine();
                System.out.println("Enter your rating (1-5):");
                int rating = scanner.nextInt();
                l.addReview(review, rating);
                System.out.println("Thank you for your review and rating " + customerName + " !");
                return;

            }
        }
        if (!isBooking) {
            System.out.println("No Booking available for " + customerName + " on " + day + " for " + lessonType);
        }
    }

    private static void printReportsForLessons() {
        // Print total number of customers
        int totalCustomers = CUSTOMERS.size();
        System.out.println("\nTotal number of customers: " + totalCustomers);

        int totalSpin = 0, totalYoga = 0, totalBody = 0, totalZumba = 0;
        for (Customer cust : CUSTOMERS) {
            for (Lesson l : cust.getBookings()) {
                if (l.getFitnessType().equalsIgnoreCase("SPIN"))
                    totalSpin++;
                if (l.getFitnessType().equalsIgnoreCase("YOGA"))
                    totalYoga++;
                if (l.getFitnessType().equalsIgnoreCase("BODYSCULPT"))
                    totalBody++;
                if (l.getFitnessType().equalsIgnoreCase("ZUMBA"))
                    totalZumba++;
            }
        }
        List<LessonSort> lessons = new ArrayList<>();
        for(Map.Entry<String, Double> map: LESSON_PRICES.entrySet()){
            if(map.getKey().equalsIgnoreCase("SPIN"))
                lessons.add(new LessonSort(map.getKey(), totalSpin * map.getValue()));
            if(map.getKey().equalsIgnoreCase("YOGA"))
                lessons.add(new LessonSort(map.getKey(), totalYoga * map.getValue()));
            if(map.getKey().equalsIgnoreCase("BODYSCULPT"))
                lessons.add(new LessonSort(map.getKey(), totalBody * map.getValue()));
            if(map.getKey().equalsIgnoreCase("ZUMBA"))
                lessons.add(new LessonSort(map.getKey(), totalZumba * map.getValue()));
        }

        System.out.println("\nTop Highest Income lessons:");
        Collections.sort(lessons, new Comparator<LessonSort>() {
            @Override
            public int compare(LessonSort o1, LessonSort o2) {
                double amount1 = o1.getTotalLessonAmount();
                double amount2 = o2.getTotalLessonAmount();
                if (amount1 < amount2) {
                    return 1;
                } else if (amount1 > amount2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (int i = 0; i < lessons.size(); i++) {
            System.out.println(lessons.get(i));
        }
    }


}



