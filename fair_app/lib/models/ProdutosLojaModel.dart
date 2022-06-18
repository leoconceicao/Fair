import 'dart:async';
import 'dart:convert';

import 'package:http/http.dart' as http;

class ProdutosLojaModel {
  final int fkProduto;
  final int fkLoja;
  final double preco;

  const ProdutosLojaModel({
    required this.fkProduto,
    required this.fkLoja,
    required this.preco,
  });

  static Future<String> addProdutoLoja(
      int idProduto, int idLoja, double preco) async {
    ProdutosLojaModel plm =
        ProdutosLojaModel(fkProduto: idProduto, fkLoja: idLoja, preco: preco);
    final response = await http.post(
        Uri.parse('http://25.76.67.204:8080/produtoLoja'),
        body: plm.toJson().toString(),
        headers: {"Content-Type": "application/json"});
    if (response.statusCode == 201) {
      return response.body;
    } else {
      return "Error";
    }
  }

  static Future findLojasByProduto(String nome) async {
    final response = await http.get(Uri.parse(
        'http://25.76.67.204:8080/produtoLoja/findLojasByProduto/"' +
            nome +
            '"'));
    if (response.statusCode == 200) {
      List<String> lojas = [];
      for (var loja in jsonDecode(response.body)) {
        lojas.add("#" +
            loja["fkProduto"]["idProduto"].toString() +
            " - " +
            loja["fkLoja"]["nome"] +
            " - R\$ " +
            (loja["preco"].toString()) +
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
      List<String> produtos = [];
      for (var produto in jsonDecode(response.body)) {
        produtos.add("#" +
            produto["fkProduto"]["idProduto"].toString() +
            " - " +
            produto["fkProduto"]["nome"].toString());
      }
      return produtos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  factory ProdutosLojaModel.fromJson(Map<String, dynamic> json) {
    return ProdutosLojaModel(
      fkProduto: json['fkProduto'],
      fkLoja: json['fkLoja'],
      preco: json['preco'],
    );
  }

  Map<String, dynamic> toJson() => {
        '"fkProduto"': fkProduto.toString(),
        '"fkLoja"': "\"" + fkLoja.toString() + "\"",
        '"preco"': "\"" + preco.toString() + "\"",
      };
}
