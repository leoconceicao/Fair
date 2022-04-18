CREATE DATABASE `Fair` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

CREATE TABLE `Cliente` (
  `idCliente` int(11) NOT NULL,
  PRIMARY KEY (`idCliente`),
  CONSTRAINT `fk_cliente_pessoa` FOREIGN KEY (`idCliente`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `Funcionario` (
  `idFuncionario` int(11) NOT NULL,
  `funcao` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`),
  CONSTRAINT `fk_funcionario_loja` FOREIGN KEY (`idFuncionario`) REFERENCES `Loja` (`idLoja`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_pessoa` FOREIGN KEY (`idFuncionario`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `Loja` (
  `idLoja` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `endereco` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cnpj` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idLoja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `Pedido` (
  `idPedido` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `periodicidade` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `numeroEntregas` int(11) DEFAULT NULL,
  `peso` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  CONSTRAINT `fk_pedido_cliente` FOREIGN KEY (`idPedido`) REFERENCES `Cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_loja` FOREIGN KEY (`idPedido`) REFERENCES `Loja` (`idLoja`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_produto` FOREIGN KEY (`idPedido`) REFERENCES `Produto` (`idProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `Pessoa` (
  `idPessoa` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `endereco` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cpf` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `Produto` (
  `idProduto` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `foto` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `validade` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `peso` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

