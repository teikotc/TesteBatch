Create database backEndTest;
use backEndTest;
CREATE TABLE tb_customer_account (id_customer BIGINT NOT NULL AUTO_INCREMENT, cpf_cpnj varchar(50)  NOT NULL, nm_customer varchar (200)  NOT NULL,is_active TINYINT  NOT NULL,vl_total decimal(11,2), PRIMARY KEY (id_customer) );



