package com.bitcoin;

import java.io.File;
import java.util.Collection;

import org.bitcoinj.core.Peer;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.kits.WalletAppKit;

public class SendBTCApplyFee {
	private static String sendAddress = "mv33qbXXv2THztxpKhKvTJrGad3Zs46i4K";

	public static void main(String[] args) {
		try {
			MyWalletFee myWallet = new MyWalletFee();
			WalletAppKit kit = new WalletAppKit(myWallet.getTestNetParam(), new File("."), Properties.USER);
			// kit.setPeerNodes(
			// new PeerAddress(InetAddress.getByName("node3.mycelium.com"),
			// 18333),
			// new PeerAddress("13.209.138.82", 18332));
			myWallet.synchBlockchain(kit);

			myWallet.setSendRequest(myWallet.getTestNetParam(), sendAddress, "0.1", kit.wallet(), 0);

			Collection<Transaction> pendingTxs = kit.wallet().getPendingTransactions();
			final Peer peer = kit.peerGroup().getConnectedPeers().get(0);
			for (Transaction t : pendingTxs) {
				peer.sendMessage(t);
			}

			System.out.println(kit.wallet().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
