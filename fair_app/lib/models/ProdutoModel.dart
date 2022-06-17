import 'dart:async';

import 'dart:convert';

import 'package:http/http.dart' as http;

class ProdutoModel {
  final int idProduto;
  final String nome;
  final String tipo;
  final double preco;
  final String foto;
  final String validade;
  final double peso;

  const ProdutoModel({
    required this.idProduto,
    required this.nome,
    required this.tipo,
    required this.preco,
    required this.foto,
    required this.validade,
    required this.peso,
  });

  static Future get() async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/produto'));
    if (response.statusCode == 200) {
      final parsed = jsonDecode(response.body).cast<String,dynamic>();
      List<String> produtos = [];
      for (var produto in parsed["content"]) {
        produtos.add(produto["idProduto"].toString() + " - " + produto["nome"]);
      }
      return produtos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }

  static Future findByName(String name) async {
    final response = await http
        .get(Uri.parse('http://25.76.67.204:8080/produto/findByName/' + name));
    if (response.statusCode == 200) {
      List<String> produtos = [];
      for (var produto in jsonDecode(response.body)) {
        produtos.add(produto["nome"]);
      }
      return produtos;
    } else {
      return "Response: " + response.statusCode.toString();
    }
  }
}

