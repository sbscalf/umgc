public class main {
	public static WeatherReport getReport(int temperature, String month) {
        switch(month) {
            case "March": case "April": case "May":
                return new Spring(temperature, month);
            case "June": case "July": case "August":
                return new Summer(temperature, month);
            default:
            	return null;
        }
    }

	public static void main(String[] args) {
		WeatherReport report = getReport(90, "September");
		System.out.println(report.getTemperature() + " " + report.getMonth());
	}

}
