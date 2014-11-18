package com.sapient.market.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {

	public static void main(String...args) {
		try {
			InetAddress[] addresses = InetAddress.getAllByName("www.gmail.com");
			for (InetAddress addr : addresses) {
				System.out.println(addr);
			}
			System.out.println(InetAddress.getByName("2404:6800:4003:807:0:0:0:1015").getHostName());
			System.out.println(InetAddress.getByName("74.125.68.18").getHostName());
			System.out.println(InetAddress.getByName("74.125.68.17").getHostName());
			System.out.println(InetAddress.getByName("74.125.68.19").getHostName());
			System.out.println(InetAddress.getByName("74.125.68.83").getHostName());
			System.out.println(InetAddress.getLocalHost());
			
			byte[] address = {107, 23, (byte) 216, (byte) 196};
			InetAddress lessWrong = InetAddress.getByAddress(address);
			InetAddress lessWrongWithname = InetAddress.getByAddress(
			   "lesswrong.com", address);
			System.out.println(lessWrong);
			System.out.println(lessWrongWithname);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
