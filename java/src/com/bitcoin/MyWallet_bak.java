package com.bitcoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;

public class MyWallet_bak {

	public static void main(String[] args) {
		try {
			MyWallet_bak myWallet = new MyWallet_bak();
			Wallet wallet = myWallet.initialWallet(myWallet.getTestNetParam());
			Map<String, String> map = myWallet.processWallet(wallet);
			System.out.println("Address : \t" + map.get("Address"));
			System.out.println("Balance : \t" + map.get("Balance"));
			System.out.println("Seed : \t" + map.get("Seed"));
			System.out.println("Creationtime : \t" + map.get("Creationtime"));
			System.out.println("Mnemonics : \t" + map.get("Mnemonics"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Map<String, String> processWallet(Wallet wallet) {
		HashMap<String, String> map = new HashMap<String, String>();
		DeterministicSeed seed = wallet.getActiveKeyChain().getSeed();
		seed.setCreationTimeSeconds(System.currentTimeMillis());
		String creationtime = Utils.dateTimeFormat(seed.getCreationTimeSeconds());
		String seedHex = seed.toHexString();
		List<String> mnemonics = seed.getMnemonicCode();
		String address = wallet.currentReceiveAddress().toBase58();
		String balance = wallet.getBalance().getValue() + " BTC";
		map.put("Address", address);
		map.put("Balance", balance);
		map.put("Seed", seedHex);
		map.put("Creationtime", creationtime);
		map.put("Mnemonics", Utils.join(mnemonics));
		return map;
	}

	public Wallet initialWallet(NetworkParameters params) {
		return new Wallet(params);
	}

	public NetworkParameters getTestNetParam() {
		return MainNetParams.fromID(MainNetParams.ID_TESTNET);
	}

}
