package br.com.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.backend.model.Customer_account;

public class DAO_Customer_account {

	public boolean batchRecordCustomer_account(ArrayList<Customer_account> listCustomer_account)
			throws ClassNotFoundException, SQLException {

		Connection con = new ConnectionDB().connection();
		PreparedStatement pst = null;
		boolean record = true;

		for (Customer_account customer : listCustomer_account) {
			String sql = "insert into tb_customer_account (cpf_cpnj,nm_customer,is_active,vl_total) values ((?),(?),(?),(?))";
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, customer.getCpf_cpnj());
				pst.setString(2, customer.getNm_customer());
				pst.setBoolean(3, customer.isIs_active());
				pst.setDouble(4, customer.getVl_total());
				pst.execute();

			} catch (SQLException e) {
				record = false;
				e.printStackTrace();
			}

		}
		con.close();
		return record;

	}

	public Double avgTotalValue() throws ClassNotFoundException, SQLException {
		Connection con = new ConnectionDB().connection();

		boolean record = true;
		double avg = 0;
		String sql = "select avg(vl_total) as vl_total from tb_customer_account where (id_customer between 1500 AND 2700) AND vl_total>560";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rst = pst.executeQuery(sql);
		while (rst.next()) {
			avg = rst.getDouble("vl_total");
		}
		return avg;
	}

	public ArrayList<Customer_account> customerUsedAvg() throws ClassNotFoundException, SQLException {
		Connection con = new ConnectionDB().connection();
		ArrayList<Customer_account> listCustomer_account = new ArrayList<Customer_account>();
		boolean record = true;
		double v = 0;
		String sql = "select id_customer as id_customer,vl_total as vl_total from tb_customer_account where (id_customer between 1500 AND 2700) AND vl_total>560 order by vl_total desc";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rst = pst.executeQuery(sql);
		Customer_account customer_account;
		while (rst.next()) {
			customer_account = new Customer_account();
			customer_account.setId_customer(rst.getLong("id_customer"));
			customer_account.setVl_total(rst.getDouble("vl_total"));
			listCustomer_account.add(customer_account);
		}
		return listCustomer_account;
	}

}
