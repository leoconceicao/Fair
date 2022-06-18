import 'dart:async';
import 'dart:convert';

import 'package:http/http.dart' as http;

class ProdutosLojaModel {
  final int fkProduto;
  final int fkLoja;

  const ProdutosLojaModel({
    required this.fkProduto,
    required this.fkLoja,
  });

  factory ProdutosLojaModel.fromJson(Map<String, dynamic> json) {
    return ProdutosLojaModel(
      fkProduto: json['fkProduto'],
      fkLoja: json['fkLoja'],
    );
  }

  static Future findLojasByProduto(String idProduto) async {
    final response = await http.get(Uri.parse(
        'http://25.76.67.204:8080/produtoLoja/findLojasByProduto/' +
            idProduto));
    if (response.statusCode == 200) {
      List<String> lojas = [];
      for (var loja in jsonDecode(response.body)) {
        lojas.add(loja["fkLoja"]["nome"] +
            " - R\$ " +
            (loja["preco"] as int).toDouble().toString() +
            "0");
      }
      return lojas;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future findProdutosByLoja(String idLoja) async {
    final response = await http.get(Uri.parse(
        'http://25.76.67.204:8080/produtoLoja/findProdutosByLoja/' +
            idLoja.toString()));
    if (response.statusCode == 200) {
      List<String> lojas = [];
      for (var loja in jsonDecode(response.body)) {
        lojas.add(loja["fkLoja"]["nome"] +
            " - R\$ " +
            (loja["preco"] as int).toDouble().toString() +
            "0");
      }
      return lojas;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }
}
