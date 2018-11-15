package com.bitcoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Utils;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.Wallet.SendResult;

public class MyWalletFee {
	public static void main(String[] args) {
		try {
			MyWalletFee myWallet = new MyWalletFee();
//			WalletAppKit kit = new WalletAppKit(myWallet.getTestNetParam(), new File("."), 회원고유번호);
			WalletAppKit kit = new WalletAppKit(myWallet.getTestNetParam(), new File("."), Properties.USER);
//			kit.setBlockingStartup(true); 이게 빠져야 하는 것으로 보인다. 
//			kit.setBlockingStartup(true);
//			kit.setPeerNodes(
//	                        new PeerAddress(InetAddress.getByName("node3.mycelium.com"), 18333),
//	                        new PeerAddress("13.209.138.82", 18332));
			myWallet.synchBlockchain(kit);
			
			System.out.println(kit.wallet().toString());
			
//			List<MyWallet> walletList = myWallet.getWalletAddress(kit);
//			for (MyWallet w : walletList) {
//				System.out.println(w.toString());
//			}
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
	
	public void setSendRequest(NetworkParameters params, String base58, String value, Wallet wallet, int fee) throws InsufficientMoneyException {
		Address address = Address.fromBase58(getTestNetParam(), base58);

		if (Float.parseFloat(value) > 0.0f) {
			SendRequest request = SendRequest.to(address, Coin.parseCoin(value));
			request.shuffleOutputs = false;
			request.ensureMinRequiredFee = true;

			switch (fee) {
			case 0:
				request.feePerKb = Transaction.DEFAULT_TX_FEE;
				break;
			case 1:
				request.feePerKb = Transaction.REFERENCE_DEFAULT_MIN_TX_FEE;
				break;
			default:
				request.feePerKb = Transaction.MIN_NONDUST_OUTPUT;
				break;
			}
			
			SendResult sendResult = wallet.sendCoins(request);
		}
	}

}
