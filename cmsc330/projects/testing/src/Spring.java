public class Spring extends Season
implements WeatherReport {
	public Spring(int temperature, String month) {
		super(temperature, month);
	}
	
	public int getTemperature() {
		return this.temperature;
	}

	public String getMonth() {
		return this.month;
	}

}
