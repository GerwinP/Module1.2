package ss.week1.ThreeWayLamp;

public class ThreeWayLamp {

	public Setting currentSetting;
	public String settingString;
	
	public enum Setting{
		OFF, LOW, MEDIUM, HIGH;
	}
	
	public ThreeWayLamp(){
		currentSetting = Setting.OFF;
	}
	
	public Setting getSetting(){
		return currentSetting;
	}
	
	public void nextSetting(Setting oldSetting){
		if(currentSetting == Setting.OFF){
			currentSetting = Setting.LOW;
		}else if(currentSetting == Setting.LOW){
			currentSetting = Setting.MEDIUM;			
		}else if(currentSetting == Setting.MEDIUM){
			currentSetting = Setting.HIGH;
		}else if(currentSetting == Setting.HIGH){
			currentSetting = Setting.OFF;
		}
	}
	
	public String toString(Setting currentSetting){
		if(currentSetting == Setting.OFF){
			settingString = "OFF";
		}else if(currentSetting == Setting.LOW){
			settingString = "LOW";
		}else if(currentSetting == Setting.MEDIUM){
			settingString = "MEDIUM";
		}else if(currentSetting == Setting.HIGH){
			settingString = "HIGH";
		}
		return settingString;
	}
	
}
