package homework.wxq;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

public class StationTest {
	private static final float[] P = { (float) 0.05, (float) 0.1, (float) 0.15,
			(float) 0.2, (float) 0.22 };

	// public static final int
	public static class P0 {
		private static final int XBxn = 0;
		private static final int XBxy = 1;
		private static final int XBxp = 2;
		private static final int XBwg = 3;
		private static final int XBcp = 4;
		private static final int XBgz = 5;
		private static final int XBbj = 6;
	}

	public static class P1 {
		private static final int BXbj = 0;
		private static final int BXgz = 1;
		private static final int BXcp = 2;
		private static final int BXwg = 3;
		private static final int BXxp = 4;
		private static final int BXxy = 5;
		private static final int BXxn = 6;
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
		int flag_stop = 0;// tell if is stopped
		Iterator<Entry<Float, Vehical>> it_xb;
		Iterator<Entry<Float, Vehical>> it_bx;
		// public enum P0 {
		// XBxy,XBxp,XBwg,XBcp,XBgz,XBbj;
		// }
		P0 p0 = new P0();
		P1 p1 = new P1();
		float[] p_xb = new float[7];
		float[] p_bx = new float[7];
		int[] interval_volvo_xb = { 12, 13, 11, 33, 11, 13 };
		int[] interval_evco_xb = { 16, 17, 15, 44, 15, 17 };
		int[] interval_volvo_bx = { 13, 11, 33, 11, 13, 12 };
		int[] interval_evco_bx = { 17, 15, 44, 15, 17, 16 };
		// 5,12,4,15
		Vehical[] volvo_xb = new Volvo[5];
		Vehical[] volvo_bx = new Volvo[4];
		Vehical[] evco_xb = new Evco[12];
		Vehical[] evco_bx = new Evco[15];
		// LinkedQueue test = new LinkedQueue();
		LinkedList llq_volvo_xb = new LinkedList();
		LinkedList llq_evco_xb = new LinkedList();
		LinkedList llq_volvo_bx = new LinkedList();
		LinkedList llq_evco_bx = new LinkedList();

		TreeMap<Float, Vehical> llq_xb = new TreeMap<Float, Vehical>();// record
																		// the
																		// running
																		// car
																		// from
																		// XN to
																		// BJ
		TreeMap<Float, Vehical> llq_bx = new TreeMap<Float, Vehical>();// record
																		// the
																		// running
																		// car
																		// from
																		// BJ to
																		// XN

		final String[] station_xb = { "西安", "咸阳", "兴平", "武功", "蔡家坡", "虎镇", "宝鸡" };
		final String[] station_bx = { "宝鸡", "虎镇", "蔡家坡", "武功", "兴平", "咸阳", "西安" };

		for (int i = 0; i < 5; i++) {
			volvo_xb[i] = new Volvo("volvo0" + i, 60, (float) 1.9, 48, "volvo");
			llq_volvo_xb.addLast(volvo_xb[i]);
			// llq_xb.addLast(volvo_xb[i]);
		}
		for (int i = 0; i < 4; i++) {
			volvo_bx[i] = new Volvo("volvo1" + i, 60, (float) 1.9, 48, "volvo");
			llq_volvo_bx.addLast(volvo_bx[i]);
			// llq_xb.addLast(volvo_bx[i]);
		}
		for (int i = 0; i < 12; i++) {
			evco_xb[i] = new Evco("evco0" + i, 20, (float) 1.4, 21, "evco");
			llq_evco_xb.addLast(evco_xb[i]);
			// llq_xb.addLast(evco_xb[i]);
		}
		for (int i = 0; i < 15; i++) {
			evco_bx[i] = new Evco("evco1" + i, 20, (float) 1.4, 21, "evco");
			llq_evco_bx.addLast(evco_bx[i]);
			// llq_xb.addLast(evco_bx[i]);
		}
		// 随机乘客数 before 8:00'clock
		for (int i = 0; i < 30; i++) {
			sumPassengerBX += r.nextInt(3);
			sumPassengerXB += r.nextInt(3);
		}
		// The following initiating some params in while
		{
			// 下述两行描述西安至宝鸡以及宝鸡至西安的乘客产生数
			// genPassenger0 = r.nextInt(2);
			// genPassenger1 = r.nextInt(2);
			// 下述6行描述从西安到宝鸡每个车站下车的概率
			p_xb[p0.XBxn] = 0;
			p_xb[p0.XBxy] = r.nextFloat() / 20;
			p_xb[p0.XBxp] = r.nextFloat() / 10;
			p_xb[p0.XBwg] = r.nextFloat() / 20 * 3;
			p_xb[p0.XBcp] = r.nextFloat() / 5;
			p_xb[p0.XBgz] = r.nextFloat() / 11 * 50;
			p_xb[p0.XBbj] = 1 - p_xb[p0.XBxy] - p_xb[p0.XBxp] - p_xb[p0.XBwg]
					- p_xb[p0.XBcp] - p_xb[p0.XBgz];
			// 下述6行描述从宝鸡到西安每个车站下车的概率
			p_xb[p1.BXbj] = 0;
			p_bx[p1.BXgz] = r.nextFloat() / 20;
			p_bx[p1.BXcp] = r.nextFloat() / 10;
			p_bx[p1.BXwg] = r.nextFloat() / 20 * 3;
			p_bx[p1.BXxp] = r.nextFloat() / 5;
			p_bx[p1.BXxy] = r.nextFloat() / 11 * 50;
			p_bx[p1.BXxn] = 1 - p_xb[p1.BXxy] - p_xb[p1.BXxp] - p_xb[p1.BXwg]
					- p_xb[p1.BXcp] - p_xb[p1.BXgz];

		}

