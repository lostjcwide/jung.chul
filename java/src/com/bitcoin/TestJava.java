package com.bitcoin;

import java.io.File;
import java.net.InetAddress;

import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.kits.WalletAppKit;

public class TestJava {
	public static void main(String[] args) throws Exception {
		MyWallet2 myWallet = new MyWallet2();
		WalletAppKit walletAppKit = new WalletAppKit(myWallet.getTestNetParam(), new File("."), "test");
        walletAppKit.setAutoSave(true);
        walletAppKit.setBlockingStartup(false);
        walletAppKit.setPeerNodes(
//                        new PeerAddress(InetAddress.getByName("node3.mycelium.com"), 18333),
                        new PeerAddress("13.209.138.82", 18332));
        
        walletAppKit.startAsync();
//        walletAppKit.awaitRunning();
        
        System.out.println(1);
	}
}
