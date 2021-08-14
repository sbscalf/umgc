
public class TrafficLightDemoController {
	
	static Thread[] lights = new Thread[3];
	
	static boolean stop = false;

	public static void main(String[] args) {
		for (int i = 0; i < lights.length; i++)
			lights[i] = new Thread(new TrafficLightSimulator());

	}
	
	

}