		while (hour < 18) {
			Vehical vehical_xb = null;
			Vehical vehical_bx = null;
			count++;// 控制分钟与小时转换
			if (count >= 60) {
				hour++;
				minite = 0;
				count = minite;
			}
			minite = count;
			System.out.println("" + hour + ":" + minite);

			if ((tmp_count - 30) % 60 == 0) {// time for volvo depart
				if (!llq_volvo_xb.isEmpty()) {
					vehical_xb = (Volvo) llq_volvo_xb.removeFirst();// dequeue
																	// in volvo
																	// xian &&
																	// next
																	// enqueue
																	// in the
																	// running
																	// queue XB

					llq_xb.put(vehical_xb.mile, vehical_xb);
					if (sumPassengerXB <= 48) {// judge if summery of passengers
												// more than 48 of
						vehical_xb.loadPassengers = sumPassengerXB;
						sumPassengerXB = 0;
					} else {
						vehical_xb.loadPassengers = vehical_xb.passenger;
						sumPassengerXB -= vehical_xb.passenger;
					}
					vehical_xb.flag = true;
					System.out.println(vehical_xb.name
							+ " depart from xn load passengers"
							+ vehical_xb.loadPassengers);
				}
				if (!llq_volvo_bx.isEmpty()) {
					vehical_bx = (Volvo) llq_volvo_bx.removeFirst();
					llq_bx.put(vehical_xb.mile, vehical_bx);
					vehical_bx = (Volvo) llq_volvo_bx.removeFirst();
					if (sumPassengerBX <= 48) {
						vehical_bx.loadPassengers = sumPassengerBX;
						sumPassengerBX = 0;
					} else {
						vehical_bx.loadPassengers = vehical_bx.passenger;
						sumPassengerBX -= vehical_bx.passenger;
					}
					vehical_bx.flag = true;
					System.out.println(vehical_bx.name
							+ " depart from bj load passengers"
							+ vehical_bx.loadPassengers);
				}
			} else if (tmp_count % 20 == 0) {// as followed , time for evco
												// depart
				if (!llq_evco_xb.isEmpty()) {
					vehical_xb = (Evco) llq_evco_xb.getFirst();
					llq_evco_xb.removeFirst();
					llq_xb.put(vehical_xb.mile, vehical_xb);
					if (sumPassengerXB <= 21) {
						vehical_xb.loadPassengers = sumPassengerXB;
						sumPassengerXB = 0;
					} else {
						vehical_xb.loadPassengers = vehical_xb.passenger;
						sumPassengerXB -= vehical_xb.passenger;
					}
					vehical_xb.flag = true;
					System.out.println(vehical_xb.name
							+ "depart from xn load passengers"
							+ vehical_xb.loadPassengers);
				}
				if (!llq_evco_bx.isEmpty()) {
					vehical_bx = (Evco) llq_evco_bx.removeFirst();
					llq_bx.put(vehical_bx.mile, vehical_bx);
					if (sumPassengerBX <= 21) {
						vehical_bx.loadPassengers = sumPassengerBX;
						sumPassengerBX = 0;
					} else {
						vehical_bx.loadPassengers = vehical_bx.passenger;
						sumPassengerBX -= vehical_bx.passenger;
					}
					vehical_bx.flag = true;
					System.out.println(vehical_bx.name
							+ "depart from xn load passengers"
							+ vehical_bx.loadPassengers);
				}
			}

			//
			it_xb = llq_xb.entrySet().iterator();
			System.out
					.println("The summary of vehicals running from xa to bj is:"
							+ llq_xb.size());
			while (it_xb.hasNext()) {
				Vehical v = (Vehical) it_xb.next().getValue();
				int flag = -1;// stores which station is reaching

				for (int i = 0, cnt = 0; i < 6; i++) {
					if (v.type == "volvo")
						cnt += interval_volvo_xb[i];
					else
						cnt += interval_evco_xb[i];
					if (Math.abs(v.mile - cnt) < 1) {// 如果到站
						v.isAtStation = true;
						flag = i;
						System.out
								.println(v.name + "is at " + station_xb[flag]);
					}
				}

				if (v.isAtStation == true) {
					if ((int) (v.loadPassengers * p_xb[flag]) > 0) {
						v.loadPassengers -= v.loadPassengers * p_xb[flag];
						v.isStop = true;
						v.stop_minite = 0;
						// System.out.println(v.name + "is stopped at "
						// + station_xb[flag]);

					}
					if (flag == 5) {
						System.out.println(v.name + " enqueue bj to xn ");
						v.flag = false;
						if (v.type == "volvo") {
							llq_volvo_bx.addLast(v);
						} else if (v.type == "evco") {
							llq_evco_bx.addLast(v);
						}
						llq_xb.remove(v);
					}
					v.isAtStation = false;
				}

				if (v.isStop == true) {
					v.stop_minite++;
					if (v.stop_minite == 2) {
						v.isStop = false;
						v.stop_minite = 0;
					}
					System.out.println(v.name + "is stopped at"
							+ station_xb[flag] + "下车乘客 "
							+ (int) v.loadPassengers * p_xb[flag]);
				} else {// judge which station is close
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
								+ Math.abs(v.mile - 22 - 24) + " away from "
								+ station_xb[2]);
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
			
			//bj to xn
			it_bx = llq_bx.entrySet().iterator();
			System.out
					.println("The summary of vehicals running from xa to bj is:"
							+ llq_bx.size());
			while (it_bx.hasNext()) {
				Vehical v = (Vehical) it_bx.next().getValue();
				int flag = -1;// stores which station is reaching

				for (int i = 0, cnt = 0; i < 6; i++) {
					if (v.type == "volvo")
						cnt += interval_volvo_bx[i];
					else
						cnt += interval_evco_bx[i];
					if (Math.abs(v.mile - cnt) < 1) {// 如果到站
						v.isAtStation = true;
						flag = i;
						System.out
								.println(v.name + "is at " + station_bx[flag]);
					}
				}

				if (v.isAtStation == true) {
					if ((int) (v.loadPassengers * p_bx[flag]) > 0) {
						v.loadPassengers -= v.loadPassengers * p_bx[flag];
						v.isStop = true;
						v.stop_minite = 0;
						// System.out.println(v.name + "is stopped at "
						// + station_bx[flag]);

					}
					if (flag == 5) {
						System.out.println(v.name + " enqueue bj to xn ");
						v.flag = false;
						if (v.type == "volvo") {/////////////////////////////////////////////////////////
							llq_volvo_xb.addLast(v);
						} else if (v.type == "evco") {
							llq_evco_xb.addLast(v);
						}
						llq_bx.remove(v);
					}
					v.isAtStation = false;
				}

				if (v.isStop == true) {
					v.stop_minite++;
					if (v.stop_minite == 2) {
						v.isStop = false;
						v.stop_minite = 0;
					}
					System.out.println(v.name + "is stopped at"
							+ station_bx[flag] + "下车乘客 "
							+ (int) v.loadPassengers * p_bx[flag]);
				} else {// judge which station is close
					v.mile += 1.9;
					if (0 < v.mile && v.mile < 22 / 2) {
						System.out.println(v.name + " is " + v.mile
								+ " away from " + station_bx[0]);
					} else if (v.mile >= 22 / 2 && v.mile < (22 + 24 / 2)) {
						System.out.println(v.name + " is "
								+ Math.abs(v.mile - 22) + " away from "
								+ station_bx[1]);
					} else if (v.mile >= (22 + 24 / 2)
							&& v.mile < (22 + 24 + 21 / 2)) {
						System.out.println(v.name + " is "
								+ Math.abs(v.mile - 22 - 24) + " away from "
								+ station_bx[2]);
					} else if (v.mile >= (22 + 24 + 21 / 2)
							&& v.mile < (22 + 24 + 21 + 62 / 2)) {
						System.out.println(v.name + " is "
								+ Math.abs(v.mile - 22 - 24 - 21)
								+ " away from " + station_bx[3]);
					} else if (v.mile >= (22 + 24 + 21 + 62 / 2)
							&& v.mile < (22 + 24 + 21 + 62 + 21 / 2)) {
						System.out.println(v.name + " is "
								+ Math.abs(v.mile - 22 - 24 - 21 - 62)
								+ " away from " + station_bx[4]);
					} else if (v.mile >= (22 + 24 + 21 + 62 + 21 / 2)
							&& v.mile < (22 + 24 + 21 + 62 + 21 + 24 / 2)) {
						System.out.println(v.name + " is "
								+ Math.abs(v.mile - 22 - 24 - 21 - 62 - 21)
								+ " away from " + station_bx[5]);
					} else {
						System.out.println(v.name + " is "
								+ (22 + 24 + 21 + 62 + 21 + 24 - v.mile)
								+ " away from " + station_bx[5]);
					}
				}

			}

			tmp++;
			tmp_count++;
			sumPassengerBX += r.nextInt(3);
			sumPassengerXB += r.nextInt(3);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
