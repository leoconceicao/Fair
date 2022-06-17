import 'dart:async';

import 'dart:convert';

import 'package:http/http.dart' as http;

class LojaModel {
  final int idLoja;
  final String nome;
  final String cnpj;
  final double telefone;
  final int fkLogradouro;

  const LojaModel({
    required this.idLoja,
    required this.nome,
    required this.cnpj,
    required this.telefone,
    required this.fkLogradouro,
  });

  factory LojaModel.fromJson(Map<String, dynamic> json) {
    return LojaModel(
      idLoja: json['idLoja'],
      nome: json['nome'],
      cnpj: json['cnpj'],
      telefone: json['telefone'],
      fkLogradouro: json['fkLogradouro']["idLogradouro"],
    );
  }

  static Future findLojasByProduto(String idProduto) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/loja/findLojasByProduto/' + idProduto));
    if (response.statusCode == 200) {
      List<String> lojas = [];
      for (var loja in jsonDecode(response.body)) {
        lojas.add(loja["nome"]);
      }
      return lojas;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }
}

