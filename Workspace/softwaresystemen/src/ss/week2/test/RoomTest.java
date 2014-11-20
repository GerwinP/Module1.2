package ss.week2.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import ss.week2.hotel.Guest;
import ss.week2.hotel.Room;

/** 
 * Testprogram for Room en Guest
 * Software Systems
 * @author Arend Rensink
 * @version $Revision: 1.5 $
 */
public class RoomTest {
    /** <tt>Gast</tt>-testvariabele. */
    public Guest guest;
    /** <tt>Kamer</tt>-testvariabele. */
    public Room room;
    
    private static final String password = "correct";


    @Before
    public void setUp() {
        // initialisatie van gast-variabele
        guest = new Guest("Jip");
        // initialisatie van kamer-variabele
        room = new Room(101);
        // initialisatie van safe-variabele
    }

    /**
     * Test of the initial situation
     * Method call should be prefixed by setUp
     * <tt>{@link #setUp}</tt>.
     */
    @Test
    public void testInitial() {
        assertEquals("room.getNumber()", 101, room.getNumber());
    	assertEquals("room.safe.isActive()", false, room.safe.isActive());
    }
    
    /**
     * Test the safe in activating, opening and closing
     */
    private void testSafe(){
    	room.safe.activate(password);
    	assertEquals("room.safe.isActive()", true, room.safe.isActive());
    	room.safe.open(password);
    	assertEquals("room.safe.isOpen()", true, room.safe.isOpen());
    	room.safe.close();
    	assertEquals("room.safe.isOpen()", false, room.safe.isOpen());
    }

    /**
     * Test setting a guest
     * Method call should be prefixed by setUp
     * <tt>{@link #setUp}</tt>.
     */
    @Test
    public void testSetGuest() {
        room.setGuest(guest);
        assertEquals("room.setueast(gast); room.getGuest()", guest, room.getGuest());
    }
    
    public void test(){
    	setUp();
    	testInitial();
    	setUp();
    	testSetGuest();
    	setUp();
    	testSafe();
    }

}
