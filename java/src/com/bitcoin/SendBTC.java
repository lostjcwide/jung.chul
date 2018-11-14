package com.bitcoin;

import java.io.File;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.kits.WalletAppKit;

public class SendBTC {
	private static String sendAddress = "2NAbAEhvWmFckpyhBEn3HBGHP1m97Lf3gei";
	
	public static void main(String[] args) {
		try {
			MyWallet2 myWallet = new MyWallet2();
			WalletAppKit kit = new WalletAppKit(myWallet.getTestNetParam(), new File("."), Properties.USER);
			kit.setBlockingStartup(false);
			kit.setPeerNodes(
//                        new PeerAddress(InetAddress.getByName("node3.mycelium.com"), 18333),
					new PeerAddress("13.209.138.82", 18332));
			myWallet.synchBlockchain(kit);
			Address address = Address.fromBase58(myWallet.getTestNetParam(), sendAddress);
			Transaction tx = kit.wallet().createSend(address, Coin.parseCoin("0.005"));
			final Peer peer = kit.peerGroup().getConnectedPeers().get(0);
			peer.sendMessage(tx);
			
			System.out.println(kit.wallet().toString());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
