package br.com.backend.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.SimpleAttributeSet;

import br.com.backend.dao.DAO_Customer_account;
import br.com.backend.model.Customer_account;

public class Controller_customer {

	public void recordBatch() throws IOException, ClassNotFoundException, SQLException {

		String path = "";
		// Verifica o sistema operacional
		// Caso for windows segue o seguinte path
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			path = System.getProperty("user.dir") + "\\Arquivo_Batch\\batch.txt";
		}
		// Caso nao seja windows
		else {
			path = System.getProperty("user.dir") + "/Arquivo_Batch/batch.txt";
		}

		// Chama o metodo para carregar a lista com Contas de cliente e manda para DAO
		if (new DAO_Customer_account().batchRecordCustomer_account(getListCustomer(path))) {
			System.out.println("Gravado com sucesso");
		}

		System.out.println("A media dos valores e: R$ " + new DAO_Customer_account().avgTotalValue());
		System.out.println("-------------------------------------------------------");
		for (Customer_account customer_account : new DAO_Customer_account().customerUsedAvg()) {
			System.out.println("Id:" + customer_account.getId_customer() + " Valor $:" + customer_account.getVl_total());
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		new Controller_customer().recordBatch();

	}

	public ArrayList<Customer_account> getListCustomer(String path) throws IOException {
		File file = new File(path);
		FileReader flr = new FileReader(file);
		BufferedReader bfr = new BufferedReader(flr);

		String line = "";
		String splitter[];
		ArrayList<Customer_account> listaClientes = new ArrayList<Customer_account>();
		Customer_account customer_account;

		while (bfr.ready()) {
			try {
				customer_account = new Customer_account();
				line = bfr.readLine();
				splitter = line.split(";");
				customer_account.setCpf_cpnj(splitter[0]);
				customer_account.setNm_customer(splitter[1]);
				customer_account.setIs_active(Boolean.parseBoolean(splitter[2]));
				customer_account.setVl_total(Double.parseDouble(splitter[3]));
				listaClientes.add(customer_account);
			} catch (Exception e) {
				System.out.println("linha fora do padrao \n" + line);
			}

		}
		return listaClientes;
	}

}
