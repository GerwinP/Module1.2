package ss.week1.ThreeWayLamp;

import ss.week1.ThreeWayLamp.ThreeWayLamp.Setting;

public class TestTWL {
	
	private ThreeWayLamp TWL;
	
	public TestTWL(){
		setUp();
		System.out.println("TestInitialSetting");
		testInitialSetting();
		System.out.println("TestNextSetting");
		testNextSetting();
	}
	
	private void setUp(){
		TWL = new ThreeWayLamp();
	}
	
	private void testInitialSetting(){
		System.out.println("The initialSetting is: " + TWL.toString(TWL.getSetting()));
	}
	
	private void testNextSetting(){
		System.out.println("The Setting is: " + TWL.toString(Setting.OFF));
		TWL.nextSetting(Setting.OFF);
		System.out.println("The Setting is: " + TWL.toString(TWL.getSetting()));
		TWL.nextSetting(TWL.getSetting());
		System.out.println("The Setting is: " + TWL.toString(TWL.getSetting()));
		TWL.nextSetting(TWL.getSetting());
		System.out.println("The Setting is: " + TWL.toString(TWL.getSetting()));
		TWL.nextSetting(TWL.getSetting());
		System.out.println("The Setting is: " + TWL.toString(TWL.getSetting()));
		TWL.nextSetting(TWL.getSetting());
		System.out.println("The Setting is: " + TWL.toString(TWL.getSetting()));
	}
	
	public static void main(String[] args){
		new TestTWL();
	}
}
