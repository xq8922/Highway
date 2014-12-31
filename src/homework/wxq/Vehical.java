package homework.wxq;

/**
 * @flag 是否已上路 
 * @
 * @author WXQ
 * 
 */
public class Vehical {
	String name;
	int interval;
	float v;
	int passenger;
	boolean flag = false;
	boolean isAtStation = false;
	boolean isStop = false;
	int loadPassengers;
	float mile = 0;
	int stop_minite;
	String type;

	public Vehical(String string, int interval, float v, int passenger,
			String type) {
		super();
		this.name = string;
		this.interval = interval;
		this.v = v;
		this.passenger = passenger;
		this.type = type;
	}

}
