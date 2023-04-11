package tests;

import com.wfc.system.Customer;
import com.wfc.system.Lesson;
import com.wfc.system.Review;
import com.wfc.system.WeekendFitnessClub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeekendFitnessClubTest {

    @Test
    public void checkCustomerLength() {
        Customer c=new Customer("John");
        c.getBookings().add(new Lesson("Sunday", "ZUMBA"));
        c.getBookings().get(0).addReview("Nice Work" , 2);
        c.getBookings().add(new Lesson("Saturday", "SPIN"));
        c.getBookings().get(1).addReview("Good lesson" , 4);
        Assertions.assertEquals(2, c.getBookings().size());
    }

    @Test
    public void checkGetCustomerTest() {
        String customerName1= "John";
        Assertions.assertEquals(customerName1, WeekendFitnessClub.getCustomer(customerName1).getName() );
    }

    @Test
    public void getCustomerNullCheck(){
        String customerName2 = "Jully";
        Assertions.assertNull(WeekendFitnessClub.getCustomer(customerName2));
    }

    @Test
    public void checkCountCustomersBookedForLessonTest(){
        String day = "Saturday";
        String fitnesstype = "SPIN";
        Assertions.assertEquals(0 , WeekendFitnessClub.countCustomersBookedForLesson(day, fitnesstype) );
    }
}