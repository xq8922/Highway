package homework.wxq;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class StationTest {
	private static final float[] P = { (float) 0.05, (float) 0.1, (float) 0.15,
			(float) 0.2, (float) 0.22 };

	// public static final int
	public static class P0 {
		private static final int XBxy = 0;
		private static final int XBxp = 1;
		private static final int XBwg = 2;
		private static final int XBcp = 3;
		private static final int XBgz = 4;
		private static final int XBbj = 5;
	}

	public static class P1 {
		private static final int BXgz = 0;
		private static final int BXcp = 1;
		private static final int BXwg = 2;
		private static final int BXxp = 3;
		private static final int BXxy = 4;
		private static final int BXxn = 5;
	}

	/**
	 * 测试用例中变量中0标志西安至宝鸡，1则反之。
	 * 
	 */
	public static void main(String args[]) {
		int hour = 7;
		int minite = 59;
		int count = minite;
		Random r = new Random();
		int volvoPassengerXB;
		int volvoPassengerBX;
		int evcoPassengerXB;
		int evcoPassengerBX;
		int sumPassengerXB = 0;
		int sumPassengerBX = 0;
		int tmp = 0;// 指示是否到站的中间变量
		int tmp_count = 0;// 控制谁在发车
		ListIterator lit;
		// public enum P0 {
		// XBxy,XBxp,XBwg,XBcp,XBgz,XBbj;
		// }
		P0 p0 = new P0();
		P1 p1 = new P1();
		float[] p_xb = new float[6];
		float[] p_bx = new float[6];
		int[] interval_volvo_xb = { 12, 13, 11, 33, 11, 13 };
		int[] interval_evco_xb = { 16, 17, 15, 44, 15, 17 };
		int[] interval_volvo_bx = { 13, 11, 33, 11, 13, 12 };
		int[] interval_evco_bx = { 17, 15, 44, 15, 17, 16 };
		// 5,12,4,15
		Vehical[] volvo_xb = new Volvo[5];
		Vehical[] volvo_bx = new Volvo[4];
		Vehical[] evco_xb = new Evco[12];
		Vehical[] evco_bx = new Evco[15];
//		LinkedQueue test = new LinkedQueue();
		LinkedList llq_volvo_xb = new LinkedList();
		LinkedList llq_evco_xb = new LinkedList();
		LinkedList llq_volvo_bx = new LinkedList();
		LinkedList llq_evco_bx = new LinkedList();
		LinkedList llq_xb = new LinkedList();
		
		String[] station_xb = { "西安", "咸阳", "兴平", "武功", "蔡家坡", "虎镇", "宝鸡" };
		String[] station_bx = { "宝鸡", "虎镇", "蔡家坡", "武功", "兴平", "咸阳", "西安" };

		for (int i = 0; i < 5; i++) {
			volvo_xb[i] = new Volvo("volvo0" + i, 60, (float) 1.9, 48, "volvo");
			llq_volvo_xb.addLast(volvo_xb[i]);
			llq_xb.addLast(volvo_xb[i]);
		}
		for (int i = 0; i < 4; i++) {
			volvo_bx[i] = new Volvo("volvo1" + i, 60, (float) 1.9, 48, "volvo");
			llq_volvo_bx.addLast(volvo_bx[i]);
			llq_xb.addLast(volvo_bx[i]);
		}
		for (int i = 0; i < 12; i++) {
			evco_xb[i] = new Evco("evco0" + i, 20, (float) 1.4, 21, "evco");
			llq_evco_xb.addLast(evco_xb[i]);
			llq_xb.addLast(evco_xb[i]);
		}
		for (int i = 0; i < 15; i++) {
			evco_bx[i] = new Evco("evco1" + i, 20, (float) 1.4, 21, "evco");
			llq_evco_bx.addLast(evco_bx[i]);
			llq_xb.addLast(evco_bx[i]);
		}

		while (hour < 18) {
			Vehical vehical;
			count++;
			if (count >= 60) {
				hour++;
				minite = 0;
				count = minite;
			}
			minite = count;
			System.out.println("" + hour + ":" + minite);
			{
				// 下述两行描述西安至宝鸡以及宝鸡至西安的乘客产生数
				// genPassenger0 = r.nextInt(2);
				// genPassenger1 = r.nextInt(2);
				// 下述6行描述从西安到宝鸡每个车站下车的概率
				p_xb[p0.XBxy] = r.nextFloat() / 20;
				p_xb[p0.XBxp] = r.nextFloat() / 10;
				p_xb[p0.XBwg] = r.nextFloat() / 20 * 3;
				p_xb[p0.XBcp] = r.nextFloat() / 5;
				p_xb[p0.XBgz] = r.nextFloat() / 11 * 50;
				p_xb[p0.XBbj] = 1 - p_xb[p0.XBxy] - p_xb[p0.XBxp]
						- p_xb[p0.XBwg] - p_xb[p0.XBcp] - p_xb[p0.XBgz];
				// 下述6行描述从宝鸡到西安每个车站下车的概率
				p_bx[p1.BXgz] = r.nextFloat() / 20;
				p_bx[p1.BXcp] = r.nextFloat() / 10;
				p_bx[p1.BXwg] = r.nextFloat() / 20 * 3;
				p_bx[p1.BXxp] = r.nextFloat() / 5;
				p_bx[p1.BXxy] = r.nextFloat() / 11 * 50;
				p_bx[p1.BXxn] = 1 - p_xb[p1.BXxy] - p_xb[p1.BXxp]
						- p_xb[p1.BXwg] - p_xb[p1.BXcp] - p_xb[p1.BXgz];
				// 乘客数
				for (int i = 0; i < 30; i++) {
					sumPassengerBX += r.nextInt(2);
					sumPassengerXB += r.nextInt(2);
				}
			}
			if (tmp_count % 60 == 0) {
				if (!llq_volvo_xb.isEmpty()) {
					vehical = (Volvo) llq_volvo_xb.removeFirst();
					if (sumPassengerXB <= 48) {
						vehical.loadPassengers = sumPassengerXB;
						sumPassengerXB = 0;
					} else {
						vehical.loadPassengers = vehical.passenger;
						sumPassengerXB -= vehical.passenger;
					}
					vehical.flag = true;
				}
				if (!llq_volvo_bx.isEmpty()) {
					vehical = (Volvo) llq_volvo_bx.removeFirst();
					if (sumPassengerBX <= 48) {
						vehical.loadPassengers = sumPassengerBX;
						sumPassengerBX = 0;
					} else {
						vehical.loadPassengers = vehical.passenger;
						sumPassengerBX -= vehical.passenger;
					}
					vehical.flag = true;
				}
			} else if (tmp_count % 20 == 0 && tmp_count >= 30) {
				if (!llq_evco_xb.isEmpty()) {
					vehical = (Evco) llq_evco_xb.removeFirst();
					if (sumPassengerXB <= 21) {
						vehical.loadPassengers = sumPassengerXB;
						sumPassengerXB = 0;
					} else {
						vehical.loadPassengers = vehical.passenger;
						sumPassengerXB -= vehical.passenger;
					}
					vehical.flag = true;
				}
				if (!llq_evco_bx.isEmpty()) {
					vehical = (Evco) llq_evco_bx.removeFirst();
					if (sumPassengerBX <= 21) {
						vehical.loadPassengers = sumPassengerBX;
						sumPassengerBX = 0;
					} else {
						vehical.loadPassengers = vehical.passenger;
						sumPassengerBX -= vehical.passenger;
					}
					vehical.flag = true;
				}
			}

			lit = llq_xb.listIterator();
			while (lit.hasNext()) {
				Vehical v = (Vehical) lit.next();
				int flag = -1;
				if (v.flag) {
					if (v.type == "volvo") {
						for (int i = 0; i < 6; i++) {
							if (tmp == interval_volvo_xb[i]) {// 如果tmp == 到站的值
								v.isAtStation = true;
								tmp = 0;
								flag = i;
							}
						}
					}
					if (v.isAtStation == true) {
						if (v.loadPassengers * p_xb[flag] > 0) {
							v.loadPassengers -= v.loadPassengers * p_xb[flag];
							v.isStop = true;
						}
						if (flag == 5) {
							v.flag = false;
							if (v.type == "volvo") {
								llq_volvo_bx.addLast(v);
								llq_volvo_xb.removeFirst();
							} else if (v.type == "evco") {
								llq_evco_bx.addLast(v);
								llq_evco_xb.removeFirst();
							}
						}
					}
					int flag_stop = 0;
					if (v.isStop == true) {
						flag_stop++;
						if (flag_stop == 2)
							v.isStop = false;
						System.out.println(v.name + "is stopped at"
								+ station_xb[flag]);
					} else {
						v.mile += 1.9;
						if (0 < v.mile && v.mile < 22 / 2) {
							System.out.println(v.name + " is " + v.mile
									+ " away from " + station_xb[0]);
						} else if (v.mile >= 22 / 2 && v.mile < (22 + 24 / 2)) {
							System.out.println(v.name + " is "
									+ Math.abs(v.mile - 22) + " away from "
									+ station_xb[1]);
						} else if (v.mile >= (22 + 24 / 2)
								&& v.mile < (22 + 24 + 21 / 2)) {
							System.out.println(v.name + " is "
									+ Math.abs(v.mile - 22 - 24)
									+ " away from " + station_xb[2]);
						} else if (v.mile >= (22 + 24 + 21 / 2)
								&& v.mile < (22 + 24 + 21 + 62 / 2)) {
							System.out.println(v.name + " is "
									+ Math.abs(v.mile - 22 - 24 - 21)
									+ " away from " + station_xb[3]);
						} else if (v.mile >= (22 + 24 + 21 + 62 / 2)
								&& v.mile < (22 + 24 + 21 + 62 + 21 / 2)) {
							System.out.println(v.name + " is "
									+ Math.abs(v.mile - 22 - 24 - 21 - 62)
									+ " away from " + station_xb[4]);
						} else if (v.mile >= (22 + 24 + 21 + 62 + 21 / 2)
								&& v.mile < (22 + 24 + 21 + 62 + 21 + 24 / 2)) {
							System.out.println(v.name + " is "
									+ Math.abs(v.mile - 22 - 24 - 21 - 62 - 21)
									+ " away from " + station_xb[5]);
						} else {
							System.out.println(v.name + " is "
									+ (22 + 24 + 21 + 62 + 21 + 24 - v.mile)
									+ " away from " + station_xb[5]);
						}
					}
				}
			}

			tmp++;
			tmp_count++;
			sumPassengerBX += r.nextInt(2);
			sumPassengerXB += r.nextInt(2);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
