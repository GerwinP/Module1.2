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


    @Before
    public void setUp() {
        // initialisatie van gast-variabele
        guest = new Guest("Jip");
        // initialisatie van kamer-variabele
        room = new Room(101);
    }

    /**
     * Test of the initial situation
     * Method call should be prefixed by setUp
     * <tt>{@link #setUp}</tt>.
     */
    @Test
    public void testInitial() {
        assertEquals("room.getNumber()", 101, room.getNumber());
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

}
