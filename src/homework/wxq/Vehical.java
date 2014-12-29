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
	boolean flag;
	boolean isAtStation;
	boolean isStop;
	int loadPassengers;
	float mile = 0;
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
