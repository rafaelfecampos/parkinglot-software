-- estacionamento.cliente definition
use estacionamento;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `cpf` char(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `senha` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- estacionamento.estacionamento definition

CREATE TABLE `estacionamento` (
  `id` int NOT NULL,
  `valorMensal` double DEFAULT NULL,
  `valorSemanal` double DEFAULT NULL,
  `valorDiaria` double DEFAULT NULL,
  `ValorHora` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- estacionamento.funcionario definition

CREATE TABLE `funcionario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `senha` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;






CREATE TABLE `veiculo` (
  `modelo` varchar(20) NOT NULL,
  `tipoPlano` enum('MENSAL','SEMANAL','DIARIA','HORA') DEFAULT NULL,
  `placa` char(7) NOT NULL,
  `id_cliente` int DEFAULT NULL,
  PRIMARY KEY (`placa`),
  KEY `clienteFK` (`id_cliente`),
  CONSTRAINT `clienteFK` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- estacionamento.estacionar definition

CREATE TABLE `estacionar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entrada` varchar(50) ,
  `saida` varchar(50) ,
  `placa_carro` char(7) DEFAULT NULL,
  `id_estacionamento` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  key placaFK(placa_carro),
  constraint placaFK foreign key (placa_carro) references veiculo(placa),
  KEY `estacionamentoFK` (`id_estacionamento`),
  CONSTRAINT `estacionamentoFK` FOREIGN KEY (`id_estacionamento`) REFERENCES `estacionamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- estacionamento.veiculo definition



INSERT INTO estacionamento.estacionamento
(id, valorMensal, valorSemanal, valorDiaria, ValorHora)
VALUES(0, 250.00, 100.00, 30.00, 8.00);
INSERT INTO estacionamento.cliente (id, nome, cpf, login, senha) VALUES(0, 'juan', '48838473648', 'juan', '1234');
INSERT INTO estacionamento.cliente (id, nome, cpf, login, senha) VALUES(0, 'rafael', '23421245678', 'rafael', '12345');

INSERT INTO estacionamento.funcionario (id, login, senha) VALUES(0, 'admin', 'admin');