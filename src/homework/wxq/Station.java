package homework.wxq;

/**
 * 
 * @author WXQ
 *
 */
class Station {
	float awayFromVolvo0;
	float awayFromVolvo1;
	float awayFromEvco0;
	float awayFromEvco1;
	int getOffPassengers;
	int getOnPassengers;
	int parkingTime;
	String name;

	public Station(float awayFromVolvo0, float awayFromVolvo1,
			float awayFromEvco0, float awayFromEvco1, int getOffPassengers,
			int getOnPassengers, int parkingTime, String name) {
		super();
		this.awayFromVolvo0 = awayFromVolvo0;
		this.awayFromVolvo1 = awayFromVolvo1;
		this.awayFromEvco0 = awayFromEvco0;
		this.awayFromEvco1 = awayFromEvco1;
		this.getOffPassengers = getOffPassengers;
		this.getOnPassengers = getOnPassengers;
		this.parkingTime = parkingTime;
		this.name = name;
	}

}
