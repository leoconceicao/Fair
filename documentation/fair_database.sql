CREATE DATABASE Fair;

USE Fair;

CREATE TABLE IF NOT EXISTS Loja (
  idLoja INT(11) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  endereco VARCHAR(255) NOT NULL,
  cnpj VARCHAR(255) NOT NULL,
  telefone VARCHAR(255) NOT NULL,
  PRIMARY KEY (idLoja),
  UNIQUE INDEX cnpj_UNIQUE (cnpj ASC) VISIBLE);


CREATE TABLE IF NOT EXISTS Produto (
  idProduto INT(11) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  tipo VARCHAR(255) NOT NULL,
  foto VARCHAR(255) NOT NULL,
  validade VARCHAR(255) NOT NULL,
  peso VARCHAR(255) NOT NULL,
  PRIMARY KEY (idProduto));

CREATE TABLE IF NOT EXISTS Pessoa (
  idPessoa INT(11) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  endereco VARCHAR(255) NOT NULL,
  telefone VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (idPessoa),
  UNIQUE INDEX cpf_UNIQUE (cpf ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS Pedido (
  idPedido INT(11) NOT NULL,
  quantidade INT(11) NOT NULL,
  periodicidade VARCHAR(255) NULL DEFAULT NULL,
  numeroEntregas INT(11) NULL,
  peso DECIMAL(10,0) NOT NULL,
  fkCliente INT(11) NOT NULL,
  fkVendedor INT(11) NOT NULL,
  INDEX fk_Pedido_Pessoa1_idx (fkCliente ASC) VISIBLE,
  INDEX fk_Pedido_Pessoa2_idx (fkVendedor ASC) VISIBLE,
  CONSTRAINT fk_pedido_loja
    FOREIGN KEY (idPedido)
    REFERENCES Loja (idLoja)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_pedido_produto
    FOREIGN KEY (idPedido)
    REFERENCES Produto (idProduto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pedido_Pessoa1
    FOREIGN KEY (fkCliente)
    REFERENCES Pessoa (idPessoa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pedido_Pessoa2
    FOREIGN KEY (fkVendedor)
    REFERENCES Pessoa (idPessoa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS Pessoa_Loja (
  fkLoja INT(11) NOT NULL,
  fkPessoa INT(11) NOT NULL,
  TipoPessoa TINYINT(1) NULL,
  PRIMARY KEY (fkLoja, fkPessoa, TipoPessoa),
  INDEX fk_Loja_has_Pessoa_Pessoa1_idx (fkPessoa ASC) VISIBLE,
  INDEX fk_Loja_has_Pessoa_Loja1_idx (fkLoja ASC) VISIBLE,
  CONSTRAINT fk_Loja_has_Pessoa_Loja1
    FOREIGN KEY (fkLoja)
    REFERENCES Loja (idLoja)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Loja_has_Pessoa_Pessoa1
    FOREIGN KEY (fkPessoa)
    REFERENCES Pessoa (idPessoa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS Comunicacao (
  idMensagem INT NOT NULL,
  fkProduto INT(11) NOT NULL,
  fkPessoaRemetente INT(11) NOT NULL,
  fkPessoaDestinatario INT(11) NOT NULL,
  dsConteudo VARCHAR(2000) NOT NULL,
  PRIMARY KEY (idMensagem),
  INDEX fk_Comunicacao_Pessoa1_idx (fkPessoaRemetente ASC) VISIBLE,
  INDEX fk_Comunicacao_Pessoa2_idx (fkPessoaDestinatario ASC) VISIBLE,
  INDEX fk_Comunicacao_Produto1_idx (fkProduto ASC) VISIBLE,
  CONSTRAINT fk_Comunicacao_Pessoa1
    FOREIGN KEY (fkPessoaRemetente)
    REFERENCES Pessoa (idPessoa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Comunicacao_Pessoa2
    FOREIGN KEY (fkPessoaDestinatario)
    REFERENCES Pessoa (idPessoa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Comunicacao_Produto1
    FOREIGN KEY (fkProduto)
    REFERENCES Produto (idProduto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
