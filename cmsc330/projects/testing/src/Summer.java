
public class Summer extends Season implements WeatherReport {

	public Summer(int temperature, String month) {
		super(temperature, month);
	}

	public int getTemperature() {
		return this.temperature;
	}

	public String getMonth() {
		return this.month;
	}

}
