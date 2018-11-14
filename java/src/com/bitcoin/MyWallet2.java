package com.bitcoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.Utils;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;

public class MyWallet2 {
	public static void main(String[] args) {
		try {
			MyWallet2 myWallet = new MyWallet2();
			WalletAppKit kit = new WalletAppKit(myWallet.getTestNetParam(), new File("."), "test");
			kit.setBlockingStartup(false);
			kit.setPeerNodes(
//	                        new PeerAddress(InetAddress.getByName("node3.mycelium.com"), 18333),
	                        new PeerAddress("13.209.138.82", 18332));
			myWallet.synchBlockchain(kit);
			List<MyWallet> walletList = myWallet.getWalletAddress(kit);
			for (MyWallet w : walletList) {
				System.out.println(w.toString());
			}
			Thread.sleep(1000);
			kit.stopAsync();
			kit.awaitTerminated();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public NetworkParameters getTestNetParam() {
		return MainNetParams.fromID(MainNetParams.ID_TESTNET);
	}

	public WalletAppKit initialWallet(NetworkParameters params) {
		String filePrefix = "wallet-service-testnet";
		return new WalletAppKit(params, new File("d:/app"), filePrefix);
	}

	public void synchBlockchain(WalletAppKit kit) {
		System.out.println("Synchronizing the blockchain ...");
		kit.startAsync();
		kit.awaitRunning();
		System.out.println("Synchronized the blockchain ...");
	}

	public List<MyWallet> getWalletAddress(WalletAppKit kit) {
		List<MyWallet> walletList = new ArrayList<MyWallet>();
		List<Address> list = kit.wallet().getWatchedAddresses();
		if (list.size() < 5) {
			kit.wallet().addWatchedAddress(kit.wallet().freshReceiveAddress());
			System.out.println("New address created");
		}
		MyWallet w = null;
		for (Address addr : kit.wallet().getWatchedAddresses()) {
			w = new MyWallet();
			w.setAddress(addr.toBase58());
			w.setBalance(kit.wallet().getBalance().toFriendlyString());
			DeterministicSeed seed = kit.wallet().getActiveKeyChain().getSeed();
			w.setSeed(seed.toHexString());
			if ((seed.getCreationTimeSeconds() - 1525190911) < 1) {
				seed.setCreationTimeSeconds(System.currentTimeMillis());
			}
			w.setCreationTime(Utils.dateTimeFormat(seed.getCreationTimeSeconds()));
			w.setMnemonics(Utils.join(seed.getMnemonicCode()));
			walletList.add(w);
		}
		return walletList;
	}

}
